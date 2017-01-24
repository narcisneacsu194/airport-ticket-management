package com.teamtreehouse.airport.dao;

import com.teamtreehouse.airport.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User>
implements UserDao{

    @Override
    public User findById(Long id){
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        Hibernate.initialize(user.getBookings());
        session.close();
        return user;
    }
}
