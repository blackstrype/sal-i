package edu.jcu.sali.index.client.sensor.commandlist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * This interface determines the functionality of the services for the
 * command-list-package.
 * 
 * @author Marc Hammerton
 * 
 */
@RemoteServiceRelativePath("commandlistservice")
public interface CommandListService extends RemoteService {

	/**
	 * Return the command list for a sensor.
	 * 
	 * @param sid
	 *            Sensor-id for which the commands should be returned.
	 * @return List of commands
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> getCommandList(int sid)
			throws Exception;

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
	public String sendCommand(String sid, String args, int cid)
			throws Exception;

	/**
	 * Removes a sensor.
	 * 
	 * @param sid
	 *            The sensor to be removed.
	 */
	public void removeSensor(int sid);

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
		 * @return CommandListServiceAsync
		 */

		public static CommandListServiceAsync getInstance() {

			return GWT.create(CommandListService.class);
		}
	}

}
