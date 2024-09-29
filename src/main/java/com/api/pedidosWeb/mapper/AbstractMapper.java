package com.api.pedidosWeb.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper <Ent, Dto> {

    public abstract Dto fromEntity(Ent entity);
    public abstract Ent fromDto(Dto dto);

    public List<Dto> fromEntity(List<Ent> entitys) {
        return entitys.stream().map(e-> fromEntity(e)).collect(Collectors.toList());
    }

    public List<Ent> fromDto(List<Dto> dtos) {
        return dtos.stream().map(e-> fromDto(e)).collect(Collectors.toList());
    }
}
