package com.api.pedidosWeb.mapper;

import com.api.pedidosWeb.dto.PizzaDto;
import com.api.pedidosWeb.model.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaMapper extends AbstractMapper<Pizza, PizzaDto> {

    @Override
    public PizzaDto fromEntity(Pizza entity) {
        if (entity == null) return null;
        return PizzaDto.builder()
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .detalleToppingPizzas(entity.getDetalleToppingPizzas())
                .precio_total(entity.getPrecio_total())
                .imagen(entity.getImagen())
                .build();
    }

    @Override
    public Pizza fromDto(PizzaDto dto) {
        if (dto == null) return null;
        return Pizza.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .detalleToppingPizzas(dto.getDetalleToppingPizzas())
                .precio_total(dto.getPrecio_total())
                .imagen(dto.getImagen())
                .build();
    }
}
