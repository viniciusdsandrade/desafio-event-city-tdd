package com.devsuperior.bds02.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private String url;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Event() {
    }

    public Event(Long id, String name, LocalDate date, String url, City city) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.url = url;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public City getCity() {
        return city;
    }
}
