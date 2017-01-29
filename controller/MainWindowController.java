package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
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
	@FXML private NumberAxis yAxis;
	@FXML private NumberAxis xAxis;
	
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
		File selectedFile = fileChooser.showOpenDialog(primaryStage);

		try {
			ElectroModel chart = new ElectroModel();
			chart.setName(selectedFile.getName());
			Scanner in = new Scanner(selectedFile);
			in.nextLine();
			in.nextLine();
			DecimalFormat df = new DecimalFormat("#");
	        df.setMaximumFractionDigits(8);
	        
			while (in.hasNext()) {
				chart.addAxisX(in.nextDouble());
				chart.addAxisY(Double.parseDouble(in.next()));
			}
			
			electroChartList.add(chart);
			chart.createSeries();
			mainChart.getData().addAll(chart.getSeries());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}
