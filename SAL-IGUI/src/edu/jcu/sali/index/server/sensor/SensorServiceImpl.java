package edu.jcu.sali.index.server.sensor;

import java.util.ArrayList;

import javax.ejb.EJB;

import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.jcu.sali.index.client.sensor.SensorService;
import edu.sal.sali.ejb.ClientLocal;

/**
 * Implementation of the sensor service. Communicates with the SAL-client.
 * 
 * @author Marc Hammerton
 * 
 */
public class SensorServiceImpl extends RemoteServiceServlet implements
		SensorService {

	@EJB
	ClientLocal client;

	// private TestClient client;

	public SensorServiceImpl() {
		// client = new TestClient();
	}

	/**
	 * Serial-Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see edu.jcu.sali.index.client.sensor.SensorService#getSensorList()
	 */
	public ArrayList<ArrayList<String>> getSensorList() throws Exception {
		ArrayList<ArrayList<String>> sensorList = new ArrayList<ArrayList<String>>();

		// add sensors to the arraylist
		try {
			SMLDescriptions listDesc = client.getSensorListActive();
			if (listDesc != null) {
				for (SMLDescription smlDesc : listDesc.getDescriptions()) {
					ArrayList<String> sensor = new ArrayList<String>();
					sensor.add(Integer.toString(smlDesc.getSID()));
					sensor.add(smlDesc.getProtocolType());
					sensor.add(smlDesc.getSensorAddress());
					sensor.add(smlDesc.getProtocolName());
					sensorList.add(sensor);
				}
			}
		} catch (Exception ex) {
			ArrayList<String> sensor = new ArrayList<String>();
			sensor.add("0");
			sensor.add(null);
			sensor.add(null);
			sensor.add(null);
			sensorList.add(sensor);
		}

		return sensorList;
	}

	/**
	 * @see edu.jcu.sali.index.client.sensor.SensorService#addSensor(String)
	 */
	public void addSensor(String newSensor) throws Exception {
		try {
			client.addSensor(newSensor);
		} catch (Exception ex) {
			System.out.println("Error: no sensor added");
		}
	}

}
