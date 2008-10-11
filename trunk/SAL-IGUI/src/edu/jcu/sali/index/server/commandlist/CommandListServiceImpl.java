package edu.jcu.sali.index.server.commandlist;

//import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.Set;

import jcu.sal.common.cml.CMLDescription;
import edu.jcu.sali.index.client.commandlist.CommandListService;
import edu.jcu.sali.test.TestClient;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CommandListServiceImpl extends RemoteServiceServlet implements CommandListService {


//	@EJB
//	ClientLocal client;
	private TestClient client;
	
	public CommandListServiceImpl() {
		client = new TestClient();
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @ToDo implement reference to SalClient 
	 * 
	 * @return list of commands
	 */	
	public ArrayList<ArrayList<String>> getCommandList(int sid) throws Exception {
		Set<CMLDescription> cmlList = client.getCommands(sid);
		
		ArrayList<ArrayList<String>> commands = new ArrayList<ArrayList<String>>();
		for (CMLDescription cml : cmlList) {
			ArrayList<String> command = new ArrayList<String>();
			command.add(cml.getName());
			command.add(cml.getDesc());	
			command.add(cml.getArgNames().toString());
			command.add(cml.getArgTypes().toString());
			commands.add(command);
		}
		
	
		return commands;
	}
}
