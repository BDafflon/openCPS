package fr.ucbl.disp.vfos.controller.sensor.listner;

import java.util.EventListener;

import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.sensor.ASensor;




/**
 * This interface defined the perception event data
 * 
 * @author Baudouin Dafflon
 */
public interface SensorProcessedDataListener extends EventListener{
	 
	void receiveSensorProcessedData(AData data);
}
