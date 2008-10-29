/**
 * 
 */
package edu.jcu.sali.index.client.sensor;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Marc
 *
 */
public interface SensorServiceAsync {

	public void getSensorList(AsyncCallback<ArrayList<ArrayList<String>>> callback);	
	
	public void addSensor(String newSensor, AsyncCallback<?> callback);

}
