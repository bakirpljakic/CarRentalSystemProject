package ba.unsa.etf.rpr.dao;

/**
 * The type Dao factory.
 */
public class DaoFactory {
    private static final CarsDao carsDao = CarsDaoSQLImpl.getInstance();
    private static final CustomersDao customersDao = CustomersDaoSQLImpl.getInstance();
    private static final OrdersDao ordersDao = OrdersDaoSQLImpl.getInstance();

    /**
     * Cars dao cars dao.
     *
     * @return the cars dao
     */
    public static CarsDao carsDao() {
        return carsDao;
    }

    /**
     * Customers dao customers dao.
     *
     * @return the customers dao
     */
    public static CustomersDao customersDao() {
        return customersDao;
    }

    /**
     * Orders dao orders dao.
     *
     * @return the orders dao
     */
    public static OrdersDao ordersDao() {
        return ordersDao;
    }
}
