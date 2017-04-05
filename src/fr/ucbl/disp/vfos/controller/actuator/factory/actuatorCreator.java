package fr.ucbl.disp.vfos.controller.actuator.factory;

import fr.ucbl.disp.vfos.controller.actuator.AActuator;
import fr.ucbl.disp.vfos.controller.actuator.actuatorType;


public interface actuatorCreator {
	 public AActuator sensorCreator(actuatorType type);
}
