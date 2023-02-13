package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CustomersManagerTest {
    CustomersManager cm = new CustomersManager();



    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getAllTest() throws CarsException {
        List<Customers> korisnik = cm.getAll();
        Assertions.assertEquals("Bakir", korisnik.get(0).getFullname());
    }

    @Test
    void getLoggedInCustomerTest() throws CarsException {
        Assertions.assertEquals(null, cm.getLoggedInCustomer("ne-postoji", "ne-postoji"));
    }
}