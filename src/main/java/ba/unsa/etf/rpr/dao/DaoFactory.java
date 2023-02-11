package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final CarsDao carsDao = CarsDaoSQLImpl.getInstance();
    private static final CustomersDao customersDao = CustomersDaoSQLImpl.getInstance();
    private static final OrdersDao ordersDao = OrdersDaoSQLImpl.getInstance();

    public static CarsDao carsDao() {
        return carsDao;
    }

    public static CustomersDao customersDao() {
        return customersDao;
    }

    public static OrdersDao ordersDao() {
        return ordersDao;
    }
}
