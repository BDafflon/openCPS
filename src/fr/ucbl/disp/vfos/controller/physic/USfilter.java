package fr.ucbl.disp.vfos.controller.physic;


import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.data.UltraSonicData;
import fr.ucbl.disp.vfos.controller.sensor.ASensor;
import fr.ucbl.disp.vfos.controller.sensor.UltraSonicSensorByGPIO;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorProcessedDataListener;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;


public class USfilter extends APhysicFilter {

	protected UltraSonicData data;
	protected UltraSonicSensorByGPIO US;
	protected boolean processed = true;
	private double oldDistance1=200;
	private double Distance1=200;
	private double sigma; 



	public USfilter(SensorConfiguration sensorConfig) {
		
		sigma=sensorConfig.getSigma();
	}

	protected void fireData(UltraSonicData data) 
	{
		for(SensorProcessedDataListener listener : getSensorListener()) {
			
			if(listener instanceof SensorProcessedDataListener){
				
				((SensorProcessedDataListener) listener).receiveSensorProcessedData(data);
			}
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		try {

			while (!Thread.currentThread().isInterrupted()){

				if(!this.processed){
					
					this.processed = true;

					Distance1=this.data.getDistance();
					if (newDetection(oldDistance1, Distance1))
					{
						US.incrementCount();
				

						this.fireData(new UltraSonicData(Distance1, US));
					}
					oldDistance1=Distance1;}



				Thread.sleep(100) ;
			}

		}  catch (InterruptedException e) {

				Thread.currentThread();
				Thread.interrupted() ;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	static boolean newDetection(double oldValue, double newValue)
	{ 
		if(oldValue>100 && newValue<20 && Math.abs(oldValue - newValue)>20)

		{
			return true;}
		else
			return false;
	}

	public  void cancel() {


	Thread.currentThread().interrupt() ;
	}



	public void receiveSensorRawData(AData data, ASensor Ultasonic) {
	
		if(data instanceof UltraSonicData){
		//	System.out.println(((UltraSonicData) data).getDistance()+"coucou");
			this.processed = false;
			this.data = (UltraSonicData) data;
			
		}
		if(Ultasonic instanceof UltraSonicSensorByGPIO){
			this.US = (UltraSonicSensorByGPIO) Ultasonic;
		}
        
	}

	

	

	
}