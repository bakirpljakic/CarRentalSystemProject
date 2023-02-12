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

/**
 * The type Login controller.
 */
public class LoginController {
    /**
     * The constant korisnik.
     */
    public static Customers korisnik = new Customers();
    /**
     * The Korisnickoime id.
     */
    public TextField KorisnickoimeID;
    /**
     * The Lozinka id.
     */
    public PasswordField LozinkaID;
    /**
     * The Upozorenje.
     */
    public Text upozorenje;
    /**
     * The Welcome text.
     */
    public Label welcomeText;
    /**
     * The Car image view.
     */
    public ImageView car_image_view;
    /**
     * The Stage.
     */
    Stage stage = new Stage();
    /**
     * The C.
     */
    Customers c = new Customers();

    //Image myImage = new Image(getClass().getResource("/resources/img/car_sale.jpg"))
    private CustomersManager cmanager = new CustomersManager();

    /**
     * Instantiates a new Login controller.
     */
    public LoginController() {
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Button click.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void buttonClick(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        RegistrationController rc = fxmlLoader.getController();
        stage.getIcons().add(new Image("/img/korisnik.jpg"));
        stage.setScene(scene);
        stage.setResizable(false);
        //String css = String.valueOf(this.getClass().getResource("/css/login.css"));
        //scene.getStylesheets().add(css);
        stage.show();
        stage.setOnHiding(x -> {
            List<String> lista = rc.login();
            KorisnickoimeID.setText(lista.get(0));
            LozinkaID.setText(lista.get(1));
        });
    }

    /**
     * Prijava button click.
     *
     * @param actionEvent the action event
     * @throws IOException   the io exception
     * @throws CarsException the cars exception
     */
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
                stage.getIcons().add(new Image("/img/sale_car.jpg"));
                stage.setTitle("Kupovina");
                stage.setScene(scene);
                stage.show();

            }
        }
    }
}