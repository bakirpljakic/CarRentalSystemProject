package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomersManager;
import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public static Customers korisnik = new Customers();
    public TextField KorisnickoimeID;
    public PasswordField LozinkaID;
    public Text upozorenje;
    public Label welcomeText;
    public ImageView car_image_view;
    Stage stage = new Stage();
    Customers c = new Customers();

    //Image myImage = new Image(getClass().getResource("/resources/img/car_sale.jpg"))
    private CustomersManager cmanager = new CustomersManager();
    public LoginController() {
    }

    @FXML
    public void initialize() {
    }

    public void buttonClick(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
        RegistrationController r = new RegistrationController();
        fxmlLoader.setController(r);
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.getIcons().add(new Image("/img/korisnik.jpg"));
        stage.setScene(scene);
        stage.setResizable(false);
        String css = String.valueOf(this.getClass().getResource("/css/login.css"));
        scene.getStylesheets().add(css);
        stage.show();
        stage.setOnHiding(x -> {
            List<String> lista = r.login();
            KorisnickoimeID.setText(lista.get(0));
            LozinkaID.setText(lista.get(1));
        });
    }

    public void PrijavaButtonClick(ActionEvent actionEvent) throws IOException, CarsException {
        c = cmanager.getLoggedInCustomer(KorisnickoimeID.getText(), LozinkaID.getText());
        if (c == null) {
            if (KorisnickoimeID.getText().isBlank() && LozinkaID.getText().isBlank()) {
                upozorenje.setText("Unesite Vaše korisničko ime i lozinku.");
            } else if (KorisnickoimeID.getText().isBlank() && !LozinkaID.getText().isBlank()) {
                upozorenje.setText("Unesite Vaše korisničko ime.");
            } else if (!KorisnickoimeID.getText().isBlank() && LozinkaID.getText().isBlank()) {
                upozorenje.setText("Unesite Vašu lozinku.");
            } else {
                upozorenje.setText("Unesite ispravno korisničko ime i lozinku!");
            }
        } else {
            if (c.isAdmin()) {
                korisnik = c;
                Stage stage = (Stage) KorisnickoimeID.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cars.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                CarsController carsController = fxmlLoader.getController();
                stage.setTitle("Automobili");
                stage.setScene(scene);
                stage.show();

            } else {
                korisnik = c;
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