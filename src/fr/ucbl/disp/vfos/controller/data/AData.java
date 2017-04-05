package fr.ucbl.disp.vfos.controller.data;

import fr.ucbl.disp.vfos.controller.sensor.UltraSonicSensorByGPIO;

public abstract class AData {
	protected Object emitter;

	public Object getEmitter() {
		
		return this.emitter;
	}
	
}