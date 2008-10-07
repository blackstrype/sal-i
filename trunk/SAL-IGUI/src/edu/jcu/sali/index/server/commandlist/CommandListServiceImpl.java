package edu.jcu.sali.index.server.commandlist;

import javax.ejb.EJB;

import edu.jcu.sali.index.client.commandlist.CommandListService;
import edu.sal.sali.ejb.ClientLocal;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CommandListServiceImpl extends RemoteServiceServlet implements CommandListService {


	@EJB
	ClientLocal client;
	
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
		String[] commands = { "command 1", client.test(), "command 3" };
		return commands;
	}
}
