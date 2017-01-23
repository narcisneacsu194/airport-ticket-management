package com.teamtreehouse.airport.dao;

import java.util.List;

public interface GenericDao<T>{
    List<T> findAll();

    T findById(Long id);

    void save(T object);

    void delete(T object);
}
