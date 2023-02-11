package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomersManager;
import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public TextField KorisnickoimeID;
    public PasswordField LozinkaID;

    public LoginController() {
    }

    public Label welcomeText;

    @FXML
    public void initialize() {}

    Stage stage = new Stage();
    private CustomersManager cmanager = new CustomersManager();



    public void buttonClick(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
       // Registracija r = fxmlLoader.getController();
        //Parent root = fxmlLoader.load();
        RegistrationController r = new RegistrationController();
        fxmlLoader.setController(r);
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        //stage.setTitle("");
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnHiding(x->{
            List<String> lista = r.login();
            KorisnickoimeID.setText(lista.get(0));
            LozinkaID.setText(lista.get(1));
        });
    }

    Customers c = new Customers();

    public void PrijavaButtonClick(ActionEvent actionEvent) throws IOException, CarsException {
       // c = (Customers) cmanager.getAll();
       c = cmanager.getLoggedInCustomer(KorisnickoimeID.getText(), LozinkaID.getText());

        if(c == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neuspjesna prijava");
            alert.setHeaderText("Pogrešni podaci!");
            alert.setContentText("Uneseni su pogrešni podaci.");
            alert.showAndWait();
        }
        else{
            if(c.isAdmin()){
                Stage stage = (Stage) KorisnickoimeID.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cars.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                CarsController carsController = fxmlLoader.getController();
                stage.setTitle("Automobili");
                stage.setScene(scene);
                stage.show();
            } else{
                Stage stage = (Stage) KorisnickoimeID.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/order.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                OrderController ordersController = fxmlLoader.getController();
                stage.setTitle("Iznajmi");
                stage.setScene(scene);
                stage.show();

            }
        }
    }
}