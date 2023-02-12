package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Customers dao sql.
 */
public class CustomersDaoSQLImpl extends AbstractDao<Customers> implements CustomersDao {
    private static CustomersDaoSQLImpl instance = null;
    private Connection connection;

    /**
     * Instantiates a new Customers dao sql.
     */
    public CustomersDaoSQLImpl() {
        super("Customers");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CustomersDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new CustomersDaoSQLImpl();
        return instance;
    }

    /**
     * Remove instance.
     */
    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

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
            List<Customers> l = executeQuery("SELECT * FROM Customers WHERE FullName = ? AND Password = ?", new Object[]{username, password});
            if (l.isEmpty()) return null;
            return l.get(0);
        } catch (CarsException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }
    public boolean provjeriKorisnika(String username) {
        String sql = "SELECT * FROM Customers WHERE FullName = ?";
        try {
            PreparedStatement s=getConnection().prepareStatement(sql);
            s.setString(1, username);
            ResultSet r = s.executeQuery();
            while(r.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}






