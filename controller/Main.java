package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SpectroElectro 2000");
		this.primaryStage.getIcons().add(new Image("/view/icon.png"));
		mainWindow();
	}

	public void mainWindow(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/MainWindowView.fxml"));
			AnchorPane pane = loader.load();
			
			MainWindowController mainWindowController = loader.getController();
			mainWindowController.setStage(primaryStage);
			primaryStage.setMinHeight(265);
			primaryStage.setMinWidth(505);
			Scene scene = new Scene(pane);
			scene.getStylesheets().add("/view/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
