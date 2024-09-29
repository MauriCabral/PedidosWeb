package com.api.pedidosWeb.service.serviceImplementation;

import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.repository.IPizzaRepository;
import com.api.pedidosWeb.service.IPizzaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImp implements IPizzaService {

    @Autowired
    private IPizzaRepository iPizzaRepository;

    @Override
    public Pizza create(String nombre, String descripcion, int precio, String imagen) {
        Pizza pizza = new Pizza();
        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio_total(precio);
        pizza.setImagen(imagen);
        return iPizzaRepository.save(pizza);
    }

    @Override
    public Pizza updateById(Pizza pizza, Long id, String imagePath) {
        Pizza existingPizza = iPizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza not found with ID: " + id));
        existingPizza.setNombre(pizza.getNombre());
        existingPizza.setDescripcion(pizza.getDescripcion());
        existingPizza.setPrecio_total(pizza.getPrecio_total());
        if (imagePath != null) {
            existingPizza.setImagen(imagePath);
        }

        return iPizzaRepository.save(existingPizza);
    }

    @Override
    public List<Pizza> getAll() {
        return iPizzaRepository.findAll();
    }

    @Override
    public Pizza getById(Long id) {
        return iPizzaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pizza not found with ID: " + id));
    }

    @Override
    public String getFileById(Long id) {
        Pizza pizza = iPizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza not found with ID: " + id));
        return pizza.getImagen();
    }
}