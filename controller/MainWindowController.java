package controller;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ChartDataModel;
import model.ElectroModel;
import model.ElectroModelCell;
import model.ElectroModelCellFactory;

public class MainWindowController {

	private Stage primaryStage;
	
	@FXML private ListView<ElectroModel> chartListView;
	@FXML private MenuItem menuLoadChartData;
	@FXML private ScatterChart<Number, Number> mainChart;
	@FXML private NumberAxis yAxis, xAxis;
	@FXML private TextField txtSample, txtScan;
	@FXML private TextArea txtDescription;
	
	private ObservableList<ElectroModel> electroChartList = FXCollections.observableArrayList();

	public void initialize() {
		chartListView.setItems(electroChartList);
		chartListView.setCellFactory(new ElectroModelCellFactory());
		yAxis.setAutoRanging(true);
		xAxis.setAutoRanging(true);
		mainChart.getStyleClass().add(getClass().getResource("/view/application.css").toExternalForm());
		
	}

	public void loadChartData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Wybierz plik z danymi");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Pliki tekstowe .ocw", "*.ocw"));
		
		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
		//File selectedFile = fileChooser.showOpenDialog(primaryStage);
		
		 if (selectedFiles != null) {
			 for (File file :selectedFiles) {
				 try {
						ElectroModel electroModel = new ElectroModel();
						electroModel.setScanName(file.getName());
						Scanner in = new Scanner(file);
						in.nextLine();
						in.nextLine();
						DecimalFormat df = new DecimalFormat("#");
				        df.setMaximumFractionDigits(8);
				        
						while (in.hasNext()) {
							electroModel.addAxisX(in.nextDouble());
							electroModel.addAxisY(Double.parseDouble(in.next()));
						}
						
						electroChartList.add(electroModel);
						electroModel.createSeries();
						mainChart.getData().addAll(electroModel.getSeries());
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
			 }
		 }
		
	}

	public void setTextFields() {
		txtSample.setText(chartListView.getSelectionModel().getSelectedItem().getSampleName());
		txtScan.setText(chartListView.getSelectionModel().getSelectedItem().getScanName());
		txtDescription.setText(chartListView.getSelectionModel().getSelectedItem().getDescription());
	}
	
	public void changeSampleTextFields() {
		chartListView.getSelectionModel().getSelectedItem().setSampleName(txtSample.getText());
	}
	
	public void changeScanTextFields() {
		chartListView.getSelectionModel().getSelectedItem().setScanName(txtScan.getText());
	}
	
	public void changeDescriptionTextFields() {
		chartListView.getSelectionModel().getSelectedItem().setDescription(txtDescription.getText());
	}
}
