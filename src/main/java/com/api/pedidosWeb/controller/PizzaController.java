package com.api.pedidosWeb.controller;

import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.service.PizzaService;
import com.api.pedidosWeb.service.UploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("/create")
    public ResponseEntity<Pizza> savePizza(
            @RequestPart("pizza") String pizzaJson,
            @RequestPart("file") MultipartFile file) {
        Pizza pizza;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            pizza = objectMapper.readValue(pizzaJson, Pizza.class);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String imagePath = null;
        try {
            imagePath = uploadFileService.copy(file, null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Pizza nuevaPizza = pizzaService.savePizza(
                pizza.getNombre(),
                pizza.getDescripcion(),
                pizza.getPrecio_total(),
                imagePath
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPizza);
    }


    @PutMapping("/update")
    public ResponseEntity<Pizza> updatePizzaById(@RequestPart("pizza") String pizzaJson,
                                                 @RequestPart(value = "file", required = false) MultipartFile file,
                                                 @RequestParam(value = "idPizza") Long id) {
        Pizza pizza;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            pizza = objectMapper.readValue(pizzaJson, Pizza.class);
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
            Pizza pizzaUpdated = this.pizzaService.updatePizzaById(pizza, id, imagePath);
            return ResponseEntity.status(HttpStatus.OK).body(pizzaUpdated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public List<Pizza> getAllPizza() {
        List<Pizza> pizzas = pizzaService.getAllPizza();
        pizzas.forEach(pizza -> {
            String imageUrl = "http://localhost:8080/pizza/images/" + pizza.getImagen();
            pizza.setImagen(imageUrl);
        });
        return pizzas;
    }
    @GetMapping("/getById")
    public ResponseEntity<Pizza> obtenerPizzaPorId(@RequestParam(value = "idPizza") Long id) {
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza != null) {
            return ResponseEntity.ok(pizza);
        } else {
            return ResponseEntity.notFound().build();
        }
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
}
