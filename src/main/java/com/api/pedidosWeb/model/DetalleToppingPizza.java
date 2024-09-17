package com.api.pedidosWeb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DetalleToppingPizza")
public class DetalleToppingPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_topping;

    @ManyToOne
    @JoinColumn(name = "id_pizza", nullable = false)
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "id_topping_pizza", nullable = false)
    private ToppingPizza topping;

    @Column
    private int cantidad;
}
