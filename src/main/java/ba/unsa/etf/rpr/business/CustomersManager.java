package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

public class CustomersManager {
    private boolean customerExists(int id) throws CarsException {
       CustomersManager c = new CustomersManager();
        try {
            Customers cus = c.getById(id);
        } catch (CarsException f) {
            return false;
        }
        return true;
    }

    private Customers getById(int id) throws CarsException{
        return DaoFactory.customersDao().getById(id);
    }

    public Customers add(Customers c) throws CarsException {
        if (customerExists(c.getId())) throw new CarsException("Customers with such username already exists!");
        try {
            return DaoFactory.customersDao().add(c);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }
    public void delete(int cusId) throws CarsException {
        if (!customerExists(cusId)) throw new CarsException("Customer does not exist!");
        try {
            DaoFactory.customersDao().delete(cusId);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }
    public Customers update(Customers c) throws CarsException {
        return DaoFactory.customersDao().update(c);
    }
    public List<Customers> getAll() throws CarsException {
        return DaoFactory.customersDao().getAll();
    }
    public Customers getLoggedInCustomer(String username, String password) throws CarsException {
        return DaoFactory.customersDao().getLoggedInCustomer(username, password);
    }
  /*  public Customers addCustomer(Customers item) {
        return DaoFactory.customersDao().addCustomer(item);
    }*/
}

