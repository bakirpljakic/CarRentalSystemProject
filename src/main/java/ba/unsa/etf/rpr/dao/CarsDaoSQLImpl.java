package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Cars;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDaoSQLImpl implements CarsDao{

    private Connection connection;

    public CarsDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPRbaza27", "freedb_bpljakic1", "2Xesc!cAcKJ%VPB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Cars getById(int id) {
        String query = "SELECT * FROM Cars WHERE CarID=?";
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet myRs = ps.executeQuery();
            if(myRs.next()){
                Cars car = new Cars();
                car.setId(myRs.getInt("CarID"));
                car.setMake(myRs.getString("Make"));
                car.setModel(myRs.getString("Model"));
                car.setYear(myRs.getInt("CarYear"));
                car.setAirconditioner(myRs.getBoolean("AirConditioner"));
                car.setNavigation(myRs.getBoolean("Navigation"));
                car.setAbs(myRs.getBoolean("ABS"));
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
        String insert =" INSERT INTO Cars (CarID, Make, Model, CarYear, Category, AirConditioner, Navigation, ABS, Price, Available) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt( 1, item.getId());
            ps.setString(2, item.getMake());
            ps.setString(3, item.getModel());
            ps.setInt(4,item.getYear());
            ps.setString(5, item.getCategory());
            ps.setBoolean(6, item.isAirconditioner());
            ps.setBoolean(7, item.isNavigation());
            ps.setBoolean(8, item.isAbs());
            ps.setInt(9, item.getPrice());
            ps.setBoolean(10, item.isAvailable());
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
        String update=" UPDATE Cars set CarID = ?, Make=?, Model=?, CarYear=?, Category=?, AirConditioner=?, Navigation=?, " +
                "ABS=?, Price=?, Available=? WHERE CarID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getMake());
            ps.setString(2, item.getModel());
            ps.setInt(3, item.getYear());
            ps.setString(4, item.getCategory());
            ps.setBoolean(5, item.isAirconditioner());
            ps.setBoolean(6, item.isNavigation());
            ps.setBoolean(7, item.isAbs());
            ps.setInt(8, item.getPrice());
            ps.setBoolean(9, item.isAvailable());
            ps.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Cars WHERE CarID = ?";
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
                car.setId(myRs.getInt("CarID"));
                car.setMake(myRs.getString("Make"));
                car.setModel(myRs.getString("Model"));
                car.setYear(myRs.getInt("CarYear"));
                car.setAirconditioner(myRs.getBoolean("AirConditioner"));
                car.setNavigation(myRs.getBoolean("Navigation"));
                car.setAbs(myRs.getBoolean("ABS"));
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
}
