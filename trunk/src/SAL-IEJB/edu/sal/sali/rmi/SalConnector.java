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
import edu.sal.sali.ejb.ClientMode;
import edu.sal.sali.ejb.SALAgentEventHandler;
import edu.sal.sali.ejb.cache.SALI_Cache;
import edu.sal.sali.ejb.exeption.SALException;
import edu.sal.sali.ejb.exeption.TechnicalException;
import edu.sal.sali.ejb.protocol.SensorCommand;

public class SalConnector implements RMIEventHandler, RMIStreamCallback {

	private String rmiName;
	private String agentRmiIP;
	private String ownIP;
	private SALAgentEventHandler eventHandler = null;
	private RMISALAgent agent = null;
	private Registry agentRegistry = null;
	private Registry ourRegistry = null;
	private int connectionCount = 0;
	private SALI_Cache salicache;
	private ClientMode mode;
	private boolean isConnected;
	protected boolean isCacheBlock;

	
	public SalConnector(String rmiName, String agentRmiIP , String ownIP, SALAgentEventHandler eventHandler){
		this.rmiName = rmiName;
		this.agentRmiIP = agentRmiIP;
		this.ownIP = ownIP;
		this.eventHandler = eventHandler;	
		this.isConnected = false;
	}
	
	synchronized public void connectToAgent() {
		
		if (!isConnected){
			
			printConMsg("connect to agent, ID: " + getRmiName());
			
			getRegistries();
			lookupAgent();
			registerClient();
			bindRmi();
			registerEventHandlers();
			
			isConnected = true;
		} else {
			printConMsg(getRmiName() + " already connected");
		}
	}
	

	synchronized public void disconnectFromAgent() {
		
		if (isConnected){
			
			printConMsg("disconnect from agent, ID: " + getRmiName());
			
			unregisterEventHandlers();
			unregisterClient();
			unbindRmi();
			
			isConnected = false;
			
			eventHandler.renewConnection();
		} else {
			printConMsg(getRmiName() + " already disconnected");
		}
	}
	
	
	private void getRegistries(){
		
		try {
			agentRegistry = LocateRegistry.getRegistry(agentRmiIP);
			ourRegistry = LocateRegistry.getRegistry(ownIP);
	
		} catch (RemoteException e) {
			formatException(e, "get rmi registries: " + getRmiName());
			//handleRemoteException(e, "get rmi registries");
		}
	}
	

	private void lookupAgent(){
		
		String exName = "lookup SAL agent: " + getRmiName();
		
		try {
			agent = (RMISALAgent) agentRegistry.lookup(RMISALAgent.RMI_STUB_NAME);

		} 
		//Technical Exception
		catch (AccessException e) {
			formatException(e, exName);
		} catch (RemoteException e) {
			formatException(e, exName);
		} catch (NotBoundException e) {
			formatException(e, exName);
		}
	}
	

	//TODO check function
	private void registerClient(){		
		
		String exName = "registerClient: " + getRmiName();
		
		try {
			agent.registerClient(getRmiName(), ownIP);
	
		}
		//Technical Exception
		catch (RemoteException e) {
			formatException(e, exName);
		} 
		//SAL Exception
		catch (ConfigurationException e) {
			//TODO Handle
			formatException(e, exName);
		} 
	}
			
	
	private String getRmiName() {
		return rmiName + "_c" + connectionCount;
	}

	private void bindRmi(){

		String exName = "bind RMI: " + getRmiName();
		
		try {
			ourRegistry.rebind(getRmiName(), UnicastRemoteObject.exportObject(this, 0));
			
		} 
		//Technical Exception
		catch (AccessException e) {
			formatException(e, exName);
		} catch (RemoteException e) {
			formatException(e, exName);
		}
	}
	
