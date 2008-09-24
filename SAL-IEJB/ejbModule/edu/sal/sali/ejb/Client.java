package edu.sal.sali.ejb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.AccessException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.ConfigurationException;

import jcu.sal.common.Constants;
import jcu.sal.common.agents.RMISALAgent;

/**
 * Session Bean implementation class Client
 */
@Stateless
public class Client implements ClientRemote, ClientLocal, Remote {

	private static final String RMI_NAME = "EJB_SAL-I_Client";
	private static final String AGENT_RMI_REG_IP = "169.254.31.20";
	private static final String OUR_IP = "10.12.170.225";
		
//	private Map<String, String> viewers;
	private RMISALAgent agent;
	private Registry agentRegistry, ourRegistry;
	private String RMIname;
	private BufferedReader b;
	
    /**
     * Default constructor. 
     */
    public Client() {
    	
    	String initTxt = "Client --> Initialisation...";
    	System.out.println(initTxt);        
    }
    
    public String test(){
    	
    	String dummyTxt = "Client --> test call successful!!";
    	System.out.println(dummyTxt);
    	return dummyTxt;
    }
    
    @Override
	public void connectToAgent() {

    	try {
			init(RMI_NAME, AGENT_RMI_REG_IP, OUR_IP);
			start(OUR_IP);
			stop();
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
		b = new BufferedReader(new InputStreamReader(System.in));
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
		}
	}
	
	public void stop() throws RemoteException{
		try {
			agent.unregisterEventHandler(RMIname, RMIname, Constants.SENSOR_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(RMIname, RMIname, Constants.PROTOCOL_MANAGER_PRODUCER_ID);
			agent.unregisterEventHandler(RMIname, RMIname, Constants.SENSOR_STATE_PRODUCER_ID);
		} catch (ConfigurationException e) {
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
		ourRegistry.rebind(name, UnicastRemoteObject.exportObject(r, 0));
	}


}
