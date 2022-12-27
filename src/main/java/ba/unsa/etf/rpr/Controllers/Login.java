package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void buttonClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
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
}