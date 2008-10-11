/**
 * 
 */
package edu.jcu.sali.index.client.commandlist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Service to return the possible commands for a sensor
 * @author Marc
 *
 */
@RemoteServiceRelativePath("commandlistservice")
public interface CommandListService extends RemoteService {

	public ArrayList<ArrayList<String>> getCommandList(int sid) throws Exception;
	
	public static class Util {

		public static CommandListServiceAsync getInstance() {

			return GWT.create(CommandListService.class);
		}
	}

}
