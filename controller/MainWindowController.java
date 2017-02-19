package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ChartDataModel;
import model.DataConverter;
import model.ElectroModel;
import model.ElectroModelCell;
import model.ElectroModelCellFactory;
import model.SpectroModel;
import model.SpectroModelCellFactory;

public class MainWindowController {

	private Stage primaryStage;
	
	@FXML private ListView<ElectroModel> electroChartListView;
	@FXML private ListView<SpectroModel> spectroChartListView;
	@FXML private MenuItem menuLoadChartData;
	@FXML private ScatterChart<Number, Number> mainChart;
	@FXML private NumberAxis yAxis, xAxis;
	@FXML private TextField txtSample, txtScan;
	@FXML private TextArea txtDescription;
	@FXML private CheckBox checkChart;
	@FXML private MenuItem menuAddSpectroChart, menuRemoveSpectroChart, menuAddElectroChart, menuRemoveElectroChart, menuClose, menuExportToExcel;
	
	private ObservableList<ElectroModel> electroChartList = FXCollections.observableArrayList();
	private ObservableList<SpectroModel> spectroChartList = FXCollections.observableArrayList();

	public void initialize() {
		electroChartListView.setItems(electroChartList);
		electroChartListView.setCellFactory(new ElectroModelCellFactory());
		electroChartListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		spectroChartListView.setItems(spectroChartList);
		spectroChartListView.setCellFactory(new SpectroModelCellFactory());
		spectroChartListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		yAxis.setAutoRanging(true);
		xAxis.setAutoRanging(true);
		mainChart.getStyleClass().add(getClass().getResource("/view/application.css").toExternalForm());
	}
	
	public void setStage(Stage primaryStage){
		this.primaryStage = primaryStage;
	}
	
	public void closeStage() {
		primaryStage.close();
	}

	public void loadChartData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select data file");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("OCW file (Cyclic voltammograms) .ocw", "*.ocw"));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV file (Absorption spectra) .csv", "*.csv"));
		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
		DataConverter dataConverter = new DataConverter();
		if (selectedFiles != null) {
			 for (File file :selectedFiles) {
				if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".ocw")){
					ElectroModel electroModel = dataConverter.convertFileToElectro(file);
					electroChartList.add(electroModel);
					electroModel.createSeries();
				} else if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".csv")) {
					SpectroModel spectroModel = dataConverter.convertFileToSpectro(file);
					spectroChartList.add(spectroModel);
					spectroModel.createSeries();
					for (int i =0; i<spectroModel.getPointCount(); i++){
						System.out.print(spectroModel.getAxisX(i) + " ");
						System.out.println(spectroModel.getAxisY(i));
					}	
				}
			 }
		 }
	}

	public void setTextFields() {
		txtSample.setText(electroChartListView.getSelectionModel().getSelectedItem().getSampleName());
		txtScan.setText(electroChartListView.getSelectionModel().getSelectedItem().getScanName());
		txtDescription.setText(electroChartListView.getSelectionModel().getSelectedItem().getDescription());
	}
	
	public void changeSampleTextFields() {
		electroChartListView.getSelectionModel().getSelectedItem().setSampleName(txtSample.getText());
	}
	
	public void changeScanTextFields() {
		electroChartListView.getSelectionModel().getSelectedItem().setScanName(txtScan.getText());
	}
	
	public void changeDescriptionTextFields() {
		electroChartListView.getSelectionModel().getSelectedItem().setDescription(txtDescription.getText());
	}
	
	public void addSpectroModelToChart() {
		mainChart.getData().addAll(spectroChartListView.getSelectionModel().getSelectedItem().getSeries());
	}
	
	public void removeSpectroModelFromChart() {
		mainChart.getData().remove(spectroChartListView.getSelectionModel().getSelectedItem().getSeries());
	}
	
	public void addElectroModelToChart() {
		mainChart.getData().addAll(electroChartListView.getSelectionModel().getSelectedItem().getSeries());
	}
	
	public void removeElectroModelFromChart() {
		mainChart.getData().remove(electroChartListView.getSelectionModel().getSelectedItem().getSeries());
	}
	
	public void exportToExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz do pliku");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Pliki MS Excel", "*.xls"));
		File file = fileChooser.showSaveDialog(primaryStage);
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
		}
	}
}
