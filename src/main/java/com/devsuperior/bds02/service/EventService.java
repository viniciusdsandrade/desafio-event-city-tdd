package com.devsuperior.bds02.service;

import com.devsuperior.bds02.dto.EventDTO;
import org.springframework.transaction.annotation.Transactional;

public interface EventService {

    @Transactional
    EventDTO update(Long id, EventDTO dto);
}
