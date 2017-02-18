package model;

public class ChartDataModel {

	private String sample, scan, description, date;
	
	public ChartDataModel (String sample, String scan, String description, String date){
		this.sample = sample;
		this.scan = scan;
		this.description = description;
		this.date = date;
	}
	
	public String getScanName(){
		return this.scan;
	}
	
	public String getSampleName(){
		return this.sample;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getDate(){
		return this.date;
	}
}
