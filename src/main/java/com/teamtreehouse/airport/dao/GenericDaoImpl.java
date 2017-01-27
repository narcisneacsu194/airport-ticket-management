package com.teamtreehouse.airport.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
// This abstract class implements all the GenericDao interface methods.
// These are methods that appear inside a database session, like save, delete, find all entries or a specific one.
public abstract class GenericDaoImpl<T> implements GenericDao<T>{
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<? extends T> daoType;

    // This constructor gets the exact entity class that was passed, like Booking, Place or User.
    @SuppressWarnings("unchecked")
    public GenericDaoImpl(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType)type;
        daoType = (Class)parameterizedType.getActualTypeArguments()[0];
    }

    // This method returns a list of all entries of a specific entity, like Booking, Place or User in this case.
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(){
        Session session = sessionFactory.openSession();
        List<T> objects = session.createCriteria(daoType).list();
        session.close();
        return objects;
    }

    // This method returns a specific Booking, Place or User entry from the database.
    @Override
    public T findById(Long id){
        Session session = sessionFactory.openSession();
        T object = session.get(daoType, id);
        session.close();
        return object;
    }

    // This method saves or updates a passed Booking, Place or User entity within the database.
    @Override
    public void save(T object){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.close();
    }

    // This method deletes a specific Booking, Place or User entry from teh database.
    @Override
    public void delete(T object){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
}
