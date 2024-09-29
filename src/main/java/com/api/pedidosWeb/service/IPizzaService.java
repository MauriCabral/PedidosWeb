package com.api.pedidosWeb.service;

import com.api.pedidosWeb.model.Pizza;

import java.util.List;

public interface IPizzaService {

    Pizza create(String nombre, String descripcion, int precio, String imagen);

    Pizza updateById(Pizza pizza, Long id, String imagePath);

    List<Pizza> getAll();

    Pizza getById(Long id);

    String getFileById(Long id);
}
