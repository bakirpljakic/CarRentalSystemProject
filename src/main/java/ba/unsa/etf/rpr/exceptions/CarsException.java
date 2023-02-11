package ba.unsa.etf.rpr.exceptions;

public class CarsException extends Exception{
    public CarsException(String m, Exception e){super(m,e);}
    public CarsException(String m){super(m);}
}
