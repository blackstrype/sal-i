package edu.jcu.sali.index.client.sensor.commandlist;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This interface determines the functionality of the services for the
 * command-list-package.
 * 
 * @author Marc Hammerton
 * 
 */
public interface CommandListServiceAsync {

	/**
	 * Return the command list for a sensor.
	 * 
	 * @param sid
	 *            Sensor-id for which the commands should be returned.
	 * @return List of commands
	 * @throws Exception
	 */
	public void getCommandList(int sid, AsyncCallback<ArrayList<ArrayList<String>>> callback);

	/**
	 * Send a command for a certain sensor.
	 * 
	 * @param sid
	 *            Sensor-id for which the command should be send.
	 * @param args
	 *            Arguments for the command.
	 * @param cid
	 *            Command-id
	 * @return Output of the command.
	 * @throws Exception
	 */
	public void sendCommand(String sid, String args, int cid, AsyncCallback<String> callback);

	/**
	 * Removes a sensor.
	 * 
	 * @param sid
	 *            The sensor to be removed.
	 */
	public void removeSensor(int sid, AsyncCallback<?> callback);

}
