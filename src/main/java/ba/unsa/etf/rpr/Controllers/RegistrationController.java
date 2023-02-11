package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomersManager;
import ba.unsa.etf.rpr.domain.Customers;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RegistrationController {
    public TextField KorisnikID;
    public TextField EmailID;
    public PasswordField LozinkaID;
    public TextField GradID;
    public TextField AdresaID;
    public TextField BrVozackeID;

    @FXML
    public Button closeButton;
    public Button registrationID;

    public RegistrationController() {
    }

    @FXML
    public void initialize() {
    }

    public void closeButton(ActionEvent actionEvent) {
        uspjesno = false;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    Customers c = new Customers();
    private CustomersManager cManager = new CustomersManager();

    //CustomersDao cDao = new CustomersDaoSQLImpl();
    boolean uspjesno = true;
    public void registrationButton(ActionEvent actionEvent) throws CarsException {

        if (KorisnikID.getText().isEmpty() || EmailID.getText().isEmpty() || LozinkaID.getText().isEmpty() || GradID.getText().isEmpty() || AdresaID.getText().isEmpty() || BrVozackeID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Status registracije:");
            alert.setContentText("Neuspješna registracija!");
            alert.showAndWait();
            uspjesno = false;
        } else {
            uspjesno = true;
            String ime = KorisnikID.getText();
            String vozacka = BrVozackeID.getText();
            String adresa = AdresaID.getText();
            String email = EmailID.getText();
            String grad = GradID.getText();
            String sifra = LozinkaID.getText();
            c = new Customers(0,ime, vozacka, adresa,email,grad,false,sifra);
            cManager.add(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status registracije:");
            alert.setContentText("Uspješno ste registrovani!");
            alert.showAndWait();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }


    }

    public List<String> login() {
        List<String> lista = new ArrayList<>();

        if (uspjesno == false) {
            lista.add("");
            lista.add("");
        } else  {
            lista.add(KorisnikID.getText());
            lista.add(LozinkaID.getText());
        }
        return lista;
    }
}