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

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.by;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDTO> findAllSortedByName() {
        List<City> list = cityRepository.findAll(by(asc("name").ignoreCase()));
        return list.stream()
                .map(CityDTO::new)
                .collect(toList());
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
