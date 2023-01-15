package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;

import java.util.List;

public interface CustomersDao extends Dao<Customers>{
    public Customers addCustomer(Customers customer);

    public Customers getLoggedInCustomer(String username, String password);


}
