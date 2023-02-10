package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.dao.CarsDao;
import ba.unsa.etf.rpr.dao.CarsDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Cars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

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
    CarsDao carsDao = new CarsDaoSQLImpl();

    @FXML
    public void initialize() {
        AzurirajTabelu();

    }

    public void save(ActionEvent actionEvent) {
        String marka = MarkaID.getText();
        String model = ModelID.getText();
        int godina = Integer.parseInt(GodisteID.getText());
        int cijena = Integer.parseInt(CIjenaID.getText());
        boolean dostupno;
        String choice = DostupnoID.getValue();
        if(choice.equals("DA")){
            dostupno = true;
        }else{
            dostupno = false;
        }
        c = new Cars(0,marka,model, godina, cijena, dostupno);
        carsDao.add(c);
        AzurirajTabelu();
    }
    public void AzurirajTabelu(){
        IDCol.setCellValueFactory(new PropertyValueFactory<Cars,Integer>("id"));
        MarkaCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Make"));
        ModelCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("Model"));
        GodisteCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("CarYear"));
        DostupnoCol.setCellValueFactory(new PropertyValueFactory<Cars, Boolean>("Available"));
        CijenaCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Price"));
        List<Cars> auta = carsDao.getAll();
        ObservableList<Cars> a = FXCollections.observableArrayList(auta);
        TabelaAuta.setItems(a);
        TabelaAuta.refresh();
    }



    public void delete(ActionEvent actionEvent) {

        carsDao.delete(ID);
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
    public void change(ActionEvent actionEvent) {


    }

    public void delete(ActionEvent actionEvent) {
    }
}