
package fr.ucbl.disp.vfos;

import fr.ucbl.disp.vfos.controller.CPSController;
import java.io.IOException;

import javax.xml.ws.Endpoint;

import org.json.JSONException;
import fr.ucbl.disp.vfos.util.configurator.CPSConfiguration;
import fr.ucbl.disp.vfos.util.json.JSonLoader;


public class Main {
/*
 * 
-Dcom.sun.management.jmxremote 
-Dcom.sun.management.jmxremote.authenticate=false 
-Dcom.sun.management.jmxremote.ssl=false 
-Dcom.sun.management.jmxremote.port=10200
 */

	
	public static void main(String[] args) throws IOException{
		try {

			if(args.length !=1){
				System.err.println("Argument invalide ");
				System.err.println("Utilisation : java -jar openCPS ./configuration/file");

			}
			else{
				JSonLoader parser;
				parser = new JSonLoader(args[0]);
				CPSConfiguration conf = parser.loadByIndex(0);
				CPSController myController = new CPSController(conf);
				Endpoint.publish("http://localhost:9999/ws/CPSControllerStatut", myController);

				myController.start();
			}
		}  catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}}

