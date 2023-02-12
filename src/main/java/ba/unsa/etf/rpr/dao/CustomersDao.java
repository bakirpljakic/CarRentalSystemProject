package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;

/**
 * The interface Customers dao.
 */
public interface CustomersDao extends Dao<Customers>{
    //public Customers addCustomer(Customers customer);

    /**
     * Gets logged in customer.
     *
     * @param username the username
     * @param password the password
     * @return the logged in customer
     * @throws CarsException the cars exception
     */
    public Customers getLoggedInCustomer(String username, String password) throws CarsException;

    /**
     * Provjeri korisnika boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean provjeriKorisnika(String username);

}
