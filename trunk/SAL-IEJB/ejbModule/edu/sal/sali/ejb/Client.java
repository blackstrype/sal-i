package edu.sal.sali.ejb;

import java.io.File;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import jcu.sal.common.Response;
import jcu.sal.common.events.Event;
import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;

import edu.sal.sali.rmi.SalConnector;

/**
 * Session Bean implementation class Client
 */
@Stateless
public class Client implements ClientRemote, ClientLocal, SALAgentEventHandler {
	private static final String RMI_NAME = "EJB_SAL-I_Client_";
//	private static final String AGENT_RMI_REG_IP = "137.219.45.117";
//	private static final String OUR_IP = "137.219.45.92";

	private static final String AGENT_RMI_REG_IP = "10.1.1.4";
	private static final String OUR_IP = "10.1.1.3";

	
	private static int clientCount = 0;
	
	private SalConnector salCon;
	
    /**
     * Default constructor. 
     */
    public Client() {
    }
    
    @PostConstruct
    public void init(){
    	String initTxt = "Client --> Initialisation...";
    	System.out.println(initTxt);        
    	
//    	InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("ConfigSALI.xml"); 
//    	new File(input);
    	
    	
    	salCon = new SalConnector(RMI_NAME + clientCount++, AGENT_RMI_REG_IP, OUR_IP, this);
    	salCon.connectToAgent();  	
    }    
    
    @PreDestroy
    public void disconnect(){
    	
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
		SMLDescriptions listDesc = salCon.getActiveSensors();
		ArrayList<String> sensorList = new ArrayList<String>();
		
		for(SMLDescription singleDesc : listDesc.getDescriptions()){
			sensorList.add(singleDesc.getID() + "##" + singleDesc.getProtocolType());
		}
		return sensorList;
	}
	
	public void getCommands(int sid){
		
		
	}
	
}
