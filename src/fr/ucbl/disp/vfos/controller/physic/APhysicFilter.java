package fr.ucbl.disp.vfos.controller.physic;
import javax.swing.event.EventListenerList;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorProcessedDataListener;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorRawDataListener;

public abstract class APhysicFilter implements Runnable, SensorRawDataListener{

	EventListenerList listeners = new EventListenerList();

	public SensorProcessedDataListener[] getSensorListener() {
		return this.listeners.getListeners(SensorProcessedDataListener.class);
	}


	public void addPerceptionReceiverListener(SensorProcessedDataListener listener) {
		this.listeners.add(SensorProcessedDataListener.class, listener);
	}


	public void removePerceptionReceiverListener(SensorProcessedDataListener listener) {
		this.listeners.remove(SensorProcessedDataListener.class, listener);
	}
	
  public void applyPhysic(){
  }

}