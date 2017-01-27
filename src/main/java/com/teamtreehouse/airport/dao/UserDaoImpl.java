package com.teamtreehouse.airport.dao;

import com.teamtreehouse.airport.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User>
implements UserDao{

    // This particular method had to be overridden, in order to include the Hibernate initialize static method.
    // This method helps prevent data fetching runtime errors.
    @Override
    public User findById(Long id){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        Hibernate.initialize(user.getBookings());
        session.close();
        return user;
    }
}
