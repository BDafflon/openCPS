package fr.ucbl.disp.vfos.controller.data;

public class itemCount extends ProcessedData {
	int count;
	public itemCount(int i, String SensorID) {
	count=i;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



	public void increment() {
		count+=1;
		
	}
	

}
