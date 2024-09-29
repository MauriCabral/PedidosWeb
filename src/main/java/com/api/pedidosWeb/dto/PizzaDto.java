package com.api.pedidosWeb.dto;

import com.api.pedidosWeb.model.DetalleToppingPizza;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PizzaDto {

    private String nombre;
    private String descripcion;
    private List<DetalleToppingPizza> detalleToppingPizzas;
    private int precio_total;
    private String imagen;
}
