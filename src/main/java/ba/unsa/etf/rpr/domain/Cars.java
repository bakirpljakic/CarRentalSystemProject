package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * The type Cars.
 */
public class Cars implements Idable {
    private int id;
    private String make;
    private  String model;
    private int CarYear;

    private int price;
    private boolean available;


    /**
     * Instantiates a new Cars.
     *
     * @param id        the id
     * @param make      the make
     * @param model     the model
     * @param year      the year
     * @param price     the price
     * @param available the available
     */
    public Cars( int id, String make, String model, int year, int price, boolean available) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.CarYear = year;
        this.price = price;
        this.available = available;
    }

    /**
     * Instantiates a new Cars.
     */
    public Cars(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Gets make.
     *
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets make.
     *
     * @param make the make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets car year.
     *
     * @return the car year
     */
    public int getCarYear() {
        return CarYear;
    }

    /**
     * Sets car year.
     *
     * @param year the year
     */
    public void setCarYear(int year) {
        this.CarYear = year;
    }


    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return id == cars.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, CarYear, price, available);
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + CarYear +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
