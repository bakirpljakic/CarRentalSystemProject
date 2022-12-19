package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    public Label welcomeText;

    @FXML
    void initialize() {}


    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}