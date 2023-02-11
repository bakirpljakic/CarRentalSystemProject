package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class CustomersDaoSQLImpl extends AbstractDao<Customers> implements CustomersDao {
    private static CustomersDaoSQLImpl instance = null;

    public CustomersDaoSQLImpl() {
        super("Customers");
    }

    public static CustomersDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new CustomersDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    private Connection connection;

    public CustomersDaoSQLImpl(){
        try (InputStream input = new FileInputStream(".properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception io) {
            io.printStackTrace();
        }
    }
    @Override
    public Customers getById(int id) {
        String query = " SELECT * FROM Customers WHERE Customerid=?";
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet myRs = ps.executeQuery();
            if(myRs.next()){
                Customers customer = new Customers();
                customer.setId(myRs.getInt("CustomerID"));
                customer.setFullname(myRs.getString("FullName"));
                customer.setDrivinglicence(myRs.getString("DrivLicenceNumber"));
                customer.setAdress(myRs.getString("Adress"));
                customer.setMail(myRs.getString("Mail"));
                customer.setCity(myRs.getString("City"));
                CarsDao carDao = new CarsDaoSQLImpl();
                customer.setCar(carDao.getById(myRs.getInt("CarID")));
                myRs.close();
                return customer;
            }else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customers add(Customers item) {
        String insert =" INSERT INTO Customers (CustomerID, FullName, DrivLicenceNumber, Adress, Mail, City, CarID) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setString(2, item.getFullname());
            ps.setString(3,item.getDrivinglicence());
            ps.setString(4, item.getAdress());
            ps.setString(5, item.getMail());
            ps.setString(6, item.getCity());
            ps.setInt(7, item.getCar().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Customers addCustomer(Customers item) {
        String insert =" INSERT INTO Customers (CustomerID, FullName, DrivLicenceNumber, Adress, Mail, City, Admin, Password) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setString(2, item.getFullname());
            ps.setString(3,item.getDrivinglicence());
            ps.setString(4, item.getAdress());
            ps.setString(5, item.getMail());
            ps.setString(6, item.getCity());
            ps.setBoolean(7, item.isAdmin());
            ps.setString(8, item.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Customers update(Customers item) {
        String update=" UPDATE Customers set CustomerID = ?, FullName=?, DrivLicenceNumber=?, Adress=?, Mail=?, City=?, CarID=?, Admin=?, Password=? ";
        try {
            PreparedStatement ps = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, item.getId());
            ps.setString(2, item.getFullname());
            ps.setString(3, item.getDrivinglicence());
            ps.setString(4, item.getAdress());
            ps.setString(5, item.getMail());
            ps.setString(6, item.getCity());
            ps.setBoolean(7, item.isAdmin());
            ps.setString(8, item.getPassword());
            CarsDao carDao = new CarsDaoSQLImpl();
            ps.setInt(7, item.getCar().getId());
            ps.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Customers WHERE CustomerID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(delete);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Customers> getAll() {
        String query = "SELECT * FROM Customers";
        List<Customers> customers = new ArrayList<Customers>();
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet myRs = ps.executeQuery();
            while(myRs.next()){
                Customers customer = new Customers();
                customer.setId(myRs.getInt("CustomerID"));
                customer.setFullname(myRs.getString("FullName"));
                customer.setDrivinglicence(myRs.getString("DrivLicenceNumber"));
                customer.setAdress(myRs.getString("Adress"));
                customer.setMail(myRs.getString("Mail"));
                customer.setCity(myRs.getString("City"));
                customer.setAdmin(myRs.getBoolean("Admin"));
                customer.setPassword(myRs.getString("Password"));
                CarsDao carDao = new CarsDaoSQLImpl();
                customer.setCar(carDao.getById(myRs.getInt("CarID")));
            }
            myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    public Customers getLoggedInCustomer(String username, String password){
        String query = "SELECT * FROM Customers WHERE FullName = ? AND Password = ?";
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet myRs = ps.executeQuery();
        if (myRs.next()){
            Customers customer = new Customers();
            customer.setId(myRs.getInt("CustomerID"));
            customer.setFullname(myRs.getString("FullName"));
            customer.setDrivinglicence(myRs.getString("DrivLicenceNumber"));
            customer.setAdress(myRs.getString("Adress"));
            customer.setMail(myRs.getString("Mail"));
            customer.setCity(myRs.getString("City"));
            customer.setAdmin(myRs.getBoolean("Admin"));
            customer.setPassword(myRs.getString("Password"));
            CarsDao carDao = new CarsDaoSQLImpl();
            customer.setCar(carDao.getById(myRs.getInt("CarID")));
        return customer;
        }
        else return null;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

