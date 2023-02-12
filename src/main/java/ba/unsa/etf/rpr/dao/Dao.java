package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao<T>{
    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws CarsException the cars exception
     */
    T getById(int id) throws CarsException;

    /**
     * Add t.
     *
     * @param item the item
     * @return the t
     * @throws CarsException the cars exception
     */
    T add(T item) throws CarsException;

    /**
     * Update t.
     *
     * @param item the item
     * @return the t
     * @throws CarsException the cars exception
     */
    T update(T item) throws  CarsException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws CarsException the cars exception
     */
    void delete(int id) throws CarsException;

    /**
     * Gets all.
     *
     * @return the all
     * @throws CarsException the cars exception
     */
    List<T> getAll() throws CarsException;
}
