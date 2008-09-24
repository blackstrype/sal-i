package edu.jcu.sali.index.server.commandlist;

import edu.jcu.sali.index.client.commandlist.CommandListService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CommandListServiceImpl extends RemoteServiceServlet implements CommandListService {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @ToDo implement reference to SalClient 
	 * 
	 * @return list of commands
	 */	
	public String[] getCommandList() throws Exception {
		String[] commands = { "command 1", "command 2", "command 3" };
		return commands;
	}
}
