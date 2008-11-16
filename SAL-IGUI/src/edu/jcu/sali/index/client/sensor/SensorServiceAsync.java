package edu.jcu.sali.index.client.sensor;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This interface determines the functionality of the services for the
 * sensor-package.
 * 
 * @author Marc Hammerton
 * 
 */
public interface SensorServiceAsync {

	/**
	 * Returns the sensor-list.
	 * 
	 * @return List of sensors.
	 * @throws Exception
	 */
	public void getSensorList(AsyncCallback<ArrayList<ArrayList<String>>> callback);

	/**
	 * Add a new sensor.
	 * 
	 * @param newSensor
	 *            XML-structure for the new sensor.
	 * @throws Exception
	 */
	public void addSensor(String newSensor, AsyncCallback<?> callback);

}
