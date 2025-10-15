package com.devsuperior.bds02.service.imp;

import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.repository.EventRepository;
import com.devsuperior.bds02.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    private final CityRepository cityRepository;

    public EventServiceImpl(EventRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    @Override
    public EventDTO update(Long id, EventDTO dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event id not found " + id));

        var city = cityRepository.findById(dto.cityId())
                .orElseThrow(() -> new ResourceNotFoundException("City id not found " + dto.cityId()));

        entity = new Event(
                id,
                dto.name(),
                dto.date(),
                dto.url(),
                city
        );

        entity = repository.save(entity);
        return new EventDTO(entity);
    }
}