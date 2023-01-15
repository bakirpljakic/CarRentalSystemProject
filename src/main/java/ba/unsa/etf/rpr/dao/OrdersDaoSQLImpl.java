package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrdersDaoSQLImpl implements OrdersDao{
    private Connection connection;

    public OrdersDaoSQLImpl(){
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
    public Orders getById(int id) {
        String query = " SELECT * FROM Orders WHERE id=?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet myRs = ps.executeQuery();
            if (myRs.next()) {
                Orders order = new Orders();
                order.setId(myRs.getInt("OrderID"));
                order.setRentstart(myRs.getDate("RentStartDate"));
                order.setRentend(myRs.getDate("RentEndDate"));
                order.setTotalprice(myRs.getInt("TotalPrice"));
                CarsDao carDao = new CarsDaoSQLImpl();
                order.setCar(carDao.getById(myRs.getInt("CarID")));
                CustomersDao customerDao = new CustomersDaoSQLImpl();
                order.setCustomer(customerDao.getById(myRs.getInt("CustomerID")));
                myRs.close();
                return order;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orders add(Orders item) {
        String insert =" INSERT INTO Orders (OrderID, RentStartDate, RentEndDate, TotalPrice, CustomerID, CarID) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setDate(2,item.getRentstart());
            ps.setDate(3, item.getRentend());
            ps.setInt(4, item.getTotalprice());
            CustomersDao customerDao = new CustomersDaoSQLImpl();
            ps.setInt(8, item.getCustomer().getId());
            CarsDao carDao = new CarsDaoSQLImpl();
            ps.setInt(9, item.getCar().getId());
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
    public Orders update(Orders item) {
        String update=" UPDATE Orders set OrderID = ?, RentStartDate=?, RentEndDate=?, TotalPrice=?, CustomerID=?, CarID=?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setDate(2,item.getRentstart());
            ps.setDate(3, item.getRentend());
            ps.setInt(4, item.getTotalprice());
            CustomersDao customerDao = new CustomersDaoSQLImpl();
            ps.setInt(5, item.getCustomer().getId());
            CarsDao carDao = new CarsDaoSQLImpl();
            ps.setInt(6, item.getCar().getId());
            ps.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(delete);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> getAll() {
        String query = "SELECT * FROM Orders";
        List<Orders> orders = new ArrayList<Orders>();
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet myRs = ps.executeQuery();
            while(myRs.next()){
                Orders order = new Orders();
                order.setId(myRs.getInt("OrderID"));
                order.setRentstart(myRs.getDate("RentStartDate"));
                order.setRentend(myRs.getDate("RentEndDate"));
                order.setTotalprice(myRs.getInt("TotalPrice"));
                CarsDao carDao = new CarsDaoSQLImpl();
                order.setCar(carDao.getById(myRs.getInt("CarID")));
                CustomersDao customerDao = new CustomersDaoSQLImpl();
                order.setCustomer(customerDao.getById(myRs.getInt("CustomerID")));
            }
            myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }
}
