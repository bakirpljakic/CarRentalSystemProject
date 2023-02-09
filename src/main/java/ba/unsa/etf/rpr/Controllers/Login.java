package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.CustomersDao;
import ba.unsa.etf.rpr.dao.CustomersDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Customers;
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

public class Login {
    public TextField KorisnickoimeID;
    public PasswordField LozinkaID;

    public Login() {
    }

    public Label welcomeText;

    @FXML
    public void initialize() {}

    Stage stage = new Stage();

    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void buttonClick(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
       // Registracija r = fxmlLoader.getController();
        //Parent root = fxmlLoader.load();
        Registration r = new Registration();
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
    CustomersDao cDao = new CustomersDaoSQLImpl();
    public void PrijavaButtonClick(ActionEvent actionEvent) throws IOException {
      c =  cDao.getLoggedInCustomer(KorisnickoimeID.getText(), LozinkaID.getText());
        if(c ==null){
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
                Cars cars = fxmlLoader.getController();
                stage.setTitle("Automobili");
                stage.setScene(scene);
                stage.show();
            } else{
                Stage stage = (Stage) KorisnickoimeID.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/order.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                Cars cars = fxmlLoader.getController();
                stage.setTitle("Iznajmi");
                stage.setScene(scene);
                stage.show();

            }
        }
    }
}