package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.business.CarsManager;
import ba.unsa.etf.rpr.domain.Cars;
import ba.unsa.etf.rpr.exceptions.CarsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

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
        ID = Integer.valueOf(IDCol.getCellData(i).toString());
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
}