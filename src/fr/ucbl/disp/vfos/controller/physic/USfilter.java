package fr.ucbl.disp.vfos.controller.physic;


import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.data.DistanceData1D;
import fr.ucbl.disp.vfos.controller.data.UltraSonicData;
import fr.ucbl.disp.vfos.controller.sensor.ASensor;
import fr.ucbl.disp.vfos.controller.sensor.distance.UltraSonicSensorByGPIO;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorProcessedDataListener;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorRawDataListener;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;


public class USfilter extends APhysicFilter {

	protected UltraSonicData data;
	protected boolean processed = true;
	private double sigma;



	public USfilter(SensorConfiguration sensorConfig) {

		sigma=sensorConfig.getSigma();
	}

	protected void fireData(AData data) 
	{
		for(SensorProcessedDataListener listener : getProcessedSensorListener()) {
			if(listener instanceof SensorProcessedDataListener){
				//System.out.println("fireDataFiltre "+data.toString());
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

					

					this.fireData(new DistanceData1D(this.data.getDistance(), this.data.getEmitter()));

					this.data=null;
					this.processed= true;
				}



				Thread.sleep(60) ;
			}

		}  catch (InterruptedException e) {

			Thread.currentThread();
			Thread.interrupted() ;
		}
	}



	//	static boolean newDetection(double oldValue, double newValue)
	//	{
	//		if(oldValue>100 && newValue<20 && Math.abs(oldValue - newValue)>20)
	//
	//		{
	//			return true;}
	//		else
	//			return false;
	//	}

	public  void cancel() {


		Thread.currentThread().interrupt() ;
	}



	public void receiveSensorRawData( AData data) {

		if(data instanceof UltraSonicData){
			this.processed = false;
			this.data = (UltraSonicData) data;

		}

	}





}