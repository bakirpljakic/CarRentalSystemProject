package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CarsException;
import java.sql.*;
import java.util.*;


/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection = null;
    private String tableName;

    /**
     * Instantiates a new Abstract dao.
     *
     * @param tableName the table name
     */
    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }

    private static void createConnection() {
        if (AbstractDao.connection == null) {
            String server = new String();
            String user = new String();
            String pass = new String();
            try {
                Properties prop = new Properties();
                prop.load(ClassLoader.getSystemResource("db.properties").openStream());
                server = prop.getProperty("db.url");
                user = prop.getProperty("db.user");
                pass = prop.getProperty("db.password");
                AbstractDao.connection = DriverManager.getConnection(server, user, pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        return AbstractDao.connection;
    }

    /**
     * Row 2 object t.
     *
     * @param rs the rs
     * @return the t
     * @throws CarsException the cars exception
     */
    public abstract T row2object(ResultSet rs) throws CarsException;


    /**
     * Object 2 row map.
     *
     * @param object the object
     * @return the map
     */
    public abstract Map<String, Object> object2row(T object);

    /**
     * Execute query list.
     *
     * @param query  the query
     * @param params the params
     * @return the list
     * @throws CarsException the cars exception
     */
    public List<T> executeQuery(String query, Object[] params) throws CarsException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null) {
                for (int i = 1; i <= params.length; i++) {
                    stmt.setObject(i, params[i - 1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    /**
     * Execute query unique t.
     *
     * @param query  the query
     * @param params the params
     * @return the t
     * @throws CarsException the cars exception
     */
    public T executeQueryUnique(String query, Object[] params) throws CarsException {
       List<T> result = executeQuery(query, params);
       if (result != null && result.size() == 1) {
           return result.get(0);
       } else {
           throw new CarsException("Object not found");
       }
   }


    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }


    private String prepareUpdateParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

    public T getById(int id) throws CarsException {
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE Cid = ?", new Object[]{id});
    }

    public List<T> getAll() throws CarsException {
        return executeQuery("SELECT * FROM " + tableName, null);
    }

    public void delete(int id) throws CarsException{
        String sql = "DELETE FROM " + tableName + " WHERE Cid = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    public T add(T item) throws CarsException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");
        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */
            return item;
        } catch (SQLException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }

    public T update(T item) throws CarsException {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE Cid = ?");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new CarsException(e.getMessage(), e);
        }
    }
}



