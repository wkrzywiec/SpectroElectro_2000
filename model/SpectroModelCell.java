package model;

import javafx.scene.control.ListCell;

public class SpectroModelCell extends ListCell<SpectroModel> {

	@Override
	public void updateItem(SpectroModel item, boolean empty) {
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
