package com.teamtreehouse.airport.dao;

import com.teamtreehouse.airport.model.Booking;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDaoImpl extends GenericDaoImpl<Booking>
implements BookingDao{

    @Override
    public Booking findById(Long id){
        Session session = sessionFactory.openSession();
        Booking booking = session.get(Booking.class, id);
        Hibernate.initialize(booking.getTrips());
        Hibernate.initialize(booking.getPassengers());
        session.close();
        return booking;
    }
}
