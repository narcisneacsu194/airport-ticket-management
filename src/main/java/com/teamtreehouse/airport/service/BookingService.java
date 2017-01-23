package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    Booking findById(Long id);
    void save(Booking booking);
    void delete(Booking booking);
}
