package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Cars dao sql.
 */
public class CarsDaoSQLImpl extends AbstractDao<Cars> implements CarsDao{
    private static CarsDaoSQLImpl instance = null;
    public CarsDaoSQLImpl() {
        super("Cars");
    }

    public static CarsDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new CarsDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Cars row2object(ResultSet rs) throws CarsException {
        try {
            Cars car = new Cars();
            car.setId(rs.getInt("Cid"));
            car.setMake(rs.getString("Make"));
            car.setModel(rs.getString("Model"));
            car.setCarYear(rs.getInt("CarYear"));
            car.setPrice(rs.getInt("Price"));
            car.setAvailable(rs.getBoolean("Available"));;
            return car;
        } catch (SQLException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Cars object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("Cid", object.getId());
        item.put("Make", object.getMake());
        item.put("Model", object.getModel());
        item.put("CarYear", object.getCarYear());
        item.put("Price", object.getPrice());
        item.put("Available", object.isAvailable());
        return item;
    }

    @Override
    public List<Cars> getAllAvailable() {
        String query = "SELECT * FROM Cars WHERE Available = 1";
        List<Cars> cars = new ArrayList<Cars>();
        try{
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ResultSet myRs = ps.executeQuery();
            while(myRs.next()){
                Cars car = new Cars();
                car.setId(myRs.getInt("Cid"));
                car.setMake(myRs.getString("Make"));
                car.setModel(myRs.getString("Model"));
                car.setCarYear(myRs.getInt("CarYear"));
                car.setPrice(myRs.getInt("Price"));
                car.setAvailable(myRs.getBoolean("Available"));
                cars.add(car);
            }
            myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    public int getID(String model, String marka, Integer godiste,Integer cijena, boolean dostupno) throws CarsException {
        Cars c = executeQueryUnique("SELECT * FROM Cars WHERE Make = ? AND Model = ? AND CarYear=? AND Price=? AND Available=?", new Object[]{model, marka, godiste, cijena, dostupno});
        return c.getId();
    }













/*


    private Connection connection;

    public CarsDaoSQLImpl(){
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
    public Cars getById(int id) {
        String query = "SELECT * FROM Cars WHERE id=?";
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet myRs = ps.executeQuery();
            if(myRs.next()){
                Cars car = new Cars();
                car.setId(myRs.getInt("id"));
                car.setMake(myRs.getString("Make"));
                car.setModel(myRs.getString("Model"));
                car.setCarYear(myRs.getInt("CarYear"));
                car.setPrice(myRs.getInt("Price"));
                car.setAvailable(myRs.getBoolean("Available"));
                myRs.close();
                return car;
            }else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cars add(Cars item) {
        String insert =" INSERT INTO Cars (id, Make, Model, CarYear, Price, Available) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setString(2, item.getMake());
            ps.setString(3, item.getModel());
            ps.setInt(4,item.getCarYear());
            ps.setInt(5, item.getPrice());
            ps.setBoolean(6, item.isAvailable());
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
    public Cars update(Cars item) {
        String update=" UPDATE Cars set Make=?, Model=?, CarYear=?," +
                "Price=?, Available=? WHERE id=?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getMake());
            ps.setString(2, item.getModel());
            ps.setInt(3, item.getCarYear());
            ps.setInt(4, item.getPrice());
            ps.setBoolean(5, item.isAvailable());
            ps.setInt(6, item.getId());
            ps.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Cars WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(delete);
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Cars> getAll() {
        String query = "SELECT * FROM Cars";
        List<Cars> cars = new ArrayList<Cars>();
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet myRs = ps.executeQuery();
            while(myRs.next()){
                Cars car = new Cars();
                car.setId(myRs.getInt("id"));
                car.setMake(myRs.getString("Make"));
                car.setModel(myRs.getString("Model"));
                car.setCarYear(myRs.getInt("CarYear"));
                car.setPrice(myRs.getInt("Price"));
                car.setAvailable(myRs.getBoolean("Available"));
                cars.add(car);
            }
            myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }
*/

  /*  @Override
    public int getID(String marka, String model, int godiste, int cijena, boolean b) {
        try {
            List<Cars> l = executeQuery("SELECT * FROM Cars WHERE Make = ? AND Model = ? AND CarYear=? AND Price=? AND Available=?", new Object[]{marka, model, godiste, cijena, b});
            if (l.isEmpty()) return 0;
            return l.get(0).getId();
        } catch (Exception e) {
          //  throw new (e.getMessage(), e);
        }
    }*/
}
