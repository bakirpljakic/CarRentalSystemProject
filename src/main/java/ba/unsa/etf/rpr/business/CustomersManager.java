package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

/**
 * The type Customers manager.
 */
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

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws CarsException the cars exception
     */
    public Customers getById(int id) throws CarsException{
        return DaoFactory.customersDao().getById(id);
    }

    /**
     * Add customers.
     *
     * @param c the c
     * @return the customers
     * @throws CarsException the cars exception
     */
    public Customers add(Customers c) throws CarsException {
        if (customerExists(c.getId())) throw new CarsException("Customers with such username already exists!");
        try {
            return DaoFactory.customersDao().add(c);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    /**
     * Delete.
     *
     * @param cusId the cus id
     * @throws CarsException the cars exception
     */
    public void delete(int cusId) throws CarsException {
        if (!customerExists(cusId)) throw new CarsException("Customer does not exist!");
        try {
            DaoFactory.customersDao().delete(cusId);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    /**
     * Update customers.
     *
     * @param c the c
     * @return the customers
     * @throws CarsException the cars exception
     */
    public Customers update(Customers c) throws CarsException {
        return DaoFactory.customersDao().update(c);
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws CarsException the cars exception
     */
    public List<Customers> getAll() throws CarsException {
        return DaoFactory.customersDao().getAll();
    }

    /**
     * Gets logged in customer.
     *
     * @param username the username
     * @param password the password
     * @return the logged in customer
     * @throws CarsException the cars exception
     */
    public Customers getLoggedInCustomer(String username, String password) throws CarsException {
        return DaoFactory.customersDao().getLoggedInCustomer(username, password);
    }

}

