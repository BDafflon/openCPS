package fr.ucbl.disp.vfos.controller.sensor;

import javax.swing.event.EventListenerList;

import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorRawDataListener;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorStatutListener;
import fr.ucbl.disp.vfos.controller.sensor.statuts.EStatut;

public abstract class ASensor implements Perceive, Runnable {
	
	private EStatut statut;
	EventListenerList listeners = new EventListenerList();


	public SensorStatutListener[] getStatutListener() {
		return this.listeners.getListeners(SensorStatutListener.class);
	}


	public void addSensorStatutListener(SensorStatutListener listener) {
		this.listeners.add(SensorStatutListener.class, listener);
	}


	public void removeSensorStatutListener(SensorStatutListener listener) {
		this.listeners.remove(SensorStatutListener.class, listener);
	}
	
	public SensorRawDataListener[] getSensorListener() {
		return this.listeners.getListeners(SensorRawDataListener.class);
	}


	public void addPerceptionReceiverListener(SensorRawDataListener listener) {
		this.listeners.add(SensorRawDataListener.class, listener);
	}


	public void removePerceptionReceiverListener(SensorRawDataListener listener) {
		this.listeners.remove(SensorRawDataListener.class, listener);
	}


	public EStatut getStatut() {
		return statut;
	}


	public void setStatut(EStatut statut) {
		this.statut = statut;
	}
	
	protected void fireData(AData xEvent) 
	{
		for(SensorRawDataListener listener : getSensorListener()) {
			if(listener instanceof SensorRawDataListener){
				
				((SensorRawDataListener) listener).receiveSensorRawData(xEvent,this);
			}
		}
	}
	protected void fireData(EStatut s) {
		for(SensorStatutListener listener : getStatutListener()) {
			if(listener instanceof SensorStatutListener){
				((SensorStatutListener) listener).receiveSensorStatut(this.getStatut(), this);
			}
		}
	}

}