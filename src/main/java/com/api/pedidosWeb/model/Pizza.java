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
@Table(name = "Pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pizza;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleToppingPizza> detalleToppingPizzas;

    @Column
    private int precio_total;

    @Column
    private String imagen;
}
