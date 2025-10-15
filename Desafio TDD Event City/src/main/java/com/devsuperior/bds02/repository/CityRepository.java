package com.devsuperior.bds02.repository;

import com.devsuperior.bds02.dto.CityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devsuperior.bds02.entities.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("""
           SELECT new com.devsuperior.bds02.dto.CityDTO(c.id, c.name)
           FROM City c
           ORDER BY lower(c.name)
           """)
    List<CityDTO> findAllDTOOrderByNameAscIgnoreCase();
}