package com.teamtreehouse.airport.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
// This class has testing methods for the User entity.
// It mainly tests the getters and setters and the variable relative to those, verifying that the hard coded value is the same
// with what is eventually assigned to the variable.
public class UserTest {
    User user;
    Booking booking;
    Place place;

    @Before
    public void setUp() throws Exception{
        user = new User();
        booking = new Booking();
        place = new Place();
    }

    @Test
    public void theSetIdMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        user.setId(1L);

        assertEquals((Long)1L, user.getId());
    }

    @Test
    public void theSetNameMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        user.setName("DefaultName");

        assertEquals("DefaultName", user.getName());
    }

    @Test
    public void theSetCurrentCityMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        user.setCurrentCity("DefaultCurrentCity");

        assertEquals("DefaultCurrentCity", user.getCurrentCity());
    }

    @Test
    public void theSetBookingsMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        List<Booking> bookings = new ArrayList<>();
        user.setBookings(bookings);
        bookings.add(booking);

        assertEquals(booking, user.getBookings().get(0));
    }
}
