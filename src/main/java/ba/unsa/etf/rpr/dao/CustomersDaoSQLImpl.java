package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDaoSQLImpl implements CustomersDao{
    private Connection connection;

    public CustomersDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPRbaza27", "freedb_bpljakic1", "2Xesc!cAcKJ%VPB");
        } catch (SQLException e) {
            e.printStackTrace();
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
                customer.setDrivinglicence(myRs.getInt("DrivLicenceNumber"));
                customer.setAdress(myRs.getString("Adress"));
                customer.setCountry(myRs.getString("Country"));
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
        String insert =" INSERT INTO Customers (CustomerID, FullName, DrivLicenceNumber, Adress, Country, City, CarID) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setString(2, item.getFullname());
            ps.setInt(3,item.getDrivinglicence());
            ps.setString(4, item.getAdress());
            ps.setString(5, item.getCountry());
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
    public Customers update(Customers item) {
        String update=" UPDATE Customers set CustomerID = ?, FullName=?, DrivLicenceNumber=?, Adress=?, Country=?, City=?, CarID=?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, item.getId());
            ps.setString(2, item.getFullname());
            ps.setInt(3, item.getDrivinglicence());
            ps.setString(4, item.getAdress());
            ps.setString(5, item.getCountry());
            ps.setString(6, item.getCity());
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
                customer.setDrivinglicence(myRs.getInt("DrivLicenceNumber"));
                customer.setAdress(myRs.getString("Adress"));
                customer.setCountry(myRs.getString("Country"));
                customer.setCity(myRs.getString("City"));
                CarsDao carDao = new CarsDaoSQLImpl();
                customer.setCar(carDao.getById(myRs.getInt("CarID")));
            }
            myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }
}
