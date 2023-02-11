package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class OrdersDaoSQLImpl extends AbstractDao<Orders> implements OrdersDao{
    private Connection connection;
    private static OrdersDaoSQLImpl instance = null;

    public OrdersDaoSQLImpl() {
        super("Orders");
    }

    public static OrdersDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new OrdersDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public Orders row2object(ResultSet rs) throws CarsException {
        try {
            Orders order = new Orders();
            order.setId(rs.getInt("OrderID"));
            order.setRentstart(rs.getDate("RentStartDate"));
            order.setRentend(rs.getDate("RentEndDate"));
            order.setTotalprice(rs.getInt("TotalPrice"));
            CarsDao carDao = new CarsDaoSQLImpl();
            order.setCar(carDao.getById(rs.getInt("CarID")));
            CustomersDao customerDao = new CustomersDaoSQLImpl();
            order.setCustomer(customerDao.getById(rs.getInt("CustomerID")));
            rs.close();
            return order;
        } catch (Exception e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Orders object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("OrderID", object.getId());
        item.put("RentStartDate", object.getRentstart());
        item.put("RentEndDate", object.getRentend());
        item.put("TotalPrice", object.getTotalprice());
        item.put("CarID", object.getCar().getId());
        item.put("CustomerID", object.getCustomer().getId());
        return item;
    }
























/*
    public OrdersDaoSQLImpl(){
        try (InputStream input = new FileInputStream("db.properties")) {
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
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("OrderID"));
                order.setRentstart(rs.getDate("RentStartDate"));
                order.setRentend(rs.getDate("RentEndDate"));
                order.setTotalprice(rs.getInt("TotalPrice"));
                CarsDao carDao = new CarsDaoSQLImpl();
                order.setCar(carDao.getById(rs.getInt("CarID")));
                CustomersDao customerDao = new CustomersDaoSQLImpl();
                order.setCustomer(customerDao.getById(rs.getInt("CustomerID")));
                rs.close();
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
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Orders order = new Orders();
                order.setId(rs.getInt("OrderID"));
                order.setRentstart(rs.getDate("RentStartDate"));
                order.setRentend(rs.getDate("RentEndDate"));
                order.setTotalprice(rs.getInt("TotalPrice"));
                CarsDao carDao = new CarsDaoSQLImpl();
                order.setCar(carDao.getById(rs.getInt("CarID")));
                CustomersDao customerDao = new CustomersDaoSQLImpl();
                order.setCustomer(customerDao.getById(rs.getInt("CustomerID")));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }*/
}
