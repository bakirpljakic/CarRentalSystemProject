package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Customers implements Idable {
    private int id;
    private String fullname;
    private int drivinglicence;
    private String adress;
    private String country;
    private String city;

    private Cars car;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getDrivinglicence() {
        return drivinglicence;
    }

    public void setDrivinglicence(int drivinglicence) {
        this.drivinglicence = drivinglicence;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return id == customers.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, drivinglicence, adress, country, city);
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", drivinglicence=" + drivinglicence +
                ", adress='" + adress + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", car=" + car +
                '}';
    }
}
