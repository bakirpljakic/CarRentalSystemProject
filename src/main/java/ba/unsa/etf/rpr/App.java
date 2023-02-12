package ba.unsa.etf.rpr;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.PrintWriter;

public class App {

    private static final Option addCar = new Option("c","add-car",false, "Adding new car to database");
    private static final Option addCustomer = new Option("u","add-customer",false, "Adding new customer to database");

    private static final Option deleteCar = new Option("delC", "delete-car", false, "Deleting a car from database");
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar CarSaleSystemProjectProject-cli-jar-with-dependencies.jar [option] (parameters)");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    public static void main(String[] args) {
      /*  Customers cus = new Customers(0, "hahah", "hahah","hahah","hahah","hahah");
        CustomersDao cusDao = new CustomersDaoSQLImpl();
        Customers c = new Customers();
        c = cusDao.addCustomer(cus);*/
        launch();



    }
}
