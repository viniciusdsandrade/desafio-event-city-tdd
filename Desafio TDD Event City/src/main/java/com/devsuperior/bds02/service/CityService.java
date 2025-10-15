package com.devsuperior.bds02.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;

public interface CityService {

    @Transactional(readOnly = true)
    List<CityDTO> findAllSortedByName();

    @Transactional
    CityDTO insert(CityDTO dto);

    @Transactional
    void delete(Long id);
}