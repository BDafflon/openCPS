package fr.ucbl.disp.vfos.controller.decision;

import fr.ucbl.disp.vfos.controller.AController;

public class LedDecision extends ADecision {
	protected String led;
	protected String color;
	
	public LedDecision(AController cpsController, long nanoTime, String name) {
		this.led = name;
		this.emitter = cpsController;
		this.time = nanoTime;
	}

	public String getLed() {
		return led;
	}

	public void setLed(String led) {
		this.led = led;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
}
