package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import model.ElectroModel;
import model.ElectroModelCellFactory;
import model.SpectroModel;
import model.SpectroModelCellFactory;

public class ExportWindowController {

	private MainWindowController mainWindowController;
	private Stage window;
	private ObservableList<ElectroModel> electroChartList;
	private ObservableList<SpectroModel> spectroChartList;

	@FXML private ListView<ElectroModel> electroChartListView;
	@FXML private ListView<SpectroModel> spectroChartListView;
	@FXML Button btnExport;
	
	public void setStage(Stage window){
		this.window = window;
	}
	
	public void setMainWindowController(MainWindowController mainWindowController){
		this.mainWindowController = mainWindowController;
		
		if(mainWindowController.getElectroList().size() != 0) {
			electroChartList = FXCollections.observableArrayList();
			electroChartList.addAll(mainWindowController.getElectroList());
			electroChartListView.setItems(electroChartList);
			electroChartListView.setCellFactory(new ElectroModelCellFactory());
			electroChartListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
		
		if(mainWindowController.getSpectroList().size() !=0){
			spectroChartList = FXCollections.observableArrayList();
			spectroChartList.addAll(mainWindowController.getSpectroList());
			spectroChartListView.setItems(spectroChartList);
			spectroChartListView.setCellFactory(new SpectroModelCellFactory());
			spectroChartListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
	}

	public void exportToExcel() {
		
	}
	
}
