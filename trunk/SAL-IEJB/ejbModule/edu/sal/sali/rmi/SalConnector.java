package edu.sal.sali.rmi;

import java.io.NotActiveException;
import java.rmi.AccessException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import jcu.sal.common.Constants;
import jcu.sal.common.Response;
import jcu.sal.common.agents.RMISALAgent;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.cml.CMLDescriptions;
import jcu.sal.common.cml.RMIStreamCallback;
import jcu.sal.common.events.Event;
import jcu.sal.common.events.RMIEventHandler;
import jcu.sal.common.exceptions.ConfigurationException;
import jcu.sal.common.exceptions.NotFoundException;
import jcu.sal.common.exceptions.SALDocumentException;
import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.SALAgentEventHandler;

public class SalConnector implements RMIEventHandler, RMIStreamCallback {
	
	private String rmiName;
	private String agentRmiIP;
	private String ownIP;
	private SALAgentEventHandler eventHandler;
	
//	private Map<String, String> viewers;
	private RMISALAgent agent;
	private Registry agentRegistry, ourRegistry;
	private String RMIname;
//	private BufferedReader b;
	
	public SalConnector(){
		
	}
	
	public SalConnector(String rmiName, String agentRmiIP , String ownIP, SALAgentEventHandler eventHandler){
		this.rmiName = rmiName;
		this.agentRmiIP = agentRmiIP;
		this.ownIP = ownIP;
		this.eventHandler = eventHandler;
	}
	
