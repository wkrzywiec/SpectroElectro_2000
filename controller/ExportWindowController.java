package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExportWindowController {

	private Stage window;
	@FXML Button bt1;
	
	public void setStage(Stage window){
		this.window = window;
	}
	
	public void close() {
		window.close();
	}
}
