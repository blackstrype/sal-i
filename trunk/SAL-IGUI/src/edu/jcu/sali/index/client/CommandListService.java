/**
 * 
 */
package edu.jcu.sali.index.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Service to return the possible commands for a sensor
 * @author Marc
 *
 */
@RemoteServiceRelativePath("commandList")
public interface CommandListService extends RemoteService {

	public String getCommandList() throws Exception;
	
	public static class Util {

		public static CommandListServiceAsync getInstance() {

			return GWT.create(CommandListService.class);
		}
	}

}
