package fr.ucbl.disp.vfos.controller.data;

import fr.ucbl.disp.vfos.controller.sensor.ASensor;


public abstract class AData {
	protected ASensor emitter;

	public ASensor getEmitter() {

		return this.emitter;
	}

}