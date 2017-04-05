package fr.ucbl.disp.vfos.controller.physic.factory;

import fr.ucbl.disp.vfos.controller.physic.APhysicFilter;
import fr.ucbl.disp.vfos.controller.sensor.SensorType;

public interface EngineCreator {

  public APhysicFilter filterCreator(SensorType type);

}