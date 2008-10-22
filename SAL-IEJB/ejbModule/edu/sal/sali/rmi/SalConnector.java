package edu.sal.sali.rmi;

import java.io.NotActiveException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import jcu.sal.common.Constants;
import jcu.sal.common.RMICommandFactory;
import jcu.sal.common.Response;
import jcu.sal.common.RMICommandFactory.RMICommand;
import jcu.sal.common.agents.RMISALAgent;
import jcu.sal.common.cml.ArgumentType;
import jcu.sal.common.cml.CMLConstants;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.cml.CMLDescriptions;
import jcu.sal.common.cml.RMIStreamCallback;
import jcu.sal.common.events.Event;
import jcu.sal.common.events.RMIEventHandler;
import jcu.sal.common.exceptions.ConfigurationException;
import jcu.sal.common.exceptions.NotFoundException;
import jcu.sal.common.exceptions.SALDocumentException;
import jcu.sal.common.exceptions.SensorControlException;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.SALAgentEventHandler;
import edu.sal.sali.ejb.protocol.SensorCommand;

public class SalConnector implements RMIEventHandler, RMIStreamCallback {
	
	private String rmiName;
	private String agentRmiIP;
	private String ownIP;
	private SALAgentEventHandler eventHandler = null;
	private RMISALAgent agent = null;
	private Registry agentRegistry = null;
	private Registry ourRegistry = null;

	
	public SalConnector(String rmiName, String agentRmiIP , String ownIP, SALAgentEventHandler eventHandler){
		this.rmiName = rmiName;
		this.agentRmiIP = agentRmiIP;
		this.ownIP = ownIP;
		this.eventHandler = eventHandler;
	}
	
	public void connectToAgent() {
		
		getRegistries();
		lookupAgent();
		registerClient();
		export();
		registerEventHandlers();
		
	}
	

	public void disconnectFromAgent() {
		
		unregisterEventHandlers();
		unregisterClient();
	}
	
	private void getRegistries(){
		
		try {
			agentRegistry = LocateRegistry.getRegistry(agentRmiIP);
			ourRegistry = LocateRegistry.getRegistry(ownIP);
		} catch (RemoteException e) {
			handleRemoteException(e, "getRegistries");
		}
	}
	

