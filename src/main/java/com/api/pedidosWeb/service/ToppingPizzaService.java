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

    public ToppingPizza createToppingPizza(ToppingPizza toppingPizza){
        return iToppigPizzaRepository.save(toppingPizza);
    }

    public ToppingPizza updateToppingPizzaById(ToppingPizza toppingPizza, Long id) {
        ToppingPizza toppingPizzaUpdate = iToppigPizzaRepository.findById(id).get();
        toppingPizzaUpdate.setNombre(toppingPizza.getNombre());
        toppingPizzaUpdate.setPrecio(toppingPizza.getPrecio());
        return iToppigPizzaRepository.save(toppingPizzaUpdate);
    }

    public List<ToppingPizza> getAllToppingPizza() {
        return iToppigPizzaRepository.findAll();
    }

    public ToppingPizza getToppingPizzaById(Long id){
        return iToppigPizzaRepository.findById(id).orElse(null);
    }
}
