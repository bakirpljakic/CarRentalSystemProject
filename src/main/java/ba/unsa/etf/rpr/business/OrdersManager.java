package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

public class OrdersManager {

    public Orders add(Orders o) throws CarsException {
        try {
            return DaoFactory.ordersDao().add(o);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }
    public void delete(int orderId) throws CarsException {
        try {
            DaoFactory.ordersDao().delete(orderId);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }
        public Orders update(Orders o) throws CarsException {
            return DaoFactory.ordersDao().update(o);
        }

    public List<Orders> getAll() throws CarsException {
        return DaoFactory.ordersDao().getAll();
    }
    public Orders getById(int id) throws CarsException {
        return DaoFactory.ordersDao().getById(id);
    }

}
