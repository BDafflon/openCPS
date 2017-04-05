package fr.ucbl.disp.vfos.util.configurator;

import java.util.ArrayList;

public class CPSConfiguration {

	private String id;
	private ArrayList<SensorConfiguration> sensorList = new ArrayList<SensorConfiguration>();
	private ArrayList<ActuatorConfiguration> actuatorList = new ArrayList<ActuatorConfiguration>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<SensorConfiguration> getSensorList() {
		return sensorList;
	}
   
	public void setActuatorList(ArrayList<ActuatorConfiguration> actuatorList) {
		this.actuatorList = actuatorList;
	}
	public ArrayList<ActuatorConfiguration> getActuatorList() {
		return actuatorList;
	}
   
	public void setSensorList(ArrayList<SensorConfiguration> sensorList) {
		this.sensorList = sensorList;
	}

	public void setID(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void addSensor(SensorConfiguration sensor1) {
		// TODO Auto-generated method stub
		this.sensorList.add(sensor1);
		
	}

	public void addActuator(ActuatorConfiguration actuator) {
		this.actuatorList.add(actuator);
		
	}

}
