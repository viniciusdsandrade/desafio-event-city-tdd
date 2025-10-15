package com.devsuperior.bds02.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.devsuperior.bds02.entities.Event;

public record EventDTO(
        Long id,
        String name,
        LocalDate date,
        String url,
        Long cityId
) implements Serializable {
    public EventDTO(Event entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getDate(),
                entity.getUrl(),
                entity.getCity().getId()
        );
    }
}
