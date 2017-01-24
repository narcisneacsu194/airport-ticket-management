package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.dao.PassengerDao;
import com.teamtreehouse.airport.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassengerServiceImpl implements PassengerService{

    @Autowired
    private PassengerDao passengerDao;

    @Override
    public List<Passenger> findAll() {
        return passengerDao.findAll();
    }

    @Override
    public Passenger findById(Long id) {
        return passengerDao.findById(id);
    }

    @Override
    public void save(Passenger passenger) {
        passengerDao.save(passenger);
    }

    @Override
    public void delete(Passenger passenger) {
        passengerDao.delete(passenger);
    }
}
