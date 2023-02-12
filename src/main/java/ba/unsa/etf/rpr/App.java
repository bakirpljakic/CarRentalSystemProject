package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CarsManager;
import ba.unsa.etf.rpr.domain.Cars;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

/**
 * The type App.
 */
public class App {

    private static final Option addCar = new Option("c", "add-car", false, "Adding new car to database");
    private static final Option deleteCar = new Option("delC", "delete-car", false, "Deleting a car from database");
    private static final Option getCars = new Option("getC", "get-cars", false, "Printing all cars from database");


    /**
     * Print formatted options.
     *
     * @param options the options
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar CarSaleSystemProjectProject-cli-jar-with-dependencies.jar [option] (parameters)");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * Add options options.
     *
     * @return the options
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addCar);
        options.addOption(deleteCar);
        options.addOption(getCars);
        return options;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        CarsManager cmanager = new CarsManager();

        if (cl.hasOption(addCar.getOpt()) || cl.hasOption((addCar.getLongOpt()))) {
            try {
                Cars c = new Cars();
                c.setMake(cl.getArgList().get(0));
                c.setModel(cl.getArgList().get(1));
                c.setCarYear(Integer.parseInt(cl.getArgList().get(2)));
                if (cl.getArgList().get(3).equals("DA")) {
                    c.setAvailable(true);
                } else {
                    c.setAvailable(false);
                }
                c.setPrice(Integer.parseInt(cl.getArgList().get(4)));
                cmanager.add(c);
                System.out.println("Car successfully added to database");
            } catch (Exception e) {
                System.out.println("Error. Invalid parametars.");
                printFormattedOptions(options);
                System.exit(-1);
            }
        } else if (cl.hasOption(deleteCar.getOpt()) || cl.hasOption(deleteCar.getLongOpt())) {
            Cars c = new Cars();
            try{
            boolean dostupno = false;
            if (cl.getArgList().get(4).equals("DA")) {
                dostupno = true;
            }
            int a = cmanager.getID(cl.getArgList().get(0), cl.getArgList().get(1), (Integer.parseInt(cl.getArgList().get(2))),
                    (Integer.parseInt(cl.getArgList().get(3))), dostupno);
            cmanager.delete(a);
            System.out.println("Car successfuly deleted from database!");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Car with that criteria does not exist.");
            }
        } else if (cl.hasOption(getCars.getOpt()) || cl.hasOption(getCars.getLongOpt())) {
            cmanager.getAll().forEach(c -> System.out.println(c.getMake() +" "+ c.getModel() + " " + c.getCarYear() + " "+
                    c.getPrice()));
        }
    }
}
