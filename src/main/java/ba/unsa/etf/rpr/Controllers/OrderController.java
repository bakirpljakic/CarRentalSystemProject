package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.CarsDao;
import ba.unsa.etf.rpr.dao.CarsDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.domain.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class OrderController {
    public TableColumn MarkaIDCol;
    public TableColumn ModelIDCol;
    public TableColumn GodisteIDCol;
    public TableColumn CijenaIDCol;
    public TableView<Cars> TabelaDostupnihAuta;

    @FXML
    public void initialize() {
        prikaziTabelu();

    }

    public void IznajmiButton(ActionEvent actionEvent) {


    }
    Orders o = new Orders();
    CarsDao carsDao = new CarsDaoSQLImpl();
    public void prikaziTabelu(){
        MarkaIDCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Make"));
        ModelIDCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Model"));
        GodisteIDCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("CarYear"));
        CijenaIDCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Price"));
        List<Cars> auta = carsDao.getAllAvailable();
        ObservableList<Cars> a = FXCollections.observableArrayList(auta);
        TabelaDostupnihAuta.setItems(a);
        TabelaDostupnihAuta.refresh();

    }
}
