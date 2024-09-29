package com.api.pedidosWeb.service;

import com.api.pedidosWeb.model.ToppingPizza;

import java.util.List;

public interface IToppingPizzaService {

    ToppingPizza create(ToppingPizza toppingPizza);

    ToppingPizza updateById(ToppingPizza toppingPizza, Long id);

    List<ToppingPizza> getAll();

    ToppingPizza getById(Long id);
}