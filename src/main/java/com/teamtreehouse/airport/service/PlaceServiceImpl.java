package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.dao.PlaceDao;
import com.teamtreehouse.airport.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
// This class implements the methods from the BookingService interface,
// which simply call their relative BookingDao methods.
@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceDao placeDao;

    @Override
    public List<Place> findAll() {
        return placeDao.findAll();
    }

    @Override
    public Place findById(Long id) {
        return placeDao.findById(id);
    }

    @Override
    public void save(Place place) {
        placeDao.save(place);
    }

    @Override
    public void delete(Place place) {
        placeDao.delete(place);
    }
}