	private void unbindRmi(){
		
		String exName = "unbind RMI: " + getRmiName();
		
		try {
			ourRegistry.unbind(getRmiName());
		} 
		//Technical Exceptions
		catch (AccessException e) {
			formatException(e, exName);
		} catch (RemoteException e) {
			formatException(e, exName);
		} catch (NotBoundException e) {
			formatException(e, exName);
		}
	}
		
		
	private void registerEventHandlers(){
		
		String exName = "register event handlers";
		
		try {
			agent.registerEventHandler(getRmiName(), getRmiName(), Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.registerEventHandler(getRmiName(), getRmiName(), Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.registerEventHandler(getRmiName(), getRmiName(), Constants.SENSOR_STATE_PRODUCER_ID);
		
		} 
		//Technical Exception
		catch (RemoteException e) {
			formatException(e, exName);
		} 
		//SAL Exception
		catch (NotFoundException e) {
			//TODO Handle
			formatException(e, exName);
		} 
	}
	
	private void unregisterEventHandlers(){
		
		String exName = "unregister event handler";
		
		try {
			agent.unregisterEventHandler(getRmiName(), getRmiName(), Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(getRmiName(), getRmiName(), Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(getRmiName(), getRmiName(), Constants.SENSOR_STATE_PRODUCER_ID);
			
		} 
		//Technical Exception
		catch (RemoteException e) {
			formatException(e, exName);
		} 
		//SAL Exception
		catch (NotFoundException e) {
			//TODO Handle
			formatException(e, exName);
		} 
	}
	
	private void unregisterClient(){
		
		String exName = "unregister client";
		
		try {
			agent.unregisterClient(getRmiName());
			
		} catch (RemoteException e) {
			formatException(e, exName);
		} 
		//SAL 
		catch (ConfigurationException e) {
			//TODO Handle
			formatException(e, exName);
		}

		agent = null;		
	}
	
	

	@Override
	public void handle(final Event e) {
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				eventHandler.handle(e);
				
				if(mode == ClientMode.CACHE){
					
					if(!isCacheBlock){

						//--------------------
						//this block (hopefully) prevents multiple cache updates within 3 seconds 
						
						isCacheBlock = true;
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e1) {
							formatException(e1, "sleep - Interrupt exception");
						}
						
						isCacheBlock = false;
						//---------------------
						
						try {
							salicache.updateAll();
						} catch (SALException e) {
							// TODO Handle
							formatException(e, "handle event");
						} catch (TechnicalException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}
			}		
		}).start();
	}

	@Override
	public void collect(final Response arg0) {
		new Thread(new Runnable(){

			@Override
			public void run() {

				eventHandler.collect(arg0);
				
				if(mode == ClientMode.CACHE){
					try {
						salicache.updateAll();
					} catch (SALException e) {
						// TODO Handle
						formatException(e, "collect response");
					} catch (TechnicalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}		
		}).start();
	}
	
	
	public String getActiveSensorsXML() throws TechnicalException{
		
		String result = "";
		
		try {
			result =  agent.listActiveSensors();
			
		} catch (RemoteException e) {
			handleRemoteException(e, "get active sensors XML");
		}
		
		return result;
	}
	

	public SMLDescriptions getActiveSensors() throws SALException, TechnicalException {
		
		SMLDescriptions sml = null;
		String exName = "get active sensors";
		
		try {
			sml = new SMLDescriptions(agent.listActiveSensors());
	
		} 
		//Technical Exception
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exception
		catch (SALDocumentException e) {
			handleSALException(e, exName);
		}
		
		return sml;
	}
	

	public void addProtocol(String xmlDoc, boolean doAssociate) throws SALException, TechnicalException{
		
		String exName = "add protocol";
		
		try {
			agent.addProtocol(xmlDoc, doAssociate);
		} 
		//Technical Exceptions
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exceptions
		catch (ConfigurationException e) {
			handleSALException(e, exName);
		} catch (SALDocumentException e) {
			handleSALException(e, exName);
		}
	}
	
	public void removeProtocol(String protocolID, boolean remAssociate) throws SALException, TechnicalException{
		
		String exName = "removeProtocol";
		
		try {
			agent.removeProtocol(protocolID, remAssociate);
			
		} 
		//Technical Exceptions
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exceptions
		catch (NotFoundException e) {
			handleSALException(e, exName);
		} 
	}
	
	public String getProtocolsList() throws TechnicalException{
		
		String list = "";
		
		try {
			list = agent.listProtocols();
			
		} catch (RemoteException e) {
			handleRemoteException(e, "getProtocolsList");
		}
		
		return list;		
	}
	
	public void addSensor(String xmlDoc) throws SALException, TechnicalException {
		
		String exName = "addSensor";
		try {
			agent.addSensor(xmlDoc);
			
		} 
		//Technical Exceptions
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exceptions
		catch (SALDocumentException e) {
			handleSALException(e, exName);
		} catch (ConfigurationException e) {
			handleSALException(e, exName);
		}
	}
	
	public void remSensor(String sensorID) throws SALException, TechnicalException {
		
		String exName = "remSensor";
		try {
			agent.removeSensor(sensorID);
			
		} 
		//Technical Exceptions
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exceptions
		catch (NotFoundException e) {
			handleSALException(e, exName);
		}
	}
	
	public String listAllSensorsXML() throws TechnicalException{
		
		String result = "";
		try {
			result = agent.listSensors();
			
		} catch (RemoteException e) {
			handleRemoteException(e, "listAllSensorsXML");
		}
		
		return result;
	}
	
	public SMLDescriptions listAllSensors() throws SALException, TechnicalException {
		
		SMLDescriptions sml = null;
		String exName = "list all sensors";
		
		try {
			sml = new SMLDescriptions(agent.listSensors());	
			
		} 
		//Technical Exceptions
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exceptions
		catch (SALDocumentException e) {
			handleSALException(e, exName);
		}
		
		return sml;
	}

	public Set<CMLDescription> getSensorCommands(int sid) throws SALException, TechnicalException {
		
		CMLDescriptions cmldesc = null;
		String exName = "getSensorCommands";
		
		try {
			cmldesc = new CMLDescriptions(agent.getCML(String.valueOf(sid)));
			
		} 
		//Technical Exceptions
		catch (RemoteException e) {
			handleRemoteException(e, exName);
		} 
		//SAL Exceptions
		catch (SALDocumentException e) {
			handleSALException(e, exName);
		} catch (NotFoundException e) {
			handleSALException(e, exName);
		}
		
		return cmldesc.getDescriptions();		
	}
	
	public Response sendCommand(SensorCommand scmd) throws SALException, TechnicalException {
		
		Response resp = null;
		String exName = "sendCommand";
		
		try {
			resp = sendCommandInner(scmd);
		
		//Internal Exceptions
		} catch (NotActiveException e) {
			formatException(e, exName);
			throw new TechnicalException(exName, e);
		} catch (RemoteException e) {
			handleRemoteException(e, exName);
		} catch (ParserConfigurationException e) {
			formatException(e, exName);
			throw new TechnicalException(exName, e);
		} 
		//SAL Exceptions
		catch (ConfigurationException e) {
			handleSALException(e, exName);
		} catch (NotFoundException e) {
			handleSALException(e, exName);
		} catch (SALDocumentException e) {
			handleSALException(e, exName);
		} catch (SensorControlException e) {
			handleSALException(e, exName);
		}
		
		return resp;
	}
	
	private Response sendCommandInner(SensorCommand scmd) throws NotActiveException, ConfigurationException, RemoteException, ParserConfigurationException, NotFoundException, SALDocumentException, SensorControlException{
		RMICommandFactory cf = null;
		RMICommand c = null;
		Response res = null;
		ArgumentType t;
		CMLDescriptions cmls;
		boolean argOK = false;
		String argValue;
		
		cmls = new CMLDescriptions(agent.getCML(String.valueOf(scmd.getSid())));
		cf = new RMICommandFactory(cmls.getDescription(scmd.getCid()));
		
		
		for(String argName: cf.listMissingArgNames()){
			t = cf.getArgType(argName);
			if(!t.getArgType().equals(CMLConstants.ARG_TYPE_CALLBACK)) {
				while(!argOK) {
					argValue = scmd.getArg(argName);
					try {cf.addArgumentValue(argName, argValue); argOK = true;}
					catch (ConfigurationException e1) {System.out.println("Wrong value"); argOK=false;}
				}
			} else {
				cf.addArgumentCallback(argName, getRmiName(), getRmiName());
				//TODO handle images
//				viewers.put(String.valueOf(sid), new JpgMini(String.valueOf(sid)));	
			}
		}
		
		try {c = cf.getCommand();}
		catch (ConfigurationException e1) {printConMsg("Send command (inner): Values missing (org. SAL client msg.)"); throw e1; }//argsDone=false;}		
		
		res = agent.execute(c, String.valueOf(scmd.getSid()));
		
		return res;
	}


	private void handleRemoteException(RemoteException e, String string) throws TechnicalException {
		formatException(e, string);
		renewConnection();	
		throw new TechnicalException(string, e);
	}
	
	private void handleSALException(Exception e, String exName) throws SALException {
		formatException(e, exName);
		throw new SALException(exName, e);
	}
	
	private void renewConnection() {
		
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
				disconnectFromAgent();
				connectionCount++;
				connectToAgent();
//			}
//			
//		}).start();
	}

	
	private void formatException(Exception e, String string) {
		printConMsg("EX -> " + string);
		e.printStackTrace();
	}

	private void printConMsg(String text) {
		System.out.println("SALCon --> ParentBean: "  + eventHandler.getID() + "; " + text);
	}

	public SalConnector isExisting() {
		return this;
	}

	public void setCache(SALI_Cache salicache) {
		this.salicache = salicache;	
		mode = ClientMode.CACHE;
	}
}
