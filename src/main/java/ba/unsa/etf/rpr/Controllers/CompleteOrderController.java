package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CarsManager;
import ba.unsa.etf.rpr.business.OrdersManager;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

import static ba.unsa.etf.rpr.Controllers.LoginController.korisnik;
import static ba.unsa.etf.rpr.Controllers.OrderController.automobil;

public class CompleteOrderController {

    public TextField MarkaTextField;
    public TextField ModelTextField;
    public TextField GodisteTextField;

    public DatePicker DatumID;
    public TextField CijenaTextField;

    OrdersManager ordersManager = new OrdersManager();

    @FXML
    public void initialize() {
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

    public void OdjavaButton(ActionEvent actionEvent) {
    }
}
