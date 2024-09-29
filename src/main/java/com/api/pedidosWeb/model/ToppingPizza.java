package com.api.pedidosWeb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ToppingPizza")
public class ToppingPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_topping_pizza;

    @Column
    private String nombre;

    @Column
    private int precio;

    @OneToMany(mappedBy = "topping")
    private List<DetalleToppingPizza> detalle_toppings;
}
