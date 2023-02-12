package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.CarsException;
import org.junit.jupiter.api.Test;

class CarsManagerTest {

    CarsManager cm = new CarsManager();
    CustomersManager cusm = new CustomersManager();


    @Test
    void addCarTest() throws CarsException {
        Cars c = new Cars();
        c.setMake("Test");
        cm.add(c);
        List<Cars> cars = cm.getAll();
        boolean dodan = false;
        for(Cars ca: cars){
            if(c.equals(ca)) dodan = true;
        }
        Assertions.assertTrue(dodan);
        cm.delete(c.getId());
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
    void getAllAvailable() {
    }

    @Test
    void getById() {
    }

    @Test
    void getID() {
    }
}