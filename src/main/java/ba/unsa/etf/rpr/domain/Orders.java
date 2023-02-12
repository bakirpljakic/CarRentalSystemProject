package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * The type Orders.
 */
public class Orders implements Idable {
    private int id;



    private Date saledate;


    private int totalprice;

    private Cars car;

    private Customers customer;

    /**
     * Instantiates a new Orders.
     *
     * @param id         the id
     * @param saledate   the saledate
     * @param totalprice the totalprice
     * @param car        the car
     * @param customer   the customer
     */
    public Orders(int id, Date saledate, int totalprice, Cars car, Customers customer) {
        this.id = id;
        this.saledate = saledate;
        this.totalprice = totalprice;
        this.car = car;
        this.customer = customer;
    }

    /**
     * Instantiates a new Orders.
     */
    public Orders(){

    }


    /**
     * Gets saledate.
     *
     * @return the saledate
     */
    public Date getSaledate() {
        return saledate;
    }

    /**
     * Sets saledate.
     *
     * @param saledate the saledate
     */
    public void setSaledate(Date saledate) {
        this.saledate = saledate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id;
    }


    /**
     * Gets car.
     *
     * @return the car
     */
    public Cars getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Cars car) {
        this.car = car;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customers getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    /**
     * Gets totalprice.
     *
     * @return the totalprice
     */
    public int getTotalprice() {
        return totalprice;
    }

    /**
     * Sets totalprice.
     *
     * @param totalprice the totalprice
     */
    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saledate, totalprice, car, customer);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", saledate=" + saledate +
                ", totalprice=" + totalprice +
                ", car=" + car +
                ", customer=" + customer +
                '}';
    }
}
