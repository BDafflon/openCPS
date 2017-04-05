
package fr.ucbl.disp.vfos;

import fr.ucbl.disp.vfos.controller.CPSController;
import java.io.IOException;

import javax.xml.ws.Endpoint;

import org.json.JSONException;
import fr.ucbl.disp.vfos.util.configurator.CPSConfiguration;
import fr.ucbl.disp.vfos.util.json.JSonLoader;


public class Main {

	public static void main(String[] args) throws IOException{	
		try {

			JSonLoader parser;
			parser = new JSonLoader("CPSController.txt");
			CPSConfiguration conf = parser.loadByIndex(0);	
			CPSController myController = new CPSController(conf);
			Endpoint.publish("http://localhost:9999/ws/CPSControllerStatut", myController);

			myController.start();
			
		}  catch (JSONException e) {
			e.printStackTrace();
		}

	
	}}

