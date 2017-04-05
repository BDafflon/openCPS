package fr.ucbl.disp.vfos.controller.data.factory;

import fr.ucbl.disp.vfos.controller.data.AData;

public class DataFactory implements DataCreator {

	private static DataFactory instance;
	
	private DataFactory(){
		
	}
	
	public static DataFactory getInstance(){
		synchronized (DataFactory.class) {
			if(instance == null)
				instance = new DataFactory();
		}
		return instance;
	}
	
	public AData dataCreator() {
		return null;
				
	}
}