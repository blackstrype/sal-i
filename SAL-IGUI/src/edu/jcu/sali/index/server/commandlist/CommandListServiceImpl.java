package edu.jcu.sali.index.server.commandlist;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;

import jcu.sal.common.Response;
import jcu.sal.common.cml.ArgumentType;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.exceptions.SensorControlException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.jcu.sali.index.client.sensor.commandlist.CommandListService;
import edu.sal.sali.ejb.ClientLocal;
import edu.sal.sali.ejb.protocol.SensorCommand;

public class CommandListServiceImpl extends RemoteServiceServlet implements
		CommandListService {

	@EJB
	ClientLocal client;

//	 private TestClient client;

	public CommandListServiceImpl() {
//		 client = new TestClient();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return list of commands
	 */
	public ArrayList<ArrayList<String>> getCommandList(int sid)
			throws Exception {
		Set<CMLDescription> cmlList = client.getCommands(sid);

		ArrayList<ArrayList<String>> commands = new ArrayList<ArrayList<String>>();
		for (CMLDescription cml : cmlList) {
			ArrayList<String> command = new ArrayList<String>();
			command.add(cml.getCID().toString());
			command.add(cml.getName());
			command.add(cml.getDesc());
			
			String argTypes = "";
			for(ArgumentType argType : cml.getArgTypes()) {
				argTypes += argType.getArgType() + "##";
			}	
			command.add(argTypes);

			String argNames = "";
			for(String argName : cml.getArgNames()) {
				argNames += argName + "##";
			}	
			command.add(argNames);
			
			commands.add(command);
		}

		return commands;
	}

	public String sendCommand(String sid, String args, int cid) {

		String responseString = "";

		SensorCommand scmd = new SensorCommand(sid, cid);
		for(String argString : args.split("####")) {
			String[] arg = argString.split("##");
			scmd.addArg(arg[0], arg[1]);
			
		}
		
		
		Response resp = client.sendCommand(scmd);
		try {
			System.out.println(resp.getString());
			responseString = resp.getString();
		} catch (SensorControlException e) {
			System.out.println(e.getMessage());
			responseString = "No data available, the sensor is currently disabled.\n" +
					"Please enable the sensor before sending other commands.";
		} catch (NullPointerException e) {
			responseString = "No data available, the sensor is currently disabled.\n" +
			"Please enable the sensor before sending other commands.";
		}

		return responseString;
	}

	public void removeSensor(int sid) {
		try {
			client.removeSensor(sid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
