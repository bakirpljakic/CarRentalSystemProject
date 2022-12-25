package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {
    public Controller() {
    }

    public Label welcomeText;

    @FXML
    void initialize() {}


    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void buttonClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registracija.fxml"));
       // Registracija r = fxmlLoader.getController();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        //stage.setTitle("");
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}