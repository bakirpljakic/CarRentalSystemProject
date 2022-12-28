package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.CustomersDao;
import ba.unsa.etf.rpr.dao.CustomersDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Registration {
    public TextField KorisnikID;
    public TextField EmailID;
    public PasswordField LozinkaID;
    public TextField GradID;
    public TextField AdresaID;
    public TextField BrVozackeID;

    @FXML
    public Button closeButton;
    public Button registrationID;

    public Registration() {
    }

    @FXML
    public void initialize() {
    }

    public void closeButton(ActionEvent actionEvent) {
        uspjesno = false;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPRbaza27", "freedb_bpljakic1", "2Xesc!cAcKJ%VPB");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    Customers c = new Customers();
    CustomersDao cDao = new CustomersDaoSQLImpl();
    boolean uspjesno = true;
    public void registrationButton(ActionEvent actionEvent) {

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
            c = new Customers(0,ime, vozacka, adresa,email,grad);
             cDao.addCustomer(c);
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