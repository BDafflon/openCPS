package fr.ucbl.disp.vfos.controller.actuator;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import fr.ucbl.disp.vfos.util.configurator.ActuatorConfiguration;


public class LedActuator extends warningActuator {


	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput pin;


	public LedActuator(ActuatorConfiguration actuatorConfig) {

		super();
		int res=actuatorConfig.getGpioResult();
		this.name=actuatorConfig.getName();
		this.pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(res),this.name, PinState.LOW);

		 
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
