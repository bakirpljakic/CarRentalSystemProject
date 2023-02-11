package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

public interface Dao<T>{
    T getById(int id) throws CarsException;
    T add(T item) throws CarsException;
    T update(T item) throws  CarsException;
    void delete(int id) throws CarsException;
    List<T> getAll() throws CarsException;
}
