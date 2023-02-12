package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Customers extends Cars implements Idable {
    private int id;
    private String fullname;
    private String drivinglicence;
    private String adress;
    private String mail;
    private String city;

    private String password;

    private boolean admin;
    private Cars car;

    public Customers(int i, String ime, String vozacka, String adresa, String email, String grad, boolean admin, String password) {
        this.id = i;
        this.fullname = ime;
        this.drivinglicence = vozacka;
        this.adress = adresa;
        this.mail = email;
        this.city= grad;
        this.admin = admin;
        this.password = password;
    }

    public Customers() {

    }


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

    public String getDrivinglicence() {
        return drivinglicence;
    }

    public void setDrivinglicence(String drivinglicence) {
        this.drivinglicence = drivinglicence;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {return password;}

    public void setPassword(String password){this.password = password;}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return id == customers.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, drivinglicence, adress, mail, city, admin, password);
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", drivinglicence='" + drivinglicence + '\'' +
                ", adress='" + adress + '\'' +
                ", mail='" + mail + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", car=" + car +
                '}';
    }
}
