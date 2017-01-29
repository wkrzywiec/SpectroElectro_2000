package model;

import java.util.ArrayList;

import javafx.scene.chart.XYChart;

public class ElectroModel {
	private String scanName, sampleName, description, date;
	private String descriptionRow;
	private ArrayList<Double> axisX = new ArrayList<Double>();
	private ArrayList<Double> axisY = new ArrayList<Double>();
	private XYChart.Series series = new XYChart.Series();
	
	public String getName() {
		return scanName;
	}
	
	public void setName(String scanName) {
		this.scanName = scanName;
	}
	
	public Double getAxisX(int i) {
		return axisX.get(i);
	}
	
	public void addAxisX(Double x) {
		axisX.add(x);
	}
	public Double getAxisY(int i) {
		return axisY.get(i);
	}
	
	public void addAxisY(Double x) {
		axisY.add(x);
	}
	
	public void createSeries() {
		
		for (int i = 0; i < axisX.size(); i++) {
			series.getData().add(new XYChart.Data(
					getAxisX(i),
					getAxisY(i)
					));
		}
	}
	
	public XYChart.Series getSeries() {
		return series;
	}
}
