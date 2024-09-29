package com.api.pedidosWeb.controller;

import com.api.pedidosWeb.dto.ToppingPizzaDto;
import com.api.pedidosWeb.mapper.AbstractMapper;
import com.api.pedidosWeb.mapper.ToppingPizzaMapper;
import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.model.ToppingPizza;
import com.api.pedidosWeb.service.ToppingPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toppingPizza")
public class ToppingPizzaController {

    @Autowired
    private ToppingPizzaService toppingPizzaService;

    @Autowired
    private ToppingPizzaMapper toppingPizzaMapper;

    @GetMapping("/getAll")
    public List<ToppingPizzaDto> getAllToppingPizza(){
        List<ToppingPizza> toppingPizzas = toppingPizzaService.getAllToppingPizza();
        return toppingPizzaMapper.fromEntity(toppingPizzas);
    }

    @GetMapping("/getById")
    public ResponseEntity<ToppingPizzaDto> obtenerTipoPizzaPorId(@RequestParam(value = "idToppingPizza") Long id) {
        ToppingPizza toppingPizza = toppingPizzaService.getToppingPizzaById(id);
        if (toppingPizza == null) {
            return ResponseEntity.notFound().build();
        }
        ToppingPizzaDto toppingPizzaDto = toppingPizzaMapper.fromEntity(toppingPizza);
        return ResponseEntity.ok(toppingPizzaDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ToppingPizzaDto> createToppingPizza (@RequestBody ToppingPizzaDto toppingPizzaDto){
        ToppingPizza createToppingPizza = toppingPizzaService.createToppingPizza(toppingPizzaMapper.fromDto(toppingPizzaDto));
        ToppingPizzaDto createToppingPizzaDto = toppingPizzaMapper.fromEntity(createToppingPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(createToppingPizzaDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ToppingPizzaDto> updatePizzaById(@RequestBody ToppingPizzaDto toppingPizzaDto, @RequestParam(value = "idToppingPizza") Long id) {
        ToppingPizza toppingPizzaUpdate = toppingPizzaMapper.fromDto(toppingPizzaDto);
        ToppingPizza toppingPizzaUpdated = toppingPizzaService.updateToppingPizzaById(toppingPizzaUpdate, id);
        ToppingPizzaDto dtoToppingPizzaUpdate = toppingPizzaMapper.fromEntity(toppingPizzaUpdated);
        return ResponseEntity.ok(dtoToppingPizzaUpdate);
    }
}
