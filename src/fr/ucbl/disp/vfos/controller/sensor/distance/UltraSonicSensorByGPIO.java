package fr.ucbl.disp.vfos.controller.sensor.distance;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.data.UltraSonicData;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorRawDataListener;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;



public class UltraSonicSensorByGPIO extends DistanceSensor {


	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput triggerGPIO;
	private GpioPinDigitalInput resultGPIO;
	public String name;
	public  double count=0;

	public double getCount(){
		return this.count;
	}
	public String getName(){
		return this.name;
	}
	public void incrementCount(){
		this.count+=1;
	}
	public UltraSonicSensorByGPIO(SensorConfiguration sensorConfig) {

		super();

		int trig= sensorConfig.getGpioTrigger();
		this.triggerGPIO = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(trig),
				"Sensor Trigger", PinState.LOW);
		int res=sensorConfig.getGpioResult();
		this.resultGPIO = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(res),
				"Sensor Result", PinPullResistance.PULL_DOWN);

		this.name=sensorConfig.getName();
	}




	public UltraSonicData perceive(GpioPinDigitalOutput out,GpioPinDigitalInput in) throws InterruptedException {

		long start = 0;

		UltraSonicData USdata = new UltraSonicData(-1,this);


		out.high();
		Thread.sleep(15);
		out.low();

		while (in.isLow()) {
			start = System.nanoTime();
		}

		while (in.isHigh()) {


		}
		USdata.setDistance(( System.nanoTime()- start) / 68000);
		//System.out.println(String.valueOf(USdata.getDistance())+ this.name );
		return USdata;

	}
	public void run() {
		try {

			while (!Thread.currentThread().isInterrupted()){

				UltraSonicData data1 = perceive(this.triggerGPIO,this.resultGPIO);

				this.fireData(data1);

			}
			Thread.sleep(50) ;

		}  catch (InterruptedException e) {

			Thread.currentThread();
			Thread.interrupted() ;
		}
	}

	public  void cancel() {


		Thread.currentThread().interrupt() ;
	}
	public AData perceive() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}



}