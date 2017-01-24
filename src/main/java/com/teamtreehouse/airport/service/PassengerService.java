package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAll();
    Passenger findById(Long id);
    void save(Passenger passenger);
    void delete(Passenger passenger);
}
