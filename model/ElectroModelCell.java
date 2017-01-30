package model;

import javafx.scene.control.ListCell;

public class ElectroModelCell extends ListCell<ElectroModel> {
	
	@Override
	public void updateItem(ElectroModel item, boolean empty) {
		super.updateItem(item, empty);
		
		String name = null;
		
		if (item == null || empty) {
		}
		else {
			name = item.getScanName();
		}
		this.setText(name);
	}

}
