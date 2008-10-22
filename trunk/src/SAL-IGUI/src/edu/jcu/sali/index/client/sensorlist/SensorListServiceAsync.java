/**
 * 
 */
package edu.jcu.sali.index.client.sensorlist;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Marc
 *
 */
public interface SensorListServiceAsync {

	public void getSensorList(AsyncCallback<ArrayList<ArrayList<String>>> callback);

}
