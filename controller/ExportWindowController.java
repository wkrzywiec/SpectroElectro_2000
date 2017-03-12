package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ElectroModel;
import model.ElectroModelCellFactory;
import model.SpectroModel;
import model.SpectroModelCellFactory;

public class ExportWindowController {

	private MainWindowController mainWindowController;
	private Stage exportWindow;
	private ObservableList<ElectroModel> electroChartList;
	private ObservableList<SpectroModel> spectroChartList;

	@FXML private ListView<ElectroModel> electroChartListView;
	@FXML private ListView<SpectroModel> spectroChartListView;
	@FXML Button btnExport;
	
	public void setStage(Stage exportWindow){
		this.exportWindow = exportWindow;
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
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save to File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("File MS Excel (*.xls)", "*.xls"));
		File file = fileChooser.showSaveDialog(exportWindow);
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
			ElectroModel modelToSave = electroChartList.get(0);
			out.write("Sample Name \t" + modelToSave.getSampleName() + "\n");
			out.write("Scan Name \t" + modelToSave.getScanName() + "\n");
			out.write("Scan Description \t" + modelToSave.getDescription() + "\n");
			out.write("\n");
			for (int i = 0; i< modelToSave.getPointCount(); i++){
				out.write(modelToSave.getAxisX(i).toString());
				out.write("\t");
				out.write(modelToSave.getAxisY(i).toString());
				out.write("\t");
				out.write("\n");
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out!=null) out.close();
			//exportWindow.close();
		}
	}
	
}
