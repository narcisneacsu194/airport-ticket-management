package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.dao.PlaceDao;
import com.teamtreehouse.airport.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
