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
    void getAll() {
    }

    @Test
    void getLoggedInCustomerTest() throws CarsException {
        Assertions.assertEquals(null, cm.getLoggedInCustomer("ne-postoji", "ne-postoji"));
    }
}