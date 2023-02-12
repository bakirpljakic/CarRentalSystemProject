package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.util.List;

/**
 * The interface Cars dao.
 */
public interface CarsDao extends Dao<Cars>{

    /**
     * Gets all available.
     *
     * @return the all available
     * @throws CarsException the cars exception
     */
    List<Cars> getAllAvailable() throws CarsException;

    /**
     * Gets id.
     *
     * @param model    the model
     * @param marka    the marka
     * @param godiste  the godiste
     * @param cijena   the cijena
     * @param dostupno the dostupno
     * @return the id
     * @throws CarsException the cars exception
     */
//int getID(String marka, String model, int godiste, int cijena, boolean b);
    int getID(String model, String marka, Integer godiste,Integer cijena, boolean dostupno) throws CarsException;
}
