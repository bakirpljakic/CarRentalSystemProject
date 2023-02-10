package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.CarsDao;
import ba.unsa.etf.rpr.dao.CarsDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.domain.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderController {
    public TableColumn MarkaIDCol;
    public TableColumn ModelIDCol;
    public TableColumn GodisteIDCol;
    public TableColumn CijenaIDCol;
    public TableView<Cars> TabelaDostupnihAuta;

    @FXML
    public void initialize() {
        PrikaziTabelu();

    }

    public void IznajmiButton(ActionEvent actionEvent) {


    }
    Orders o = new Orders();
    CarsDao carsDao = new CarsDaoSQLImpl();
    public void PrikaziTabelu(){


    }
}
