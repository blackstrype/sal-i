package edu.sal.sali.ejb;

import java.io.NotActiveException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.xml.parsers.ParserConfigurationException;

import jcu.sal.common.Response;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.events.Event;
import jcu.sal.common.exceptions.ConfigurationException;
import jcu.sal.common.exceptions.NotFoundException;
import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.protocol.SensorCommand;
import edu.sal.sali.rmi.SalConnector;

/**
 * Session Bean implementation class Client
 */
@Stateless
public class Client implements ClientRemote, ClientLocal, SALAgentEventHandler {
	private static final String RMI_NAME = "EJB_SAL-I_Client_";
	private static final String AGENT_RMI_REG_IP = "137.219.45.117";
	private static final String OUR_IP = "137.219.45.136";
		
	private static int clientCount = 0;
	
	private SalConnector salCon;
	
    /**
     * Default constructor. 
     */
    public Client() {
    }
    
    @PostConstruct
    public void init(){
    	String initTxt = "Client --> Initialisation... ID: " + clientCount;
    	System.out.println(initTxt);        
    	
//    	InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("ConfigSALI.xml"); 
//    	new File(input);
    	
    	
    	salCon = new SalConnector(RMI_NAME + clientCount++, AGENT_RMI_REG_IP, OUR_IP, this);
    	salCon.connectToAgent();  	
    }    
    
    //TODO does not work, figure something out!
    @PreDestroy
    public void disconnect(){
    	
    	String initTxt = "Client --> PreDestroy... ID: " + clientCount;
    	System.out.println(initTxt);  
    	
	    try {
	    	
			salCon.stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
    
    @Override
    public void stop(){
    	String initTxt = "Client --> STOP... ID: " + clientCount;
    	System.out.println(initTxt);  
    	
	    try {
	    	
			salCon.stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
    @Override
	public void handle(Event e) {
		System.out.println("Client --> Received: "+e.toString());
	}

	@Override
	public void collect(Response arg0) {
		System.out.println("Client --> Collect function has been called!!");
	}
	
	@Override
	public String test(){
	    	String dummyTxt = "Client --> test call successful!!";
	    	//System.out.println(dummyTxt);
	    	return dummyTxt;
	}
	
	@Override
	public ArrayList<String> getSensorList(){
		
		String tempSensor;
		ArrayList<String> sensorList = new ArrayList<String>();
		
		SMLDescriptions listDesc = salCon.getActiveSensors();
		for(SMLDescription singleDesc : listDesc.getDescriptions()){
			tempSensor = singleDesc.getSID() + "##" + singleDesc.getType();	
			sensorList.add(tempSensor);
		}
		
		return sensorList;
	}
	
	@Override
	public SMLDescriptions getSensorListActive(){
		return salCon.getActiveSensors();
	}
	
	@Override
	public void removeSensor(int sid){
		salCon.remSensor(Integer.toString(sid));
	}
	
	@Override
	public void addSensor(String xmlDoc){
		salCon.addSensor(xmlDoc);
	}
	
	@Override
	public String getProtocolList(){
		return salCon.getProtocolsList();
	}
	
	@Override
	public void addProtocol(String xmlDoc, boolean doAssociate){
		salCon.addProtocol(xmlDoc, doAssociate);
	}
	
	@Override
	public void removeProtocol(int pid, boolean remAssociate){
		salCon.removeProtocol(Integer.toString(pid), remAssociate);
	}
	
	@Override
	public Set<CMLDescription> getCommands(int sid){
		return salCon.getSensorCommands(sid);
		
	}
	
	@Override
	public Response sendCommand(SensorCommand scmd){
		
		Response resp = null;
		
		try {
			resp = salCon.sendCommand(scmd);
		} catch (NotActiveException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return resp;
	}	
	
}
