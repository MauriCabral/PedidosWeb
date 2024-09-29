package com.api.pedidosWeb.controller;

import com.api.pedidosWeb.dto.PizzaDto;
import com.api.pedidosWeb.mapper.PizzaMapper;
import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.service.PizzaService;
import com.api.pedidosWeb.service.UploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private PizzaMapper pizzaMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<PizzaDto>> getAllPizza() {
        List<Pizza> pizzas = pizzaService.getAllPizza();
        if (pizzas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        pizzas.forEach(pizza -> {
            String imageUrl = "http://localhost:8080/pizza/images/" + pizza.getImagen();
            pizza.setImagen(imageUrl);
        });
        List<PizzaDto> pizzasDto = pizzaMapper.fromEntity(pizzas);
        return new ResponseEntity<>(pizzasDto, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<PizzaDto> getPizzaById(@RequestParam(value = "idPizza") Long id) {
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        PizzaDto pizzaDto = pizzaMapper.fromEntity(pizza);
        return ResponseEntity.ok(pizzaDto);
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Resource file = uploadFileService.load(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PizzaDto> createPizza(@RequestPart("pizza") String pizzaJson, @RequestPart("file") MultipartFile file) {
        PizzaDto pizzaDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            pizzaDto = objectMapper.readValue(pizzaJson, PizzaDto.class);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String imagePath = null;
        try {
            imagePath = uploadFileService.copy(file, null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Pizza newPizza = pizzaMapper.fromDto(pizzaDto);
        newPizza = pizzaService.savePizza(newPizza.getNombre(), newPizza.getDescripcion(), newPizza.getPrecio_total(), imagePath);

        PizzaDto newPizzaDto = pizzaMapper.fromEntity(newPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPizzaDto);
    }

    @PutMapping("/update")
    public ResponseEntity<PizzaDto> updatePizzaById(@RequestPart("pizza") String pizzaJson, @RequestPart(value = "file", required = false) MultipartFile file, @RequestParam(value = "idPizza") Long id) {
        PizzaDto pizzaDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            pizzaDto = objectMapper.readValue(pizzaJson, PizzaDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String imagePath = null;
        if (file != null && !file.isEmpty()) {
            try {
                String existingFilename = pizzaService.getExistingFilenameById(id);
                imagePath = uploadFileService.copy(file, existingFilename);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        try {
            Pizza pizza = pizzaMapper.fromDto(pizzaDto);
            Pizza pizzaUpdated = this.pizzaService.updatePizzaById(pizza, id, imagePath);

            PizzaDto pizzaUpdateDto = pizzaMapper.fromEntity(pizzaUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(pizzaUpdateDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
