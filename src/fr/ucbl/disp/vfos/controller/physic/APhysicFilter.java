package fr.ucbl.disp.vfos.controller.physic;
import javax.swing.event.EventListenerList;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorProcessedDataListener;
import fr.ucbl.disp.vfos.controller.sensor.listner.SensorRawDataListener;

public abstract class APhysicFilter implements Runnable, SensorRawDataListener{

	EventListenerList listeners = new EventListenerList();

	public SensorProcessedDataListener[] getProcessedSensorListener() {
		return this.listeners.getListeners(SensorProcessedDataListener.class);
	}


	public void addPerceptionReceiverProcessedListener(SensorProcessedDataListener listener) {
		this.listeners.add(SensorProcessedDataListener.class, listener);
	}


	public void removePerceptionReceiverProcessedListener(SensorProcessedDataListener listener) {
		this.listeners.remove(SensorProcessedDataListener.class, listener);
	}
	
  public void applyPhysic(){
  }

}