package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.dao.TripDao;
import com.teamtreehouse.airport.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    @Autowired
    private TripDao tripDao;

    @Override
    public List<Trip> findAll() {
        return tripDao.findAll();
    }

    @Override
    public Trip findById(Long id) {
        return tripDao.findById(id);
    }

    @Override
    public void save(Trip trip) {
        tripDao.save(trip);
    }

    @Override
    public void delete(Trip trip) {
        tripDao.delete(trip);
    }
}
