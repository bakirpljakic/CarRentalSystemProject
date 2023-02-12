package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.CarsManager;
import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class OrderController {
    public TableColumn MarkaIDCol;
    public TableColumn ModelIDCol;
    public TableColumn GodisteIDCol;
    public TableColumn CijenaIDCol;
    public TableView<Cars> TabelaDostupnihAuta;

    public static Cars automobil = new Cars();
    private Integer id;
    private String Marka = "";
    private String Model = "";
    private Integer Godiste;
    private Integer Cijena;

    private CarsManager carsManager = new CarsManager();

    @FXML
    public void initialize() {
        prikaziTabelu();

    }
    Stage stage = new Stage();
    public void IznajmiButton(ActionEvent actionEvent) throws IOException, CarsException {
        Stage stage = (Stage) TabelaDostupnihAuta.getScene().getWindow();
        stage.close();
        automobil = carsManager.getById(id);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/completeOrder.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        CompleteOrderController completeOrderController = fxmlLoader.getController();
        stage.setTitle("KUPOVINA");
        stage.setScene(scene);
        stage.show();

    }
    Orders o = new Orders();

    public void prikaziTabelu(){
        MarkaIDCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Make"));
        ModelIDCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Model"));
        GodisteIDCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("CarYear"));
        CijenaIDCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Price"));
        List<Cars> auta = null;
        try {
            auta = carsManager.getAllAvailable();
        } catch (CarsException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Cars> a = FXCollections.observableArrayList(auta);
        TabelaDostupnihAuta.setItems(a);
        TabelaDostupnihAuta.refresh();

    }
    public void selectedCar(MouseEvent mouseEvent) throws CarsException {
        int i = TabelaDostupnihAuta.getSelectionModel().getSelectedIndex();
        if( MarkaIDCol.getCellData(i) == null) {
            return;
        }
        Marka = MarkaIDCol.getCellData(i).toString();
        Model = ModelIDCol.getCellData(i).toString();
        Godiste = Integer.parseInt(GodisteIDCol.getCellData(i).toString());
        Cijena = Integer.parseInt(CijenaIDCol.getCellData(i).toString());
        id = carsManager.getID(Marka, Model, Godiste, Cijena, true);
    }


}

