package com.api.pedidosWeb.controller;

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

    @PostMapping("/create")
    public ResponseEntity<ToppingPizza> saveToppingPizza (@RequestBody ToppingPizza toppingPizza){
        ToppingPizza nuevoToppingPizza = toppingPizzaService.saveToppingPizza(
                toppingPizza.getNombre(),
                toppingPizza.getPrecio()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoToppingPizza);
    }

    @PutMapping("/update")
    public ResponseEntity<ToppingPizza> updatePizzaById(@RequestBody ToppingPizza toppingPizza, @RequestParam(value = "idToppingPizza") Long id) {
        ToppingPizza toppingPizzaUp = this.toppingPizzaService.updateToppingPizzaById(toppingPizza, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(toppingPizzaUp);
    }

    @GetMapping("/getAll")
    public List<ToppingPizza> getAllToppingPizza(){
        return toppingPizzaService.getAllToppingPizza();
    }

    @GetMapping("/getById")
    public ResponseEntity<ToppingPizza> obtenerTipoPizzaPorId(@RequestParam(value = "idPizza") Long id) {
        ToppingPizza toppingPizza = toppingPizzaService.getToppingPizzaById(id);
        if (toppingPizza != null) {
            return ResponseEntity.ok(toppingPizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
