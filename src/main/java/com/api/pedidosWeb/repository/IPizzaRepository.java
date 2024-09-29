package com.api.pedidosWeb.repository;

import com.api.pedidosWeb.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPizzaRepository extends JpaRepository<Pizza, Long> {
}
