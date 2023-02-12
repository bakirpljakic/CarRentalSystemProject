package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.business.CarsManager;
import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class CarsController {



    public TextField MarkaID;
    public TextField ModelID;
    public TextField GodisteID;
    @FXML
    public ChoiceBox<String> DostupnoID;
    public TextField CijenaID;
    public TableColumn MarkaCol;
    public TableColumn ModelCol;
    public TableColumn GodisteCol;
    public TableColumn DostupnoCol;
    public TableColumn CijenaCol;
    public TableView<Cars> TabelaAuta;
    public TableColumn IDCol;
    public int ID;


    Cars c = new Cars();

    private CarsManager carsManager = new CarsManager();

    @FXML
    public void initialize() throws CarsException {
        AzurirajTabelu();

    }

    public void save(ActionEvent actionEvent) throws CarsException {
        String marka = MarkaID.getText();
        String model = ModelID.getText();
        int godina = Integer.parseInt(GodisteID.getText());
        int cijena = Integer.parseInt(CijenaID.getText());
        boolean dostupno;
        String choice = DostupnoID.getValue();
        if(choice.equals("DA")){
            dostupno = true;
        }else{
            dostupno = false;
        }
        c = new Cars(ID,marka,model, godina, cijena, dostupno);
        carsManager.add(c);
        AzurirajTabelu();
    }
    public void AzurirajTabelu() throws CarsException {
        IDCol.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("id"));
        MarkaCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Make"));
        ModelCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Model"));
        GodisteCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("CarYear"));
        DostupnoCol.setCellValueFactory(new PropertyValueFactory<Cars, Boolean>("Available"));
        CijenaCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Price"));
        List<Cars> auta = carsManager.getAll();
        ObservableList<Cars> a = FXCollections.observableArrayList(auta);
        TabelaAuta.setItems(a);
        TabelaAuta.refresh();
    }



    public void delete(ActionEvent actionEvent) throws CarsException {
        carsManager.delete(ID);
        AzurirajTabelu();
    }



    public void selectedCar(javafx.scene.input.MouseEvent mouseEvent) {
        int i = TabelaAuta.getSelectionModel().getSelectedIndex();
        ID = Integer.parseInt(String.valueOf(IDCol.getCellData(i)));
        MarkaID.setText(MarkaCol.getCellData(i).toString());
        ModelID.setText(ModelCol.getCellData(i).toString());
        GodisteID.setText(GodisteCol.getCellData(i).toString());
        if(DostupnoCol.getCellData(i).toString().equals("true")){
            DostupnoID.setValue("DA");
        }else{
            DostupnoID.setValue("NE");
        }
        CijenaID.setText(CijenaCol.getCellData(i). toString());
    }
    public void change(ActionEvent actionEvent) throws CarsException {
        String marka = MarkaID.getText();
        String model = ModelID.getText();
        int godina = Integer.parseInt(GodisteID.getText());
        int cijena = Integer.parseInt(CijenaID.getText());
        boolean dostupno = false;
        String choice = DostupnoID.getValue();
        if(choice.equals("DA")){
            dostupno = true;
        }else {
            dostupno = false;
        }
        c = new Cars(ID,marka,model, godina, cijena, dostupno);
        carsManager.update(c);
        AzurirajTabelu();

    }

    public void reset(ActionEvent actionEvent) {
        MarkaID.setText(null);
        ModelID.setText(null);
        GodisteID.setText(null);
        DostupnoID.setValue(null);
        CijenaID.setText(null);
    }

    public void nazadButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) MarkaID.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController ordersController = fxmlLoader.getController();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}