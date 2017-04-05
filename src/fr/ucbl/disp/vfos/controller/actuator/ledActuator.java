package fr.ucbl.disp.vfos.controller.actuator;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import fr.ucbl.disp.vfos.util.configurator.ActuatorConfiguration;


public class ledActuator extends warningActuator {
	
	
	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput pin;
	public String name;

	public ledActuator(ActuatorConfiguration actuatorConfig) {

		super();
		int res=actuatorConfig.getGpioResult();
		this.name=actuatorConfig.getName();
	this.pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(res),this.name, PinState.LOW);
	
		System.out.println("Hello I'm a led actuator my name is "+ name);
	}
	
	
	public void turnOnLed()
	{ 
		pin.high();
	}
	public void turnOffLed()
	{ 
		pin.low();
	}

}
