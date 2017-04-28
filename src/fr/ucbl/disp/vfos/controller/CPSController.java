package fr.ucbl.disp.vfos.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.jws.WebService;

import org.json.JSONObject;

import fr.ucbl.disp.vfos.controller.actuator.AActuator;
import fr.ucbl.disp.vfos.controller.actuator.LedActuator;
import fr.ucbl.disp.vfos.controller.actuator.factory.actuatorFactory;
import fr.ucbl.disp.vfos.controller.data.AData;
import fr.ucbl.disp.vfos.controller.data.DistanceData1D;
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
	private boolean verbose=true;

	public CPSController(CPSConfiguration conf) {
		ArrayList<SensorConfiguration> sensorConfList= conf.getSensorList();
		ArrayList<ActuatorConfiguration> actuatorConfList= conf.getActuatorList();

		for (int i=0; i<sensorConfList.size(); i++){
			filterList.add(FilterFactory.getInstance().filterCreator( sensorConfList.get(i)));
			sensorList.add(SensorFactory.getInstance().sensorCreator( sensorConfList.get(i)));

			sensorList.get(i).addPerceptionRawReceiverListener(filterList.get(i));
			filterList.get(i).addPerceptionReceiverProcessedListener(this);
		}

		for (int i=0; i<actuatorConfList.size(); i++){
			if(verbose)
				System.out.println(actuatorConfList.get(i).toString());
			actuatorList.add(actuatorFactory.getInstance().actuatorCreator( actuatorConfList.get(i)));
		}
	}

	public void start(){
		for (int i=0; i<filterList.size(); i++){
			ThreadUtil.execute(filterList.get(i));
			ThreadUtil.execute(sensorList.get(i));
		}
	}

	public void receiveSensorProcessedData(AData data) {

		if( data instanceof DistanceData1D){
			DistanceData1D d= (DistanceData1D)data;
			if(!verbose)
				System.out.println("receiveSensorProcessedData"+d.toString());
			if (data.getEmitter() instanceof UltraSonicSensorByGPIO)
			{
				UltraSonicSensorByGPIO us =(UltraSonicSensorByGPIO)data.getEmitter();

				doDecision(d);
			}
		}

	}

	public void doDecision(AData d) {
		// TODO Auto-generated method stub
		try{
			LedDecision l = new LedDecision(this, System.currentTimeMillis(), "");

			if( d instanceof DistanceData1D){

				DistanceData1D dist= (DistanceData1D)d;
				if(!verbose)
					System.out.println("doDecision :"+dist.toString());

				if(d.getEmitter() instanceof UltraSonicSensorByGPIO){
					UltraSonicSensorByGPIO emitter = (UltraSonicSensorByGPIO)d.getEmitter();


					if(emitter.getName().equals("US1")){

						l.setDest(this.actuatorList.get(3));
						l.enable(false);

						if(Math.abs(dist.getDataDistance()-emitter.getDesiredDistance())<2){
							l.enable(true);
						}
						else{
							l.enable(false);
						}
						doApplication(l);
					}

					if(emitter.getName().equals("US2")){

						l.setDest(this.actuatorList.get(2));
						l.enable(false);

						if(Math.abs(dist.getDataDistance()-emitter.getDesiredDistance())<2){
							l.enable(true);
						}
						else{
							l.enable(false);
						}
						doApplication(l);
					}
					if(emitter.getName().equals("US3")){

						l.setDest(this.actuatorList.get(1));
						l.enable(false);

						if(Math.abs(dist.getDataDistance()-emitter.getDesiredDistance())<2){
							l.enable(true);
						}
						else{
							l.enable(false);
						}
						doApplication(l);
					}
					if(emitter.getName().equals("US4")){

						l.setDest(this.actuatorList.get(0));
						l.enable(false);

						if(Math.abs(dist.getDataDistance()-emitter.getDesiredDistance())<2){
							l.enable(true);
						}
						else{
							l.enable(false);
						}
						doApplication(l);
					}
					if(Math.abs(dist.getDataDistance()-emitter.getDesiredDistance())>2)
						System.err.println("doDecision :"+dist.toString()+ " target "+emitter.getDesiredDistance()+ "sigma "+Math.abs(dist.getDataDistance()-emitter.getDesiredDistance()));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}




	}

	public void doApplication(ADecision a) {
		// TODO Auto-generated method stub
		try {

			if(a instanceof LedDecision){
				LedDecision d = (LedDecision)a;

				if(!verbose)
					System.out.println(d.toString());

				if(d.getDest() instanceof LedActuator){
					LedActuator l = (LedActuator) d.getDest();

					if(d.isEnable()){
						l.turnOnLed();
						Thread.sleep(100);

					}
					else
					{
						l.turnOffLed();
						Thread.sleep(100);
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