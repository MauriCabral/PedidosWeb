package com.api.pedidosWeb.service;

import com.api.pedidosWeb.model.Pizza;
import com.api.pedidosWeb.repository.IPizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Component
public class PizzaService {

//    @Autowired
    private final IPizzaRepository iPizzaRepository;

    public PizzaService(IPizzaRepository iPizzaRepository) {
        this.iPizzaRepository = iPizzaRepository;
    }

    public Pizza savePizza(String nombre, String descripcion, int precio, String imagen) {
        Pizza pizza = new Pizza();
        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio_total(precio);
        pizza.setImagen(imagen);
        return iPizzaRepository.save(pizza);
    }

    public Pizza updatePizzaById(Pizza pizza, Long id, String imagePath) {
        Pizza existingPizza = iPizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza no encontrada con el ID: " + id));
        existingPizza.setNombre(pizza.getNombre());
        existingPizza.setDescripcion(pizza.getDescripcion());
        existingPizza.setPrecio_total(pizza.getPrecio_total());
        if (imagePath != null) {
            existingPizza.setImagen(imagePath);
        }

        return iPizzaRepository.save(existingPizza);
    }

    public List<Pizza> getAllPizza() {
        return iPizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id) {
        return iPizzaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pizza no encontrada con el ID: " + id));
    }

    public String getExistingFilenameById(Long id) {
        Pizza pizza = iPizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza no encontrada con el ID: " + id));
        return pizza.getImagen();
    }

}
