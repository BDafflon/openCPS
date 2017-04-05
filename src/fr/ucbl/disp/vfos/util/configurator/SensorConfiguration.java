package fr.ucbl.disp.vfos.util.configurator;


public class SensorConfiguration {

	private String id;
	private String name;
	private String type;
	private int gpioResult;
	private int gpioTrigger;
	private double frequency;
	private double sigma;
	
	public void setID(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public double getFrequency() {
		return frequency;
	}
	public double getSigma() {
		return sigma;
	}
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
	public void setGPIOResult(int gpioResult) {
		// TODO Auto-generated method stub
		this.gpioResult = gpioResult;
	}
	public void setGPIOTrigger(int gpioTrigger) {
		// TODO Auto-generated method stub
		this.gpioTrigger = gpioTrigger;
	}
	public void setFrequency(double frequency) {
		// TODO Auto-generated method stub
		this.frequency=frequency;
	}
	public void setSigma(double sigma) {
		// TODO Auto-generated method stub
		this.sigma = sigma;
	}

}
