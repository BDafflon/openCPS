package fr.ucbl.disp.vfos.controller.sensor.factory;

import fr.ucbl.disp.vfos.controller.data.factory.DataFactory;
import fr.ucbl.disp.vfos.controller.sensor.ASensor;
import fr.ucbl.disp.vfos.controller.sensor.SensorType;
import fr.ucbl.disp.vfos.controller.sensor.distance.UltraSonicSensorByGPIO;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;


public class SensorFactory implements SensorCreator {

	private static SensorFactory instance;

	private SensorFactory(){

	}

	public static SensorFactory getInstance(){
		synchronized (DataFactory.class) {
			if(instance == null)
				instance = new SensorFactory();
		}
		return instance;
	}

	public ASensor sensorCreator( SensorConfiguration sensorConfig) {
		SensorType type= SensorType.valueOf(sensorConfig.getType());

		if(type == SensorType.ULTRASON)
			return new UltraSonicSensorByGPIO(sensorConfig);

		return null;
	}

	public ASensor sensorCreator(SensorType type) {
		// TODO Auto-generated method stub
		return null;
	}
}