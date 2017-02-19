package model;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SpectroModelCellFactory implements Callback<ListView<SpectroModel>, ListCell<SpectroModel>> {

    @Override
    public ListCell<SpectroModel> call(ListView<SpectroModel> listview){
        return new SpectroModelCell();
    }
}
