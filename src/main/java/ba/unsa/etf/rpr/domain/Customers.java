package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * The type Customers.
 */
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

    /**
     * Instantiates a new Customers.
     *
     * @param i        the
     * @param ime      the ime
     * @param vozacka  the vozacka
     * @param adresa   the adresa
     * @param email    the email
     * @param grad     the grad
     * @param admin    the admin
     * @param password the password
     */
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

    /**
     * Instantiates a new Customers.
     */
    public Customers() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets fullname.
     *
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Sets fullname.
     *
     * @param fullname the fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Gets drivinglicence.
     *
     * @return the drivinglicence
     */
    public String getDrivinglicence() {
        return drivinglicence;
    }

    /**
     * Sets drivinglicence.
     *
     * @param drivinglicence the drivinglicence
     */
    public void setDrivinglicence(String drivinglicence) {
        this.drivinglicence = drivinglicence;
    }

    /**
     * Gets adress.
     *
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets adress.
     *
     * @param adress the adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {return password;}

    /**
     * Set password.
     *
     * @param password the password
     */
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
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
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
