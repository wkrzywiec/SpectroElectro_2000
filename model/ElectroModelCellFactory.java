package model;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ElectroModelCellFactory implements Callback<ListView<ElectroModel>, ListCell<ElectroModel>> {

    @Override
    public ListCell<ElectroModel> call(ListView<ElectroModel> listview){
        return new ElectroModelCell();
    }

}
