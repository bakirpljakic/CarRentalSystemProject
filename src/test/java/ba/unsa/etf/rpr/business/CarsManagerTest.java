package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void deleteCarTest() throws CarsException {
        Cars c = new Cars();
        c.setMake("Test");
        cm.add(c);
        cm.delete(c.getId());
        List<Cars> cars = cm.getAll();
        boolean obrisan = true;
        for (Cars ca :cars) {
            if (c.equals(ca)) obrisan = false;
        }
        Assertions.assertTrue(obrisan);
    }
    @Test
    void addCarWithIDTest() {
        Cars c = new Cars();
        c.setId(7);
        c.setMake("Test");
        Assertions.assertThrows(CarsException.class, ()->{
            cm.add(c);
        });
    }

    @Test
    void getAllTest() throws CarsException {
        List<Cars> auto = cm.getAll();
        Assertions.assertEquals("X5", auto.get(3).getModel());
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