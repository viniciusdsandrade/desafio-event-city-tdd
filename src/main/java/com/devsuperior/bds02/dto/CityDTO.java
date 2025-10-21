package com.devsuperior.bds02.dto;

import com.devsuperior.bds02.entities.City;

public record CityDTO(Long id, String name) {
    public CityDTO(City entity) {
        this(entity.getId(), entity.getName());
    }
}
