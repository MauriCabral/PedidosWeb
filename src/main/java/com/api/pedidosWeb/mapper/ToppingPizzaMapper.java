package com.api.pedidosWeb.mapper;

import com.api.pedidosWeb.dto.ToppingPizzaDto;
import com.api.pedidosWeb.model.ToppingPizza;
import org.springframework.stereotype.Component;

@Component
public class ToppingPizzaMapper extends AbstractMapper <ToppingPizza, ToppingPizzaDto>{

    @Override
    public ToppingPizzaDto fromEntity(ToppingPizza entity) {
        if (entity == null) return null;
        return ToppingPizzaDto.builder()
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .build();
    }

    @Override
    public ToppingPizza fromDto(ToppingPizzaDto dto) {
        if (dto == null) return null;
        return ToppingPizza.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .build();
    }
}
