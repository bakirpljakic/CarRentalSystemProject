package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
       /* Controller controller = new Controller();
        fxmlLoader.setController(controller);*/
        String css = String.valueOf(this.getClass().getResource("/css/login.css"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        scene.getStylesheets().add(css);
        stage.getIcons().add(new Image("/img/sale_car.jpg"));
        stage.setTitle("");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
      /*  Customers cus = new Customers(0, "hahah", "hahah","hahah","hahah","hahah");
        CustomersDao cusDao = new CustomersDaoSQLImpl();
        Customers c = new Customers();
        c = cusDao.addCustomer(cus);*/
        launch();



    }
}
