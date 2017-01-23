package com.teamtreehouse.airport.service;

import com.teamtreehouse.airport.dao.BookingDao;
import com.teamtreehouse.airport.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingDao bookingDao;

    @Override
    public List<Booking> findAll() {
        return bookingDao.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingDao.findById(id);
    }

    @Override
    public void save(Booking booking) {
        bookingDao.save(booking);
    }

    @Override
    public void delete(Booking booking) {
        bookingDao.delete(booking);
    }
}
