package com.devsuperior.bds02.service.imp;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exception.DatabaseException;
import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.service.CityService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CityDTO> findAllSortedByName() {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream()
                .map(CityDTO::new)
                .collect(toList());
    }

    @Transactional
    @Override
    public CityDTO insert(CityDTO dto) {
        City entity = new City(dto.name());
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("City id not found " + id);
        }
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
