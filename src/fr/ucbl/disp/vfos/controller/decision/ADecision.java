package fr.ucbl.disp.vfos.controller.decision;

import fr.ucbl.disp.vfos.controller.AController;

public abstract class ADecision {
	protected long time;
	protected AController emitter;
	
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
	
	
}
