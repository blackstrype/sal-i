package edu.jcu.sali.index.server;

import edu.jcu.sali.index.client.CommandListService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CommandListServiceImpl extends RemoteServiceServlet implements CommandListService {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCommandList() throws Exception {
		return "some commands";
	}
}
