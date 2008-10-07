package edu.jcu.sali.index.server.sensorlist;

import java.util.ArrayList;

import javax.ejb.EJB;

import edu.jcu.sali.index.client.sensorlist.SensorListService;
import edu.sal.sali.ejb.ClientLocal;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SensorListServiceImpl extends RemoteServiceServlet implements SensorListService {

	@EJB
	ClientLocal client;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<String> getSensorList() throws Exception {
		return client.getSensorList();
//		ArrayList<String> sensorList = new ArrayList<String>();
//		for(int i=0;i<10;i++) {
//			sensorList.add(i+"##Sensor" + (i+1));
//		}
//		return sensorList;
	}

}
