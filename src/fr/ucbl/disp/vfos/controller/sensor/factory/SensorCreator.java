package fr.ucbl.disp.vfos.controller.sensor.factory;

import fr.ucbl.disp.vfos.controller.sensor.ASensor;
import fr.ucbl.disp.vfos.controller.sensor.SensorType;

public interface SensorCreator {

  public ASensor sensorCreator(SensorType type);

}