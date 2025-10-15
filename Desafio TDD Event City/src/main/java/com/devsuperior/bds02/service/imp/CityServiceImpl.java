package com.devsuperior.bds02.service.imp;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exception.DatabaseException;
import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.service.CityService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CityDTO> findAllSortedByName() {
        return cityRepository.findAllDTOOrderByNameAscIgnoreCase();
    }

    @Override
    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City(dto.name());
        cityRepository.save(entity);
        return new CityDTO(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!cityRepository.existsById(id)) throw new ResourceNotFoundException("City id not found " + id);

        try {
            cityRepository.deleteById(id);
            cityRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
