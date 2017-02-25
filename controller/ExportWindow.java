package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExportWindow {

	public static void display(String path) {
		
		try {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setMinWidth(100);
			window.getIcons().add(new Image("/view/icon.png"));
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(path));
			ExportWindowController exportWindowController = loader.getController();
			
			
			AnchorPane pane= loader.load();
			Scene scene = new Scene(pane);
			window.setScene(scene);
			window.showAndWait();
			exportWindowController.setStage(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
