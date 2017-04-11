package fr.ucbl.disp.vfos.controller.data;

import fr.ucbl.disp.vfos.controller.sensor.ASensor;


public class UltraSonicData extends RawData {

	private double distance;


	public UltraSonicData(double distance, ASensor us) {
		// TODO Auto-generated constructor stub
		this.distance = distance;
		this.emitter = us;
	}



	public double getDistance() {
	    return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}



	public String getCount() {
		// TODO Auto-generated method stub
		return null;
	}










}
