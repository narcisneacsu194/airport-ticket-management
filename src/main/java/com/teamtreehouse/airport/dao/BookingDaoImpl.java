package com.teamtreehouse.airport.dao;

import com.teamtreehouse.airport.model.Booking;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDaoImpl extends GenericDaoImpl<Booking>
implements BookingDao{

}
