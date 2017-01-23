package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.model.Place;

import java.util.List;

public interface PlaceService {
    List<Place> findAll();
    Place findById(Long id);
    void save(Place place);
    void delete(Place place);
}
