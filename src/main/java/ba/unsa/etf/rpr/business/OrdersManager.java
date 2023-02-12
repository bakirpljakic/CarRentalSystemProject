package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

/**
 * The type Orders manager.
 */
public class OrdersManager {

    /**
     * Add orders.
     *
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
     *
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
