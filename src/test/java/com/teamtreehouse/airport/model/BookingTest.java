package com.teamtreehouse.airport.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
// This class has testing methods for the Booking entity.
// It mainly tests the getters and setters and the variable relative to those, verifying that the hard coded value is the same
// with what is eventually assigned to the variable.
import static org.junit.Assert.*;
public class BookingTest {
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
    public void theSetIdMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setId(5L);

        assertEquals((Long)5L, booking.getId());
    }

    @Test
    public void theSetPlaceOfDepartureMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setPlaceOfDeparture(place);

        assertEquals(place, booking.getPlaceOfDeparture());
    }

    @Test
    public void thePlaceOfDepartureSetIdMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setPlaceOfDeparture(place);
        place.setId(1L);

        assertEquals((Long)1L, booking.getPlaceOfDeparture().getId());
    }

    @Test
    public void thePlaceOfDepartureSetCityNameMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setPlaceOfDeparture(place);
        place.setCityName("DefaultCityName");

        assertEquals("DefaultCityName", booking.getPlaceOfDeparture().getCityName());
    }

    @Test
    public void thePlaceOfDepartureSetCountryNameMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setPlaceOfDeparture(place);
        place.setCountryName("DefaultCountryName");

        assertEquals("DefaultCountryName", booking.getPlaceOfDeparture().getCountryName());
    }

    @Test
    public void thePlaceOfDepartureSetDescriptionMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setPlaceOfDeparture(place);
        place.setDescription("DefaultDescription");

        assertEquals("DefaultDescription", booking.getPlaceOfDeparture().getDescription());
    }

    @Test
    public void thePlaceOfDepartureSetStartingPriceMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setPlaceOfDeparture(place);
        place.setStartingPrice(50);

        assertEquals((Integer)50, booking.getPlaceOfDeparture().getStartingPrice());
    }

    @Test
    public void theSetBookingsMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        List<Booking> bookings = new ArrayList<>();
        place.setBookings(bookings);
        bookings.add(booking);

        assertEquals(booking, place.getBookings().get(0));
    }

    @Test
    public void theSetDestinationMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setDestination(place);

        assertEquals(place, booking.getDestination());
    }

    @Test
    public void theDestinationSetIdMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setDestination(place);
        place.setId(1L);

        assertEquals((Long)1L, booking.getDestination().getId());
    }

    @Test
    public void theDestinationSetCityNameMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setDestination(place);
        place.setCityName("DefaultCityName");

        assertEquals("DefaultCityName", booking.getDestination().getCityName());
    }

    @Test
    public void theDestinationSetCountryNameMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setDestination(place);
        place.setCountryName("DefaultCountryName");

        assertEquals("DefaultCountryName", booking.getDestination().getCountryName());
    }

    @Test
    public void theDestinationSetDescriptionMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setDestination(place);
        place.setDescription("DefaultDescription");

        assertEquals("DefaultDescription", booking.getDestination().getDescription());
    }

    @Test
    public void theDestinationSetStartingPriceMethodCorrectlyAssignsValueForRelativePlaceVariable() throws Exception{
        booking.setDestination(place);
        place.setStartingPrice(50);

        assertEquals((Integer)50, booking.getDestination().getStartingPrice());
    }

    @Test
    public void theSetDepartureDateMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setDepartureDate(new Date(1485446370));

        assertEquals(0, booking.getDepartureDate().compareTo(new Date(1485446370)));
    }

    @Test
    public void theSetReturnDateMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setReturnDate(new Date(1485446370));
        assertEquals(0, booking.getReturnDate().compareTo(new Date(1485446370)));
    }

    @Test
    public void theSetNumberOfPassengersMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setNumberOfPassengers(5);

        assertEquals((Integer)5, booking.getNumberOfPassengers());
    }

    @Test
    public void theSetUserMethodCorrectlyAssignsValueForRelativeBookingVariable() throws Exception{
        booking.setUser(user);

        assertEquals(user, booking.getUser());
    }

    @Test
    public void theSetIdMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        booking.setUser(user);
        user.setId(1L);

        assertEquals((Long)1L, booking.getUser().getId());
    }

    @Test
    public void theSetNameMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        booking.setUser(user);
        user.setName("DefaultName");

        assertEquals("DefaultName", booking.getUser().getName());
    }

    @Test
    public void theSetCurrentCityMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        booking.setUser(user);
        user.setCurrentCity("DefaultCurrentCity");

        assertEquals("DefaultCurrentCity", booking.getUser().getCurrentCity());
    }

    @Test
    public void theSetBookingsMethodCorrectlyAssignsValueForRelativeUserVariable() throws Exception{
        List<Booking> bookings = new ArrayList<>();
        user.setBookings(bookings);
        bookings.add(booking);

        assertEquals(booking, user.getBookings().get(0));
    }
}
