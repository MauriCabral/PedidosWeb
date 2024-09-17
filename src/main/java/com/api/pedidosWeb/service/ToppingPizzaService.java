package com.api.pedidosWeb.service;

import com.api.pedidosWeb.model.ToppingPizza;
import com.api.pedidosWeb.repository.IToppigPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingPizzaService {

    @Autowired
    private IToppigPizzaRepository iToppigPizzaRepository;

    public ToppingPizza saveToppingPizza(String nombre, int precio){
        ToppingPizza toppingPizza = new ToppingPizza();
        toppingPizza.setNombre(nombre);
        toppingPizza.setPrecio(precio);
        return iToppigPizzaRepository.save(toppingPizza);
    }

    public ToppingPizza updateToppingPizzaById(ToppingPizza toppingPizza1, Long id) {
        ToppingPizza toppingPizza = iToppigPizzaRepository.findById(id).get();
        toppingPizza.setNombre(toppingPizza1.getNombre());
        toppingPizza.setPrecio(toppingPizza1.getPrecio());
        return iToppigPizzaRepository.save(toppingPizza);
    }

    public List<ToppingPizza> getAllToppingPizza() {
        return iToppigPizzaRepository.findAll();
    }

    public ToppingPizza getToppingPizzaById(Long id){
        return iToppigPizzaRepository.findById(id).orElse(null);
    }
}
