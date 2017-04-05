package fr.ucbl.disp.vfos.controller;
 

import fr.ucbl.disp.vfos.controller.sensor.listner.SensorProcessedDataListener;

public abstract class AController implements SensorProcessedDataListener, Decide, Apply {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}