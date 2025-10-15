package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.bds02.dto.EventDTO;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
        EventDTO obj = service.update(id, dto);
        return ok(obj);
    }
}