package model;

import java.util.ArrayList;

import javafx.scene.chart.XYChart;

public class ElectroModel {
	private String name = "NoName";
	private ArrayList<Double> axisX = new ArrayList<Double>();
	private ArrayList<Double> axisY = new ArrayList<Double>();
	private XYChart.Series series = new XYChart.Series();
	
	public ElectroModel () {
	}
	
	public ElectroModel (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
					getAxisX(i).toString(),
					getAxisY(i)
					));
		}
	}
	
	public XYChart.Series getSeries() {
		return series;
	}
}
