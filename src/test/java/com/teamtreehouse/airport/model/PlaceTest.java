package com.teamtreehouse.airport.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
