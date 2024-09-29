package com.api.pedidosWeb.service.serviceImplementation;

import com.api.pedidosWeb.model.ToppingPizza;
import com.api.pedidosWeb.repository.IToppigPizzaRepository;
import com.api.pedidosWeb.service.IToppingPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingPizzaServiceImp implements IToppingPizzaService {

    @Autowired
    private IToppigPizzaRepository iToppigPizzaRepository;

    @Override
    public ToppingPizza create(ToppingPizza toppingPizza){
        return iToppigPizzaRepository.save(toppingPizza);
    }

    @Override
    public ToppingPizza updateById(ToppingPizza toppingPizza, Long id) {
        ToppingPizza toppingPizzaUpdate = iToppigPizzaRepository.findById(id).get();
        toppingPizzaUpdate.setNombre(toppingPizza.getNombre());
        toppingPizzaUpdate.setPrecio(toppingPizza.getPrecio());
        return iToppigPizzaRepository.save(toppingPizzaUpdate);
    }

    @Override
    public List<ToppingPizza> getAll() {
        return iToppigPizzaRepository.findAll();
    }

    @Override
    public ToppingPizza getById(Long id){
        return iToppigPizzaRepository.findById(id).orElse(null);
    }
}
