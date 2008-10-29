/**
 * 
 */
package edu.jcu.sali.index.client.sensor.commandlist;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Service to return the possible commands for a sensor
 * @author Marc
 *
 */
public interface CommandListServiceAsync {

	public void getCommandList(int sid, AsyncCallback<ArrayList<ArrayList<String>>> callback);
	public void sendCommand(String sid, int cid, AsyncCallback<String> callback);

}