	private void lookupAgent(){
		String exName = "lookupAgent";
		
		try {
			agent = (RMISALAgent) agentRegistry.lookup(RMISALAgent.RMI_STUB_NAME);
		} catch (AccessException e) {
			handleAccessException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private void registerClient(){		
		String exName = "registerClient";
		try {
			agent.registerClient(rmiName, ownIP);
		} catch (ConfigurationException e) {
			handleConfigurationException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}
	}
			
	
	private void export(){

		String exName = "export";
		try {
			ourRegistry.rebind(rmiName, UnicastRemoteObject.exportObject(this, 0));
		} catch (AccessException e) {
			handleAccessException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}

	}
		
		
	private void registerEventHandlers(){
		String exName = "registerEventHandlers";
		try {
			agent.registerEventHandler(rmiName, rmiName, Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.registerEventHandler(rmiName, rmiName, Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.registerEventHandler(rmiName, rmiName, Constants.SENSOR_STATE_PRODUCER_ID);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}
	
	}
	
	private void unregisterEventHandlers(){
		
		String exName = "unregisterEventHandlers";
		try {
			agent.unregisterEventHandler(rmiName, rmiName, Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(rmiName, rmiName, Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(rmiName, rmiName, Constants.SENSOR_STATE_PRODUCER_ID);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}
	}
	
	private void unregisterClient(){
		
		String exName = "unregisterClient";
		try {
			agent.unregisterClient(rmiName);
		} catch (ConfigurationException e) {
			handleConfigurationException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}

		agent = null;		
	}
	
	

	@Override
	public void handle(Event e) throws RemoteException {
		eventHandler.handle(e);
	}

	@Override
	public void collect(Response arg0) throws RemoteException {
		eventHandler.collect(arg0);
	}
	
	
	public String getActiveSensorsXML(){
		
		String result = "";
		
		try {
			result =  agent.listActiveSensors();
		} catch (RemoteException e) {
			handleRemoteException(e, "getActiveSensorsXML");
		}
		
		return result;
	}
	
	public SMLDescriptions getActiveSensors(){
		
		SMLDescriptions sml = null;
		
		
		String exName = "getActiveSensors";
		try {
			sml = new SMLDescriptions(agent.listActiveSensors());
		} catch (SALDocumentException e) {
			handleSALDocumentException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}
		
		
		return sml;
	}
	
	
	public void addProtocol(String xmlDoc, boolean doAssociate){
		
		String exName = "addProtocol";
		try {
			agent.addProtocol(xmlDoc, doAssociate);
		} catch (ConfigurationException e) {
			handleConfigurationException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (SALDocumentException e) {
			handleSALDocumentException(e, exName);
		}
	}
	
	public void removeProtocol(String protocolID, boolean remAssociate){
		
		String exName = "removeProtocol";
		try {
			agent.removeProtocol(protocolID, remAssociate);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		}
	}
	
	public String getProtocolsList(){
		
		String list = "";
		try {
			list = agent.listProtocols();
		} catch (RemoteException e) {
			handleRemoteException(e, "getProtocolsList");
		}
		return list;
			
	}
	
	public void addSensor(String xmlDoc){
		
		String exName = "addSensor";
		try {
			agent.addSensor(xmlDoc);
		} catch (ConfigurationException e) {
			handleConfigurationException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (SALDocumentException e) {
			handleSALDocumentException(e, exName);
		}
	}
	
	public void remSensor(String sensorID){
		
		String exName = "remSensor";
		try {
			agent.removeSensor(sensorID);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		}
	}
	
	public String listAllSensorsXML(){
		
		String result = "";
		try {
			result = agent.listSensors();
		} catch (RemoteException e) {
			handleRemoteException(e, "listAllSensorsXML");
		}
		
		return result;
	}
	
	public SMLDescriptions listAllSensors(){
		
		SMLDescriptions sml = null;
		String exName = "listAllSensors";
		try {
			sml = new SMLDescriptions(agent.listSensors());
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (SALDocumentException e) {
			handleSALDocumentException(e, exName);
		}
		
		return sml;
	}

	public Set<CMLDescription> getSensorCommands(int sid){
		
		CMLDescriptions cmldesc = null;
		String exName = "getSensorCommands";
		try {
			cmldesc = new CMLDescriptions(agent.getCML(String.valueOf(sid)));
		} catch (SALDocumentException e) {
			handleSALDocumentException(e, exName);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		}
		
		return cmldesc.getDescriptions();		
	}
	
//	public RMICommandFactory prepareCommand
	
	public Response sendCommand(SensorCommand scmd){
		
		Response resp = null;
		
		String exName = "sendCommand";
		try {
			resp = sendCommandInner(scmd);
		} catch (NotActiveException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (ConfigurationException e) {
			handleConfigurationException(e, exName);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
		return resp;
		
	}
	
	private Response sendCommandInner(SensorCommand scmd) throws NotActiveException, ConfigurationException, RemoteException, ParserConfigurationException, NotFoundException{
		//TODO resolve problems
		RMICommandFactory cf = null;
		RMICommand c = null;
		Response res = null;
		ArgumentType t;
		CMLDescriptions cmls;
		boolean argOK = false;
		String argValue;
		
		String exName = "sendCommandInner";
		try {
			cmls = new CMLDescriptions(agent.getCML(String.valueOf(scmd.getSid())));
			cf = new RMICommandFactory(cmls.getDescription(scmd.getCid()));
		} catch (SALDocumentException e) {
			handleSALDocumentException(e, exName);
		} catch (NotFoundException e) {
			handleNotFoundException(e, exName);
		}
		
		
		for(String argName: cf.listMissingArgNames()){
			t = cf.getArgType(argName);
			if(!t.getArgType().equals(CMLConstants.ARG_TYPE_CALLBACK)) {
				while(!argOK) {
//					System.out.println("Enter value of type '"+t.getArgType()+"' for argument '"+str+"'");
//					str2 = b.readLine();
					argValue = scmd.getArg(argName);
					try {cf.addArgumentValue(argName, argValue); argOK = true;}
					catch (ConfigurationException e1) {System.out.println("Wrong value"); argOK=false;}
				}
			} else {
				cf.addArgumentCallback(argName,rmiName, rmiName);
				//TODO handle images
//				viewers.put(String.valueOf(sid), new JpgMini(String.valueOf(sid)));	
			}
		}
		try {c = cf.getCommand();}
		catch (ConfigurationException e1) {System.out.println("Values missing"); throw e1; }//argsDone=false;}
		
		
		try {
			res = agent.execute(c, String.valueOf(scmd.getSid()));
		} catch (SensorControlException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
		return res;
		
//		if(cmls.getDescription(j).getReturnType().equals(CMLConstants.RET_TYPE_BYTE_ARRAY))
//		System.out.println("jpeg!");	//new JpgMini(String.valueOf(sid)).setImage(res.getBytes());
//		else
//			System.out.println("Command returned: " + res.getString());	
//		
//		
//		}
	}
	
	private void handleNotFoundException(NotFoundException e, String string) {
		formatException(e, string);
		
	}

	private void handleRemoteException(RemoteException e, String string) {
		formatException(e, string);
	}
	
	private void handleAccessException(AccessException e, String string) {
		formatException(e, string);
		
	}
	
	private void handleConfigurationException(ConfigurationException e,
			String string) {
		formatException(e, string);
		
	}

	private void handleSALDocumentException(SALDocumentException e,
			String string) {
		formatException(e, string);
		
	}
	
	private void formatException(Exception e, String string) {
		System.out.println("EX -> " + string + ": " + e.toString());	
	}
	
	
////	private boolean handleConfigurationException(ConfigurationException e, String causedBy) {
////		System.out.println(causedBy + " --> " + e.toString());
////		//disconnectFromAgent();
////		
////		try {
////			agent.unregisterClient(rmiName);
////		} catch (ConfigurationException ex) {
////			// TODO Auto-generated catch block
////			System.out.println(causedBy + " --> " + e.toString());
////		} catch (RemoteException ex) {
////			// TODO Auto-generated catch block
////			System.out.println(causedBy + " --> " + e.toString());
////		}
//		
//		
//		return false;
//	}
	
//	private boolean handleRemoteException(RemoteException e, String causedBy) {
//		System.out.println(causedBy + " --> " + e.toString());
//		disconnectFromAgent();
//		
//		return false;
//	}


}
