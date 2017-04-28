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

	private double distance;
	private double desiredDistance;



	public UltraSonicSensorByGPIO(SensorConfiguration sensorConfig) {

		super();

		int trig= sensorConfig.getGpioTrigger();
		this.triggerGPIO = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(trig),
				"Sensor Trigger", PinState.LOW);
		int res=sensorConfig.getGpioResult();
		this.resultGPIO = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(res),
				"Sensor Result", PinPullResistance.PULL_DOWN);

		this.setName(sensorConfig.getName());
		this.distance=sensorConfig.getDistance();
		this.desiredDistance=sensorConfig.getDesiredDistance();
	}




	public UltraSonicData perceive(GpioPinDigitalOutput out,GpioPinDigitalInput in) throws InterruptedException {

		long start = 0;

		UltraSonicData USdata = new UltraSonicData(-1,this);


		out.high();
		Thread.sleep(10);
		out.low();

		int cpt=0;
		while (in.isLow()) {

			start = System.nanoTime();
			cpt++;
			if(cpt>1000){
				System.err.println(this.getName()+"lock");
				return null;
			}

		}

		while (in.isHigh()) {

		}

		USdata.setDistance(( System.nanoTime()- start) / 68000.0);

		this.distance=(System.nanoTime()- start) / 68000;
		//System.out.println(String.valueOf(USdata.getDistance())+ "distance="+this.distance +"desired distance= "+ this.desiredDistance);
		return USdata;

	}
	public void run() {
		try {

			while (!Thread.currentThread().isInterrupted()){

				UltraSonicData data1 = perceive(this.triggerGPIO,this.resultGPIO);
				
				if(data1!=null)
					this.fireData(data1);
				Thread.sleep(50) ;
			}

		}  catch (InterruptedException e) {

			Thread.currentThread();
			Thread.interrupted() ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}


	public  void cancel() {


		Thread.currentThread().interrupt() ;

	}

	public AData perceive() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}




	public double getDistance() {
		return distance;
	}




	public void setDistance(double distance) {
		this.distance = distance;
	}




	public double getDesiredDistance() {
		return desiredDistance;
	}




	public void setDesiredDistance(double desiredDistance) {
		this.desiredDistance = desiredDistance;
	}



}