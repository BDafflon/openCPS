package fr.ucbl.disp.vfos.controller.data;

import fr.ucbl.disp.vfos.controller.sensor.ASensor;

public class DistanceData1D extends ProcessedData{
	private double distance;
	
	public DistanceData1D(double i, ASensor emitter) {
		// TODO Auto-generated constructor stub
		this.emitter = emitter;
		this.distance = i;
	}
	
	public DistanceData1D(double i) {

		this.distance = i;
	}
	

	public double getDataDistance() {
		return distance;
	}
	public void setDataDistance(double data) {
		this.distance = data;
	}

	public String toString() {
		return "Emitter name:"+this.emitter.getName()+" distance:"+this.distance;
	}
}
