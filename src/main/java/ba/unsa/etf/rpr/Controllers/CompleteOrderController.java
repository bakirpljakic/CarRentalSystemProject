package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import static ba.unsa.etf.rpr.Controllers.OrderController.automobil;

public class CompleteOrderController {

    public TextField MarkaTextField;
    public TextField ModelTextField;
    public TextField GodisteTextField;

    @FXML
    public void initialize() {
    MarkaTextField.setEditable(false);
    ModelTextField.setEditable(false);
    GodisteTextField.setEditable(false);
    MarkaTextField.setText(automobil.getMake());
    ModelTextField.setText(automobil.getModel());
    GodisteTextField.setText(String.valueOf(automobil.getCarYear()));
    }

}
