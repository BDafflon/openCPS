package fr.ucbl.disp.vfos.util.json;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import org.json.*;

import fr.ucbl.disp.vfos.util.configurator.ActuatorConfiguration;
import fr.ucbl.disp.vfos.util.configurator.CPSConfiguration;
import fr.ucbl.disp.vfos.util.configurator.EGPIO;
import fr.ucbl.disp.vfos.util.configurator.SensorConfiguration;
import fr.ucbl.disp.vfos.util.file.EIOFileType;
import fr.ucbl.disp.vfos.util.file.IOFile;

public class JSonLoader {

	JSONObject data;
	HashMap<String,CPSConfiguration> configurationList = new HashMap<String,CPSConfiguration>();

	public JSonLoader(String url) throws IOException, JSONException {

		IOFile f = new IOFile(url, EIOFileType.READ);
		data = new JSONObject(f.readFile());

		JSONArray  cpsList = data.getJSONArray("CPSControllerList");

		for (int i = 0; i < cpsList.length(); i++)
		{
			CPSConfiguration cps1 = new CPSConfiguration();

			cps1.setID(cpsList.getJSONObject(i).getString("ID"));
			JSONArray sensorList = cpsList.getJSONObject(i).getJSONArray("SensorsList");
			for (int j = 0; j < sensorList.length(); j++)
			{
				SensorConfiguration sensor1 = new SensorConfiguration();
				sensor1.setID(sensorList.getJSONObject(j).getString("sensorId"));
				sensor1.setName(sensorList.getJSONObject(j).getString("sensorName"));
				sensor1.setType(sensorList.getJSONObject(j).getString("sensorType"));
				String gpioT= sensorList.getJSONObject(j).getString("triggerPin");
				sensor1.setGPIOTrigger(Integer.parseInt(gpioT));
				String gpioR= sensorList.getJSONObject(j).getString("resultPin");
				sensor1.setGpioResult(Integer.parseInt(gpioR));

	
				sensor1.setFrequency(Double.parseDouble(sensorList.getJSONObject(j).getString("Frequency")));
				sensor1.setSigma(Double.parseDouble(sensorList.getJSONObject(j).getString("Sigma")));

				cps1.addSensor(sensor1);

			}
			JSONArray actuatorList = cpsList.getJSONObject(i).getJSONArray("ActuatorList");
			for (int j = 0; j < actuatorList.length(); j++)
			{
				ActuatorConfiguration actuator = new ActuatorConfiguration();
				actuator.setId(actuatorList.getJSONObject(j).getString("actuatorId"));
				actuator.setName(actuatorList.getJSONObject(j).getString("actuatorName"));
				actuator.setType(actuatorList.getJSONObject(j).getString("actuatorType"));
				String gpioR= actuatorList.getJSONObject(j).getString("resultPin");
				actuator.setGpioResult(Integer.parseInt(gpioR));
				cps1.addActuator(actuator);
              
			}
			this.configurationList.put(cps1.getID(), cps1);
		}
		System.out.println(f.readFile());
	}

	public boolean existingConfiguration(String key){
		return this.configurationList.containsKey(key);
	}


	public CPSConfiguration loadByID(String key) throws JSONException {

		return this.configurationList.get(key);
	}

	
	public CPSConfiguration loadByIndex(int index) throws JSONException {
		List<CPSConfiguration> keys = new ArrayList<CPSConfiguration>(configurationList.values());
		return (CPSConfiguration) keys.get(index);
	}

}
