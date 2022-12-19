package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Orders {
    private int id;
    private Date dateproccessed;
    private Date rentstart;
    private Date rentend;
    private int tank;
    private int mileagestart;
    private int mileageend;

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

    public int getTank() {
        return tank;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

    public int getMileagestart() {
        return mileagestart;
    }

    public void setMileagestart(int mileagestart) {
        this.mileagestart = mileagestart;
    }

    public int getMileageend() {
        return mileageend;
    }

    public void setMileageend(int mileageend) {
        this.mileageend = mileageend;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mileagestart);
    }

    public java.sql.Date getDateproccessed() {
        return (java.sql.Date) dateproccessed;
    }

    public void setDateproccessed(Date dateproccessed) {
        this.dateproccessed = dateproccessed;
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", dateproccessed=" + dateproccessed +
                ", rentstart=" + rentstart +
                ", rentend=" + rentend +
                ", tank=" + tank +
                ", mileagestart=" + mileagestart +
                ", mileageend=" + mileageend +
                ", car=" + car +
                ", customer=" + customer +
                '}';
    }
}
