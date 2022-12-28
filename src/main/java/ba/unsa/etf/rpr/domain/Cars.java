package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Cars implements Idable {
    private int id;
    private String make;
    private  String model;
    private int year;

    private int price;
    private boolean available;


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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return Objects.hash(id, make, model, year, price, available);
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
