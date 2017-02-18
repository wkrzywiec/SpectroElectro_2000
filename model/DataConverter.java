package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class DataConverter {

	public ElectroModel convertFile(File file){
		
		ElectroModel electroModel = new ElectroModel();
		electroModel.setScanName(file.getName());
		
		try {
			Scanner in = new Scanner(file);
			DecimalFormat df = new DecimalFormat("#");
	        df.setMaximumFractionDigits(8);
			while (in.hasNext()) {
				if (in.hasNextDouble()){
					electroModel.addAxisX(in.nextDouble());
					electroModel.addAxisY(Double.parseDouble(in.next()));
				} else {
					in.nextLine();
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return electroModel;
	}
}
