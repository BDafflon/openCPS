package fr.ucbl.disp.vfos.controller.actuator.factory;

import fr.ucbl.disp.vfos.controller.actuator.AActuator;
import fr.ucbl.disp.vfos.controller.actuator.actuatorType;
import fr.ucbl.disp.vfos.controller.actuator.ledActuator;
import fr.ucbl.disp.vfos.controller.data.factory.DataFactory;
import fr.ucbl.disp.vfos.util.configurator.ActuatorConfiguration;


public class actuatorFactory implements actuatorCreator {

	private static actuatorFactory instance;

	private actuatorFactory(){

	}

	public static actuatorFactory getInstance(){
		synchronized (DataFactory.class) {
			if(instance == null)
				instance = new actuatorFactory();
		}
		return instance;
	}

	public AActuator actuatorCreator( ActuatorConfiguration actuatorConfig) {
		actuatorType type= actuatorType.valueOf(actuatorConfig.getType());
		
		if(type == actuatorType.LED)
		return new ledActuator(actuatorConfig);
		
		return null;
	}

	

	public AActuator sensorCreator(actuatorType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
