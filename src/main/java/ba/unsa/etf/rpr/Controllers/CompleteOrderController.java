package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CarsManager;
import ba.unsa.etf.rpr.business.OrdersManager;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

import static ba.unsa.etf.rpr.Controllers.LoginController.korisnik;
import static ba.unsa.etf.rpr.Controllers.OrderController.automobil;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * The type Complete order controller.
 */
public class CompleteOrderController {

    /**
     * The Marka text field.
     */
    public TextField MarkaTextField;
    /**
     * The Model text field.
     */
    public TextField ModelTextField;
    /**
     * The Godiste text field.
     */
    public TextField GodisteTextField;

    /**
     * The Datum id.
     */
    public DatePicker DatumID;
    /**
     * The Cijena text field.
     */
    public TextField CijenaTextField;

    /**
     * The Orders manager.
     */
    OrdersManager ordersManager = new OrdersManager();

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        MarkaTextField.setFocusTraversable(false);
        ModelTextField.setFocusTraversable(false);
        GodisteTextField.setFocusTraversable(false);
        DatumID.setFocusTraversable(true);
        MarkaTextField.setEditable(false);
        ModelTextField.setEditable(false);
        GodisteTextField.setEditable(false);
        CijenaTextField.setEditable(false);
        MarkaTextField.setText(automobil.getMake());
        ModelTextField.setText(automobil.getModel());
        GodisteTextField.setText(String.valueOf(automobil.getCarYear()));
        CijenaTextField.setText(String.valueOf(automobil.getPrice()));
    }

    /**
     * The Cars manager.
     */
    CarsManager carsManager = new CarsManager();

    /**
     * Kupi button.
     *
     * @param actionEvent the action event
     * @throws CarsException the cars exception
     */
    public void KupiButton(ActionEvent actionEvent) throws CarsException {
        if(DatumID.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Unesite datum kupovine!");
            alert.showAndWait();
            return;
        }
        Orders o = new Orders();
        Date date = Date.valueOf(DatumID.getValue());
        int cijena = Integer.parseInt(CijenaTextField.getText());
        o = new Orders(0, date, cijena, automobil, korisnik);
        ordersManager.add(o);
        automobil.setAvailable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uspešna kupovina");
        alert.setHeaderText("Uspješno ste kupili Vaš automobil.");
        alert.setContentText("Hvala na kupovini.");
        alert.showAndWait();
    }

    /**
     * Odjava button.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void OdjavaButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ModelTextField.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController ordersController = fxmlLoader.getController();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}
