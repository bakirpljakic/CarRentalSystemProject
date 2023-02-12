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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

import static ba.unsa.etf.rpr.Controllers.LoginController.korisnik;
import static ba.unsa.etf.rpr.Controllers.OrderController.automobil;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class CompleteOrderController {

    public TextField MarkaTextField;
    public TextField ModelTextField;
    public TextField GodisteTextField;

    public DatePicker DatumID;
    public TextField CijenaTextField;

    OrdersManager ordersManager = new OrdersManager();

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

    CarsManager carsManager = new CarsManager();
    public void KupiButton(ActionEvent actionEvent) throws CarsException {
        Orders o = new Orders();
        Date date = Date.valueOf(DatumID.getValue());
        int cijena = Integer.parseInt(CijenaTextField.getText());
        o = new Orders(0, date, cijena, automobil, korisnik);
        ordersManager.add(o);
        automobil.setAvailable(false);

    }

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
