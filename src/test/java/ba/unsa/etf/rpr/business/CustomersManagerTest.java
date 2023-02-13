package ba.unsa.etf.rpr.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomersManagerTest {

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

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