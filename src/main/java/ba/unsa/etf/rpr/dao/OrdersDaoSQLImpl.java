package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoSQLImpl implements OrdersDao{
    private Connection connection;

    public OrdersDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPRbaza27", "freedb_bpljakic1", "2Xesc!cAcKJ%VPB");
        } catch (SQLException e) {
            e.printStackTrace();
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
                order.setTank(myRs.getInt("TankLevel"));
                order.setMileagestart(myRs.getInt("MileageStart"));
                order.setMileageend(myRs.getInt("MileageEnd"));
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
        String insert =" INSERT INTO Orders (OrderID, DateProccessed, RentStartDate, RentEndDate, TankLevel, MileageStart, MileageEnd, CustomerID, CarID) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setDate(2, item.getDateproccessed());
            ps.setDate(3,item.getRentstart());
            ps.setDate(4, item.getRentend());
            ps.setInt(5, item.getTank());
            ps.setInt(6, item.getMileagestart());
            ps.setInt(7, item.getMileageend());
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
        String update=" UPDATE Orders set OrderID = ?, DateProccessed=?, RentStartDate=?, RentEndDate=?, TankLevel=?, MileageStart=?, MileageEnd=?, CustomerID=?, CarID=?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setDate(2, item.getDateproccessed());
            ps.setDate(3,item.getRentstart());
            ps.setDate(4, item.getRentend());
            ps.setInt(5, item.getTank());
            ps.setInt(6, item.getMileagestart());
            ps.setInt(7, item.getMileageend());
            CustomersDao customerDao = new CustomersDaoSQLImpl();
            ps.setInt(8, item.getCustomer().getId());
            CarsDao carDao = new CarsDaoSQLImpl();
            ps.setInt(9, item.getCar().getId());
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
                order.setDateproccessed(myRs.getDate("DateProccessed"));
                order.setRentstart(myRs.getDate("RentStartDate"));
                order.setRentend(myRs.getDate("RentEndDate"));
                order.setTank(myRs.getInt("TankLevel"));
                order.setMileagestart(myRs.getInt("MileageStart"));
                order.setMileageend(myRs.getInt("MileageEnd"));
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