	public void connectToAgent() {

    	try {
			init(rmiName, agentRmiIP, ownIP);
			start(ownIP);
			//stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(String rmiName, String agentRMIRegIP, String ourIP) throws RemoteException {
		agentRegistry = LocateRegistry.getRegistry(agentRMIRegIP);
		ourRegistry = LocateRegistry.getRegistry(ourIP);
//		viewers = new Hashtable<String, String>();
		RMIname = rmiName;
		try {
			agent = (RMISALAgent) agentRegistry.lookup(RMISALAgent.RMI_STUB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException();
		}
		//b = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	public void start(String ourRmiIP) throws ConfigurationException{
		
		try {
			agent.registerClient(RMIname, ourRmiIP);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConfigurationException();
		}
		try {
			//TODO resolve REMOTE interface problem
			export(RMIname, this);
		} catch (AccessException e) {
			e.printStackTrace();
			throw new ConfigurationException();
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConfigurationException();
		}
		
		try {
			agent.registerEventHandler(RMIname, RMIname, Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.registerEventHandler(RMIname, RMIname, Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.registerEventHandler(RMIname, RMIname, Constants.SENSOR_STATE_PRODUCER_ID);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConfigurationException();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() throws RemoteException{
		try {
			agent.unregisterEventHandler(RMIname, RMIname, Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(RMIname, RMIname, Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(RMIname, RMIname, Constants.SENSOR_STATE_PRODUCER_ID);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			agent.unregisterClient(RMIname);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agent = null;
		System.gc();
		
	}
	
	public void export(String name, Remote r) throws AccessException, RemoteException{
		//TODO Check why export doesent work
		ourRegistry.rebind(name, UnicastRemoteObject.exportObject(r, 0));
		//ourRegistry.rebind(name, r);
	}

	@Override
	public void handle(Event e) throws RemoteException {
		eventHandler.handle(e);
	}

	@Override
	public void collect(Response arg0) throws RemoteException {
		eventHandler.collect(arg0);
	
		
//		if(ts==0)
//			ts = System.currentTimeMillis();
//		else if((ts+10000)<System.currentTimeMillis()){
//			System.out.println("FPS: "+( (float) ((float) 1000*n/((float)(System.currentTimeMillis()-ts))) ) );
//			ts = System.currentTimeMillis();
//			n=0;
//		} else
//			n++;
//		
//		try {
//			viewers.get(r.getSID()).setImage(r.getBytes());
//		} catch (ConfigurationException e) {
//			System.out.println("Stream from sensor "+r.getSID()+" returned an error");
//			viewers.remove(r.getSID());
//		} catch (ClosedChannelException e) {
//			System.out.println("Stream from sensor "+r.getSID()+" completed");
//			viewers.remove(r.getSID());
//		}
	}
	
	
	
	public void printSensorList(SMLDescriptions smls){
		for(SMLDescription tmp: smls.getDescriptions()){
			System.out.print("SID: "+tmp.getSID());
			System.out.print(" - "+tmp.getSensorAddress());
			System.out.print(" - "+tmp.getProtocolType());
			System.out.println(" - "+tmp.getProtocolName());
		}
	}
	
	public String getActiveSensorsXML(){
		try {
			return agent.listActiveSensors();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO implement exception handling
		return "Exception occured";
	}
	
	public SMLDescriptions getActiveSensors(){
		try {
			return new SMLDescriptions(agent.listActiveSensors());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SALDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO exception handling
		return null;
	}
	
	public void addProtocol(String xmlDoc, boolean doAssociate){
		try {
			agent.addProtocol(xmlDoc, doAssociate);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SALDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeProtocol(String protocolID, boolean remAssociate){
		try {
			agent.removeProtocol(protocolID, remAssociate);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getProtocolsList(){
		String list = "";
		try {
			list = agent.listProtocols();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
			
	}
	
	public void addSensor(String xmlDoc){
		try {
			agent.addSensor(xmlDoc);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SALDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remSensor(String sensorID){
		try {
			agent.removeSensor(sensorID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String listAllSensorsXML(){
		try {
			return agent.listSensors();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO add exception handling
		return "Exception Occured";
	}
	
	public SMLDescriptions listAllSensors(){
		try {
			return new SMLDescriptions(agent.listSensors());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SALDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO exception....
		return null;
	}
//	System.out.println("Enter either :\n\ta sensor id to send a command\n\t-1 to quit\n\t-2 to see a list of active sensors (XML)");
//	System.out.println("\t-3 to see a list of active sensors (shorter, human readable listing)");
//	System.out.println("\t-4 to add a new protocol\n\t-5 to remove a protocol\n\t-6 to list all protocols");
//	System.out.println("\t-7 to add a new sensor\n\t-8 to remove a sensor");
//	System.out.println("\t-9 to list all sensors (XML)\n\t-10 to list all sensors(shorter, human readable listing)");
	
	
	public Set<CMLDescription> getSensorComamnds(int sid){
		
		CMLDescriptions cmldesc;
		try {
			cmldesc = new CMLDescriptions(agent.getCML(String.valueOf(sid)));
			return cmldesc.getDescriptions();
		} catch (SALDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public void getSensorCommands(int sid){
		
		
	}
	
	
	public String sendCommand(int sid, int cmdID) throws NotActiveException, ConfigurationException, RemoteException, ParserConfigurationException{
		//TODO resolve problems
//		RMICommandFactory cf;
//		RMICommand c = null;
//		Response res;
//		ArgumentType t;
//		CMLDescriptions cmls;
//		
//		try {
//			cmls = new CMLDescriptions(agent.getCML(String.valueOf(sid)));
//			cf = new RMICommandFactory(cmls.getDescription(cmdID));
//		} catch (SALDocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return "test";
		
//		for(String str: cf.listMissingArgNames()){
//			t = cf.getArgType(str);
//			if(!t.getArgType().equals(CMLConstants.ARG_TYPE_CALLBACK)) {
//				while(!argOK) {
//					System.out.println("Enter value of type '"+t.getArgType()+"' for argument '"+str+"'");
//					str2 = "argument"; //b.readLine();
//					try {cf.addArgumentValue(str, str2); argOK = true;}
//					catch (ConfigurationException e1) {System.out.println("Wrong value"); argOK=false;}
//				}
//			} else {
//				cf.addArgumentCallback(str,RMIname, RMIname);
////				viewers.put(String.valueOf(sid), new JpgMini(String.valueOf(sid)));	
//			}
//		}
//		try {c = cf.getCommand(); argsDone=true;}
//		catch (ConfigurationException e1) {System.out.println("Values missing"); throw e1; }//argsDone=false;}
//	}
//	
//	res = agent.execute(c, String.valueOf(sid));
//	
//	if(cmls.getDescription(j).getReturnType().equals(CMLConstants.RET_TYPE_BYTE_ARRAY))
//	System.out.println("jpeg!");	//new JpgMini(String.valueOf(sid)).setImage(res.getBytes());
//	else
//		System.out.println("Command returned: " + res.getString());	
//		
//		
	}
	

	


}
