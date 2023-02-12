package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Cars implements Idable {
    private int id;
    private String make;
    private  String model;
    private int CarYear;

    private int price;
    private boolean available;



    public Cars( int id, String make, String model, int year, int price, boolean available) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.CarYear = year;
        this.price = price;
        this.available = available;
    }
    public Cars(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCarYear() {
        return CarYear;
    }

    public void setCarYear(int year) {
        this.CarYear = year;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

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
