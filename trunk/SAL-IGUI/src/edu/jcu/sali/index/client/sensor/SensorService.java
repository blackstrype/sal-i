/**
 * 
 */
package edu.jcu.sali.index.client.sensor;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Marc
 *
 */
@RemoteServiceRelativePath("sensorservice")
public interface SensorService extends RemoteService {

	public ArrayList<ArrayList<String>> getSensorList() throws Exception;	
	
	public void addSensor(String newSensor) throws Exception;
	
	public static class Util {
		
		public static SensorServiceAsync getInstance() {

			return GWT.create(SensorService.class);
		}
	}

}
