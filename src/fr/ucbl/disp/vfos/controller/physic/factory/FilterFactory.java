package fr.ucbl.disp.vfos.controller.physic.factory;

import fr.ucbl.disp.vfos.controller.data.factory.DataFactory;
import fr.ucbl.disp.vfos.controller.physic.APhysicFilter;
import fr.ucbl.disp.vfos.controller.physic.USfilter;
import fr.ucbl.disp.vfos.controller.sensor.SensorType;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;

public class FilterFactory implements EngineCreator {
	private static FilterFactory instance;

	private FilterFactory(){

	}

	public static FilterFactory getInstance(){
		synchronized (DataFactory.class) {
			if(instance == null)
				instance = new FilterFactory();
		}
		return instance;
	}
	
	public APhysicFilter filterCreator(SensorConfiguration sensorConfig) {
		SensorType type= SensorType.valueOf(sensorConfig.getType());
      if(type == SensorType.ULTRASON)
    	 
			return new USfilter(sensorConfig);
		return null;
 
	}

	public APhysicFilter filterCreator(SensorType type) {
		// TODO Auto-generated method stub
		return null;
	}
}