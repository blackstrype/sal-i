package edu.sal.sali.ejb;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import jcu.sal.common.Response;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.events.Event;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.cache.SALI_Cache;
import edu.sal.sali.ejb.cache.SALI_CacheMode;
import edu.sal.sali.ejb.protocol.SensorCommand;
import edu.sal.sali.rmi.SalConnector;

/**
 * Session Bean implementation class Client
 */
@Stateless
public class Client implements ClientRemote, ClientLocal, SALAgentEventHandler {
	private static final String RMI_NAME = "EJB_SAL-I_Client";
	private static final String AGENT_RMI_REG_IP = "137.219.45.191";
	private static final String OUR_IP = "137.219.45.170";
	private static final ClientMode mode = ClientMode.CACHE;
	private static final SALI_CacheMode MODE = SALI_CacheMode.EVENT;
		
	private static int clientCount = 0;
	private SalConnector salCon = null;
	
	private static SALI_Cache salicache = null;
	
    /**
     * Default constructor. 
     */
    public Client() {
    	
    }
    
    @PostConstruct
    public void init(){
    	
    	String initTxt = "Client --> Initialisation... ID: " + clientCount;
    	Logger.getRootLogger().debug(initTxt);        
    	
    	//TODO Read client and server parameter from external configFile
//    	InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("ConfigSALI.xml"); 
//    	new File(input);   	
    	
    	salCon = new SalConnector(RMI_NAME + "_" + System.currentTimeMillis() + "_b" + clientCount++, AGENT_RMI_REG_IP, OUR_IP, this);    	
    	salCon.connectToAgent();  	
    	
    	if(salicache == null){
    		Logger.getRootLogger().debug("Cach not existing. Create cache and fill with data.");
    		salicache = new SALI_Cache(MODE, salCon);
    	} else {
    		Logger.getRootLogger().debug("Cach already existing.");
    	}
    }    
    
    //TODO does not work, figure something out!
    @PreDestroy
    public void disconnect(){   	
    	salCon.disconnectFromAgent();
    }
    
    @Override
    public void stop(){
    	this.disconnect();
    }
    
    @Override
	public void handle(Event e) {
		Logger.getRootLogger().debug("Client --> Received: "+e.toString());
		updateCache();
	}

	private void updateCache() {
		
		if(mode == ClientMode.CACHE){
			salicache.updateSensorData();
		}
	}

	@Override
	public void collect(Response arg0) {
		Logger.getRootLogger().debug("Client --> Collect function has been called!!");
	}
	
	@Override
	public String test(){
	    	String dummyTxt = "Client --> test call successful!!";
	    	//Logger.getRootLogger().debug(dummyTxt);
	    	return dummyTxt;
	}
	
	@Override
	public SMLDescriptions getSensorList(){
		
		SMLDescriptions sensorList = null;

		if (mode == ClientMode.CACHE) {
			sensorList = salicache.getSensorList();
		} else {
			sensorList = salCon.listAllSensors();
		}

		return sensorList;
	}
	
	@Override
	public SMLDescriptions getSensorListActive(){

		SMLDescriptions sensorList = null;

		if (mode == ClientMode.CACHE) {
			sensorList = salicache.getSensorListActive();
		} else {
			sensorList = salCon.getActiveSensors();
		}

		return sensorList;
	}
	
	@Override
	public void removeSensor(int sid){
		salCon.remSensor(Integer.toString(sid));
		updateCache();
	}
	
	@Override
	public void addSensor(String xmlDoc){
		salCon.addSensor(xmlDoc);
		updateCache();
	}
	
	@Override
	public String getProtocolList(){
		
		String protocolList = null;

		if (mode == ClientMode.CACHE) {
			protocolList = salicache.getProtocolList();
		} else {
			protocolList = salCon.getProtocolsList();
		}

		return protocolList;
	}
	
	@Override
	public void addProtocol(String xmlDoc, boolean doAssociate){
		salCon.addProtocol(xmlDoc, doAssociate);
		updateCache();
	}
	
	@Override
	public void removeProtocol(int pid, boolean remAssociate){
		salCon.removeProtocol(Integer.toString(pid), remAssociate);
		updateCache();
	}
	
	@Override
	public Set<CMLDescription> getCommands(int sid){
		
		Set<CMLDescription> cmdList = null;

		if (mode == ClientMode.CACHE) {
			cmdList = salicache.getSensorCommands(Integer.toString(sid));
		} else {
			cmdList = salCon.getSensorCommands(sid);
		}

		return cmdList;
		
	}
	
	@Override
	public Response sendCommand(SensorCommand scmd){	
		return salCon.sendCommand(scmd);
		//TODO check if cache update is necessary

	}	
	
}
