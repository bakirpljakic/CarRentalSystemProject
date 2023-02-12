package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CustomersManager;
import ba.unsa.etf.rpr.dao.CustomersDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
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

/**
 * The type Registration controller.
 */
public class RegistrationController {
    /**
     * The Korisnik id.
     */
    public TextField KorisnikID;
    /**
     * The Email id.
     */
    public TextField EmailID;
    /**
     * The Lozinka id.
     */
    public PasswordField LozinkaID;
    /**
     * The Grad id.
     */
    public TextField GradID;
    /**
     * The Adresa id.
     */
    public TextField AdresaID;
    /**
     * The Br vozacke id.
     */
    public TextField BrVozackeID;

    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;
    /**
     * The Registration id.
     */
    public Button registrationID;
    /**
     * The C.
     */
    Customers c = new Customers();

    /**
     * The Uspjesno.
     */
    boolean uspjesno = true;
    private CustomersManager cManager = new CustomersManager();

    /**
     * Instantiates a new Registration controller.
     */
    public RegistrationController() {
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Close button.
     *
     * @param actionEvent the action event
     */
    public void closeButton(ActionEvent actionEvent) {
        uspjesno = false;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Registration button.
     *
     * @param actionEvent the action event
     * @throws CarsException the cars exception
     */
    public void registrationButton(ActionEvent actionEvent) throws CarsException {
        if (KorisnikID.getText().isEmpty() || EmailID.getText().isEmpty() || LozinkaID.getText().isEmpty() || GradID.getText().isEmpty() || AdresaID.getText().isEmpty() || BrVozackeID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Status registracije:");
            alert.setContentText("Popunite sva polja kako biste se uspješno registrovali!");
            alert.showAndWait();
            uspjesno = false;
        } else if (!KorisnikID.getText().isEmpty() || !EmailID.getText().isEmpty() || !LozinkaID.getText().isEmpty() || !GradID.getText().isEmpty() || !AdresaID.getText().isEmpty() || !BrVozackeID.getText().isEmpty()) {
            String korisnik = KorisnikID.getText();
            CustomersDaoSQLImpl u = new CustomersDaoSQLImpl();
            Customers customer = new Customers();
            boolean provjera = DaoFactory.customersDao().provjeriKorisnika(korisnik);
            if (provjera) {
                uspjesno = false;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Status registracije:");
                alert.setContentText("Korisničko ime već postoji!");
                alert.showAndWait();
                KorisnikID.setFocusTraversable(true);
            } else {
                uspjesno = true;
                String ime = KorisnikID.getText();
                String vozacka = BrVozackeID.getText();
                String adresa = AdresaID.getText();
                String email = EmailID.getText();
                String grad = GradID.getText();
                String sifra = LozinkaID.getText();
                c = new Customers(0, ime, vozacka, adresa, email, grad, false, sifra);
                cManager.add(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Status registracije:");
                alert.setContentText("Uspješno ste registrovani!");
                alert.showAndWait();
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * Login list.
     *
     * @return the list
     */
    public List<String> login() {
        List<String> lista = new ArrayList<>();

        if (uspjesno == false) {
            lista.add("");
            lista.add("");
        } else {
            lista.add(KorisnikID.getText());
            lista.add(LozinkaID.getText());
        }
        return lista;
    }
}