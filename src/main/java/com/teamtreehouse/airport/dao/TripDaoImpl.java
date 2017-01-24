package com.teamtreehouse.airport.dao;

import com.teamtreehouse.airport.model.Trip;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class TripDaoImpl extends GenericDaoImpl<Trip>
implements TripDao{

    @Override
    public Trip findById(Long id){
        Session session = sessionFactory.openSession();
        Trip trip = session.get(Trip.class, id);
        Hibernate.initialize(trip.getBookings());
        session.close();
        return trip;
    }
}
