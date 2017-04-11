package fr.ucbl.disp.vfos.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.jws.WebService;

import org.json.JSONObject;

import fr.ucbl.disp.vfos.controller.actuator.AActuator;
import fr.ucbl.disp.vfos.controller.actuator.LedActuator;
import fr.ucbl.disp.vfos.controller.actuator.factory.actuatorFactory;
import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.decision.ADecision;
import fr.ucbl.disp.vfos.controller.decision.LedDecision;
import fr.ucbl.disp.vfos.controller.physic.APhysicFilter;
import fr.ucbl.disp.vfos.controller.physic.factory.FilterFactory;
import fr.ucbl.disp.vfos.controller.sensor.ASensor;
import fr.ucbl.disp.vfos.controller.sensor.distance.UltraSonicSensorByGPIO;
import fr.ucbl.disp.vfos.controller.sensor.factory.SensorFactory;
import fr.ucbl.disp.vfos.controller.service.CPSLocalService;
import fr.ucbl.disp.vfos.util.ThreadUtil;
import fr.ucbl.disp.vfos.util.configurator.ActuatorConfiguration;
import fr.ucbl.disp.vfos.util.configurator.CPSConfiguration;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;

@WebService(endpointInterface = "fr.ucbl.disp.vfos.controller.service.CPSLocalService")

public class CPSController extends AController implements CPSLocalService{


	private ArrayList<ASensor> sensorList= new ArrayList<ASensor>();
	private ArrayList<APhysicFilter> filterList= new ArrayList<APhysicFilter>();
	private ArrayList<AActuator> actuatorList= new ArrayList<AActuator>();
	private ArrayDeque<ADecision> decisionList = new ArrayDeque<ADecision>();


	public CPSController(CPSConfiguration conf) {
		ArrayList<SensorConfiguration> sensorConfList= conf.getSensorList();
		ArrayList<ActuatorConfiguration> actuatorConfList= conf.getActuatorList();

		for (int i=0; i<sensorConfList.size(); i++){
			filterList.add(FilterFactory.getInstance().filterCreator( sensorConfList.get(i)));
			sensorList.add(SensorFactory.getInstance().sensorCreator( sensorConfList.get(i)));
			sensorList.get(i).addPerceptionReceiverListener(filterList.get(i));
			filterList.get(i).addPerceptionReceiverListener(this);
		}
		for (int i=0; i<actuatorConfList.size(); i++){
			actuatorList.add(actuatorFactory.getInstance().actuatorCreator( actuatorConfList.get(i)));
		}
	}

	public void start(){
		for (int i=0; i<filterList.size(); i++){
			ThreadUtil.execute( filterList.get(i));
			ThreadUtil.execute(sensorList.get(i));
		}
	}

	public void receiveSensorProcessedData(AData d) {
		if (d.getEmitter() instanceof UltraSonicSensorByGPIO)
		{
			System.out.println("ICI c'est le controleur, count="+((UltraSonicSensorByGPIO) d.getEmitter()).getCount()+"nom=  "+((UltraSonicSensorByGPIO) d.getEmitter()).getName());
			doDecision(d);
		}
	}

	public void doDecision(AData d) {
		// TODO Auto-generated method stub
		LedDecision l = new LedDecision(this,System.nanoTime(), ((UltraSonicSensorByGPIO) d.getEmitter()).getName());


		if(l.getEmitter().getName().equals( "US1")){
			l.enable(true);
			l.setDest(actuatorList.get(1));
			this.decisionList.add(l);
			doApplication(l);
		}

		if(l.getEmitter().getName().equals( "US2"))
		{
			l.enable(true);
			l.setDest(actuatorList.get(0));
			this.decisionList.add(l);
			doApplication(l);
		}

	}

	public void doApplication(ADecision a) {
		// TODO Auto-generated method stub
		try {
			if(a instanceof LedDecision){
				LedDecision d = (LedDecision)a;

				if(d.getDest() instanceof LedActuator){
					LedActuator l = (LedActuator) d.getDest();

					if(d.isEnable()){
						l.turnOnLed();
						Thread.sleep(100);
						l.turnOffLed();
					}
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//TODO
	// WEB SERVICES
	public String getCPSStatutJSON(String name) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject(this);
		String myJson  =jsonObject.toString();
		System.out.println(myJson);
		return myJson;
	}
	public String getLastDecision(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public String get5LastDecision(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public String setCPSStatutJSON(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPredictDecision(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}