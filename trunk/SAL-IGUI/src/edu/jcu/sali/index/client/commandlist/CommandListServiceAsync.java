/**
 * 
 */
package edu.jcu.sali.index.client.commandlist;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Service to return the possible commands for a sensor
 * @author Marc
 *
 */
public interface CommandListServiceAsync {

	public void getCommandList(AsyncCallback<String[]> callback);

}
