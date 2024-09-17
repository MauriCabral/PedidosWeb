package com.api.pedidosWeb.repository;

import com.api.pedidosWeb.model.ToppingPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IToppigPizzaRepository extends JpaRepository<ToppingPizza, Long> {
}
