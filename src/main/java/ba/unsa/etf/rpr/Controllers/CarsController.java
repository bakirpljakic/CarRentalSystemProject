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
    public TextField CIjenaID;
    public TableColumn MarkaCol;
    public TableColumn ModelCol;
    public TableColumn GodisteCol;
    public TableColumn DostupnoCol;
    public TableColumn CijenaCol;

    Cars c = new Cars();
    CarsDao carsDao = new CarsDaoSQLImpl();

    @FXML
    public void initialize() {
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


    }

    public void change(ActionEvent actionEvent) {


    }

    public void delete(ActionEvent actionEvent) {
    }
}