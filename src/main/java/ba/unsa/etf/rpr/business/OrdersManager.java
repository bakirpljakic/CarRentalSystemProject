package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

/**
 * This is a Java class called "OrdersManager" that provides a set of methods for managing Orders objects.
 * The class uses the DaoFactory to access the Orders DAO (Data Access Object) and perform CRUD (Create, Read, Update, Delete) operations on Orders objects.
 * It also throws a CarsException in case of an error.
 * @author Bakir Pljakic
 */
public class OrdersManager {

    /**
     * Add orders.
     * Add Orders object to table Orders.
     * @param o the o
     * @return the orders
     * @throws CarsException the cars exception
     */
    public Orders add(Orders o) throws CarsException {
        try {
            return DaoFactory.ordersDao().add(o);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    /**
     * Delete.
     * Delete orders, from db table Orders, with a given id.
     * @param orderId the order id
     * @throws CarsException the cars exception
     */
    public void delete(int orderId) throws CarsException {
        try {
            DaoFactory.ordersDao().delete(orderId);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    /**
     * Update orders.
     *
     * @param o the o
     * @return the orders
     * @throws CarsException the cars exception
     */
    public Orders update(Orders o) throws CarsException {
            return DaoFactory.ordersDao().update(o);
        }

    /**
     * Gets all.
     *
     * @return the all
     * @throws CarsException the cars exception
     */
    public List<Orders> getAll() throws CarsException {
        return DaoFactory.ordersDao().getAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws CarsException the cars exception
     */
    public Orders getById(int id) throws CarsException {
        return DaoFactory.ordersDao().getById(id);
    }

}
