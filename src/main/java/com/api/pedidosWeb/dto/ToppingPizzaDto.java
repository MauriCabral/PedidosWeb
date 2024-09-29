package com.api.pedidosWeb.dto;

import com.api.pedidosWeb.model.DetalleToppingPizza;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ToppingPizzaDto {

    private String nombre;
    private int precio;
}
