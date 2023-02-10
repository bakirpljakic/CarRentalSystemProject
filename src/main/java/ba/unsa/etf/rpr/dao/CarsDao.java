package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Cars;

import java.util.List;

public interface CarsDao extends Dao<Cars>{

    List<Cars> getAllAvailable();
}
