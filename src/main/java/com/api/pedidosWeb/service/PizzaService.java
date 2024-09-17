package com.api.pedidosWeb.service;

import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.repository.IPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private IPizzaRepository iPizzaRepository;

    public Pizza savePizza(String nombre , String descripcion, int precio){
        Pizza pizza = new Pizza();
        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio_total(precio);
        return iPizzaRepository.save(pizza);
    }

    public Pizza updatePizzaById(Pizza pizza1, Long id) {
        Pizza pizza = iPizzaRepository.findById(id).get();
        pizza.setNombre(pizza1.getNombre());
        pizza.setDescripcion(pizza1.getDescripcion());
        pizza.setPrecio_total(pizza1.getPrecio_total());
        return iPizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizza() {
        return iPizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id){
        return iPizzaRepository.findById(id).orElse(null);
    }
}
