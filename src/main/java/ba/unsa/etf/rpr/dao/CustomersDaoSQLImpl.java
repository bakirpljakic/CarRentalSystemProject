package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.sql.Connection;
import java.sql.ResultSet;
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

    @Override
    public Customers row2object(ResultSet rs) throws CarsException {
        try {
            Customers customer = new Customers();
            customer.setId(rs.getInt("CustomerID"));
            customer.setFullname(rs.getString("FullName"));
            customer.setDrivinglicence(rs.getString("DrivLicenceNumber"));
            customer.setAdress(rs.getString("Adress"));
            customer.setMail(rs.getString("Mail"));
            customer.setCity(rs.getString("City"));
            customer.setAdmin(rs.getBoolean("Admin"));
            customer.setPassword(rs.getString("Password"));
            /*CarsDao carDao = new CarsDaoSQLImpl();
            customer.setCar(carDao.getById(rs.getInt("CarID")));*/
            return customer;
        } catch (Exception e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Customers object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("CustomerID", object.getId());
        item.put("FullName", object.getFullname());
        item.put("DrivLicenceNumber", object.getDrivinglicence());
        item.put("Adress", object.getAdress());
        item.put("Mail", object.getMail());
        item.put("City", object.getCity());
        item.put("Admin", object.isAdmin());
        item.put("Password", object.getPassword());
        return item;
    }




    public Customers getLoggedInCustomer(String username, String password) throws CarsException {
            try {
                /*System.out.println("jesmo li ovdje");
                List<Customers> l = executeQuery("SELECT * FROM Customers WHERE FullName = ? AND Password = ?", new Object[]{username, password});
                if (l.isEmpty()) return null;
                return l.get(0);*/
                Customers c = executeQueryUnique("SELECT * FROM Customers WHERE FullName = ? AND Password = ?", new Object[]{username, password});
                return c;
            } catch (CarsException e) {
                throw new CarsException(e.getMessage(), e);
            }
        }}

   /*public Customers getLoggedInCustomer(String username, String password) throws CarsException {
    String query = "SELECT * FROM Customers WHERE FullName = ? AND Password = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet myRs = ps.executeQuery();
            if (myRs.next()) {
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
            } else return null;
        } catch (SQLException | CarsException e) {
            throw new RuntimeException(e);
        }
    }}*/
    /*

    @Override
    public Customers addCustomer(Customers item) {
        String insert =" INSERT INTO Customers (CustomerID, FullName, DrivLicenceNumber, Adress, Mail, City, Admin, Password) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
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
}
/*
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
*/



