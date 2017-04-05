package fr.ucbl.disp.vfos.controller.data;

public class DistanceData1D extends ProcessedData{
	private double distance;
	
	public DistanceData1D(double i) {

		this.distance = i;
	}
	

	public double getDataDistance() {
		return distance;
	}
	public void setDataDistance(double data) {
		this.distance = data;
	}

}
