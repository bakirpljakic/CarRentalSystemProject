package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

public interface CarsDao extends Dao<Cars>{

    List<Cars> getAllAvailable() throws CarsException;

    //int getID(String marka, String model, int godiste, int cijena, boolean b);
    int getID(String model, String marka, Integer godiste,Integer cijena, boolean dostupno) throws CarsException;
}
