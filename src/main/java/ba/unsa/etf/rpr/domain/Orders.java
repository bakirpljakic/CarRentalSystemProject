package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Orders implements Idable {
    private int id;

    private Date rentstart;
    private Date rentend;

    private int totalprice;

    private Cars car;

    private Customers customer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getRentstart() {
        return (java.sql.Date) rentstart;
    }

    public void setRentstart(Date rentstart) {
        this.rentstart = rentstart;
    }

    public java.sql.Date getRentend() {
        return (java.sql.Date) rentend;
    }

    public void setRentend(Date rentend) {
        this.rentend = rentend;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id;
    }



    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentstart, rentend, totalprice, car, customer);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", rentstart=" + rentstart +
                ", rentend=" + rentend +
                ", totalprice=" + totalprice +
                ", car=" + car +
                ", customer=" + customer +
                '}';
    }
}
