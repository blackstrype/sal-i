package edu.jcu.sali.index.server.sensorlist;

import java.util.ArrayList;

import edu.jcu.sali.index.client.sensorlist.SensorListService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SensorListServiceImpl extends RemoteServiceServlet implements SensorListService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<String> getSensorList() throws Exception {
		ArrayList<String> sensorList = new ArrayList<String>();
		//TODO: still to implement
		for(int i=0; i<30; i++) {
			sensorList.add("Sensor " + i);
		}
			
		return sensorList;
	}

}
