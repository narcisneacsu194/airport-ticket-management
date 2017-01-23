package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();
    Trip findById(Long id);
    void save(Trip trip);
    void delete(Trip trip);
}
