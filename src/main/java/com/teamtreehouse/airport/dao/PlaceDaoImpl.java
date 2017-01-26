package com.teamtreehouse.airport.dao;

import com.teamtreehouse.airport.model.Place;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDaoImpl extends GenericDaoImpl<Place>
implements PlaceDao{
    @Override
    public Place findById(Long id){
        Session session = sessionFactory.openSession();
        Place place = session.get(Place.class, id);
        Hibernate.initialize(place.getBookings());
        session.close();
        return place;
    }
}
