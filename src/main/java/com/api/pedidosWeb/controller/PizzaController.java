package com.api.pedidosWeb.controller;

import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping("/create")
    public ResponseEntity<Pizza> savePizza (@RequestBody Pizza pizza){
        Pizza nuevaPizza = pizzaService.savePizza(
                pizza.getNombre(),
                pizza.getDescripcion(),
                pizza.getPrecio_total()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPizza);
    }

    @PutMapping("/update")
    public ResponseEntity<Pizza> updatePizzaById(@RequestBody Pizza pizza, @RequestParam(value = "idPizza") Long id) {
        Pizza pizzaUp = this.pizzaService.updatePizzaById(pizza, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(pizzaUp);
    }

    @GetMapping("/getAll")
    public List<Pizza> getAllPizza(){
        return pizzaService.getAllPizza();
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
}
