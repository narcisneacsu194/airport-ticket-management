package com.teamtreehouse.airport.loader;

import com.teamtreehouse.airport.dao.BookingDao;
import com.teamtreehouse.airport.dao.PlaceDao;
import com.teamtreehouse.airport.dao.UserDao;
import com.teamtreehouse.airport.model.Booking;
import com.teamtreehouse.airport.model.Place;
import com.teamtreehouse.airport.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner{

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for(int i = 1;i <= 5;i++){
            Place place = new Place();
            place.setCityName("city" + i);
            place.setCountryName("country" + i);
            place.setDescription("description" + i);
            place.setStartingPrice(i);
            placeDao.save(place);

            User user = new User();
            user.setName("User" + i);
            user.setCurrentCity("current_city" + i);
            userDao.save(user);

            for(int j = 1;j <= 5;j++){
                Booking booking = new Booking();
                booking.setPlaceOfDeparture("place_of_departure" + j);
                booking.setDestination("destination" + j);
                booking.setDepartureDate(new Date());
                booking.setReturnDate(new Date());
                booking.setNumberOfPassengers(j);
                booking.setUser(user);
                bookingDao.save(booking);
            }
        }
    }
}
