package fr.ucbl.disp.vfos.util.configurator;

public class ActuatorConfiguration {
	private String id;
	private String name;
	private String type;
	private int gpioResult;
	private int gpioTrigger;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGpioResult() {
		return gpioResult;
	}
	public void setGpioResult(int gpioResult) {
		this.gpioResult = gpioResult;
	}
	public int getGpioTrigger() {
		return gpioTrigger;
	}
	public void setGpioTrigger(int gpioTrigger) {
		this.gpioTrigger = gpioTrigger;
	}

}
