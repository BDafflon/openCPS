package fr.ucbl.disp.vfos.controller.decision;

import fr.ucbl.disp.vfos.controller.AController;
import fr.ucbl.disp.vfos.controller.actuator.AActuator;

public abstract class ADecision {
	protected long time;
	protected AController emitter;
	protected AActuator dest;
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public AController getEmitter() {
		return emitter;
	}
	public void setEmitter(AController emitter) {
		this.emitter = emitter;
	}
	
	public AActuator getDest() {
		return dest;
	}
	public void setDest(AActuator dest) {
		this.dest = dest;
	}
	
	
}
