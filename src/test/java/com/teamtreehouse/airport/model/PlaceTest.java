package com.teamtreehouse.airport.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
// This class has testing methods for the Place entity.
// It mainly tests the getters and setters and the variable relative to those, verifying that the hard coded value is the same
// with what is eventually assigned to the variable.
public class PlaceTest {
    Booking booking;
    User user;
    Place place;

    @Before
    public void setUp() throws Exception{
        booking = new Booking();
        user = new User();
        place = new Place();
    }

    @Test
    public void theSetIdMethodCorrectlyAssignsValueForTheRelativePlaceVariable() throws Exception{
        place.setId(1L);

        assertEquals((Long)1L, place.getId());
    }

    @Test
    public void theSetCityNameMethodCorrectlyAssignsValueForTheRelativePlaceVariable() throws Exception{
        place.setCityName("DefaultCityName");

        assertEquals("DefaultCityName", place.getCityName());
    }

    @Test
    public void theSetCountryNameMethodCorrectlyAssignsValueForTheRelativePlaceVariable() throws Exception{
        place.setCountryName("DefaultCountryName");

        assertEquals("DefaultCountryName", place.getCountryName());
    }

    @Test
    public void theSetDescriptionMethodCorrectlyAssignsValueForTheRelativePlaceVariable() throws Exception{
        place.setDescription("DefaultDescription");

        assertEquals("DefaultDescription", place.getDescription());
    }

    @Test
    public void theSetStartingPriceMethodCorrectlyAssignsValueForTheRelativePlaceVariable() throws Exception{
        place.setStartingPrice(50);

        assertEquals((Integer)50, place.getStartingPrice());
    }

    @Test
    public void theSetBookingsMethodCorrectlyAssignsValueForTheRelativePlaceVariable() throws Exception{
        List<Booking> bookings = new ArrayList<>();
        place.setBookings(bookings);
        bookings.add(booking);

        assertEquals(booking, place.getBookings().get(0));
    }

}
