package fr.ucbl.disp.vfos.controller.sensor.listner;

import java.util.EventListener;

import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.sensor.ASensor;
import fr.ucbl.disp.vfos.controller.sensor.statuts.EStatut;


/**
 * This interface defined the perception event data
 * 
 * @author Baudouin Dafflon
 */
public interface SensorStatutListener extends EventListener{
	 
	void receiveSensorStatut(EStatut statut, ASensor sensor);
}

