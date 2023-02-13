package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    void getAllAvailableTest() throws CarsException {
        Cars c = new Cars();
        List<Cars> cars = cm.getAllAvailable();
        boolean dostupan = true;
        for (Cars ca :cars) {
            if (!ca.isAvailable()) dostupan = false;
        }
        Assertions.assertTrue(dostupan);
    }

    @Test
    void getByIdTest() throws CarsException {
        Cars c = new Cars();
        c= new Cars(25,"Seat", "Leon,", 2009, 15000, true);
        Cars finalC = c;
        Assertions.assertThrows(CarsException.class, ()->{
            cm.add(finalC);
        });
    }

    @Test
    public void mockitoTestAvailabe() throws CarsException{
        CarsManager mockM = Mockito.mock(CarsManager.class);
        Mockito.when(mockM.getById(0)).thenReturn(new Cars(10,"mockito", "mockito",   2010, 22000, false));
        Assertions.assertEquals(false, mockM.getById(0).isAvailable());
    }

    @Test
    public  void mockitoTestMake() throws  CarsException{
        CarsManager mockM = Mockito.mock(CarsManager.class);
        Mockito.when(mockM.getById(0)).thenReturn(new Cars(10,"mockito", "mockito",   2010, 22000, false));
        Assertions.assertEquals("mockito", mockM.getById(0).getMake());
    }


}