/**
 * 
 */
package edu.jcu.sali.index.client.sensorlist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Marc
 *
 */
@RemoteServiceRelativePath("sensorlistservice")
public interface SensorListService extends RemoteService {

	public ArrayList<ArrayList<String>> getSensorList() throws Exception;	
	
	public static class Util {
		
		public static SensorListServiceAsync getInstance() {

			return GWT.create(SensorListService.class);
		}
	}

}
