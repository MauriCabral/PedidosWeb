package com.api.pedidosWeb.controller;

import com.api.pedidosWeb.dto.ToppingPizzaDto;
import com.api.pedidosWeb.mapper.ToppingPizzaMapper;
import com.api.pedidosWeb.model.ToppingPizza;
import com.api.pedidosWeb.service.serviceImplementation.ToppingPizzaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toppingPizza")
public class ToppingPizzaController {

    @Autowired
    private ToppingPizzaServiceImp toppingPizzaServiceImp;

    @Autowired
    private ToppingPizzaMapper toppingPizzaMapper;

    @GetMapping("/getAll")
    public List<ToppingPizzaDto> getAll(){
        List<ToppingPizza> toppingPizzas = toppingPizzaServiceImp.getAll();
        return toppingPizzaMapper.fromEntity(toppingPizzas);
    }

    @GetMapping("/getById")
    public ResponseEntity<ToppingPizzaDto> getById(@RequestParam(value = "id") Long id) {
        ToppingPizza toppingPizza = toppingPizzaServiceImp.getById(id);
        if (toppingPizza == null) {
            return ResponseEntity.notFound().build();
        }
        ToppingPizzaDto toppingPizzaDto = toppingPizzaMapper.fromEntity(toppingPizza);
        return ResponseEntity.ok(toppingPizzaDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ToppingPizzaDto> create(@RequestBody ToppingPizzaDto toppingPizzaDto){
        ToppingPizza createToppingPizza = toppingPizzaServiceImp.create(toppingPizzaMapper.fromDto(toppingPizzaDto));
        ToppingPizzaDto createToppingPizzaDto = toppingPizzaMapper.fromEntity(createToppingPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(createToppingPizzaDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ToppingPizzaDto> updateById(@RequestBody ToppingPizzaDto toppingPizzaDto, @RequestParam(value = "id") Long id) {
        ToppingPizza toppingPizzaUpdate = toppingPizzaMapper.fromDto(toppingPizzaDto);
        ToppingPizza toppingPizzaUpdated = toppingPizzaServiceImp.updateById(toppingPizzaUpdate, id);
        ToppingPizzaDto dtoToppingPizzaUpdate = toppingPizzaMapper.fromEntity(toppingPizzaUpdated);
        return ResponseEntity.ok(dtoToppingPizzaUpdate);
    }
}
