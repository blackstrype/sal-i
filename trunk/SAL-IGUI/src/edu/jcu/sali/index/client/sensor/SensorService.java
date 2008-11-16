package edu.jcu.sali.index.client.sensor;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * This interface determines the functionality of the services for the
 * sensor-package.
 * 
 * @author Marc Hammerton
 * 
 */
@RemoteServiceRelativePath("sensorservice")
public interface SensorService extends RemoteService {

	/**
	 * Returns the sensor-list.
	 * 
	 * @return List of sensors.
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> getSensorList() throws Exception;

	/**
	 * Add a new sensor.
	 * 
	 * @param newSensor
	 *            XML-structure for the new sensor.
	 * @throws Exception
	 */
	public void addSensor(String newSensor) throws Exception;

	/**
	 * Inner-class to return an instance of the service.
	 * 
	 * @author Marc Hammerton
	 * 
	 */
	public static class Util {

		/**
		 * Returns an instance of the service.
		 * 
		 * @return SensorServiceAsync
		 */
		public static SensorServiceAsync getInstance() {

			return GWT.create(SensorService.class);
		}
	}

}
