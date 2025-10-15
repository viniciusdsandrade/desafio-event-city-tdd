package com.devsuperior.bds02.controller;


import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        List<CityDTO> list = service.findAllSortedByName();
        return ok(list);
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto) {
        CityDTO saved = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();
        return created(uri).body(saved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return noContent().build();
    }
}

