package edu.sal.sali.ejb;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import jcu.sal.common.Response;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.events.Event;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.cache.SALI_Cache;
import edu.sal.sali.ejb.cache.SALI_CacheMode;
import edu.sal.sali.ejb.exeption.SALException;
import edu.sal.sali.ejb.exeption.TechnicalException;
import edu.sal.sali.ejb.protocol.SensorCommand;
import edu.sal.sali.rmi.SalConnector;

/**
 * Session Bean implementation class Client
 */
@Stateless
public class Client implements ClientRemote, ClientLocal, SALAgentEventHandler {
	
	//TODO export connection settings
	private static final String RMI_NAME = "SAL-I";
//	private static final String AGENT_RMI_REG_IP = "137.219.45.117";
//	private static final String OUR_IP = "137.219.45.222";
	private static final String AGENT_RMI_REG_IP = "10.1.1.4";
	private static final String OUR_IP = "10.1.1.3";
	
	private static final ClientMode mode = ClientMode.CACHE;
	private static final SALI_CacheMode MODE = SALI_CacheMode.EVENT;
		
	private static int clientCount;
	private static SalConnector salCon;
	private static SALI_Cache salicache;
	private String fullClientName;
	private int beanID;
	
    /**
     * Default constructor. 
     */
    public Client() {}
    
    
    private void printCltMsg(String text){
    	System.out.println("Client --> Bean, ID: "  + beanID + "; " + text);
    }
    
    
    @PostConstruct
    public void init(){
    	
    	beanID = clientCount++;
    	
    	System.out.println("Client --> NEW Bean, ID: " + beanID);  
    
    	//first time initialization
    	createSalConnector();
    	createSalCache();
    }


	public void createSalCache() {
		
		if(salCon == null){
			createSalConnector();
		}
		
		if(salicache == null && mode == ClientMode.CACHE){
    		
    		printCltMsg("Create SALCache");	
    		salicache = new SALI_Cache(MODE, salCon);
    		salCon.setCache(salicache);   		
    	} else {   		
    		printCltMsg("SALCache already existing");
    	}
	}


	public void createSalConnector() {
		if(salCon == null){	    	
    		
			fullClientName = RMI_NAME + "_" + OUR_IP + "_" + System.currentTimeMillis() + "_b" + beanID;
	    	System.out.println("Client --> Full name: " + fullClientName);	
	    	
    		printCltMsg("Create SALConnector");  		
    		salCon = new SalConnector(fullClientName, AGENT_RMI_REG_IP, OUR_IP, this);    	
	    	salCon.connectToAgent();  	
    	}
	}    
    
    @Override
    public void stop(){
    	salCon.disconnectFromAgent();
    	salCon = null;
    }
    
    @Override
	public void handle(Event e) {
    	printCltMsg("Event received: " + e.toString());
	}

	private void updateCache() {
		
		String exName = "update cache";
		if(mode == ClientMode.CACHE){
			try {
				salicache.updateAll();
			} catch (SALException e) {
				//TODO Handle ex
				formatException(e, exName);
			} catch (TechnicalException e) {
				//TODO handle ex
				formatException(e, exName);
			}
		}
	}

	@Override
	public void collect(Response arg0) {
		printCltMsg("Collect function has been called!!");
	}
	
	
	@Override
	public String test(){
		//Test stuff, no logic
    	String dummyTxt = "Client --> test call successful!!";
    	printCltMsg(dummyTxt);
    	return dummyTxt;
	}
	
	@Override
	public SMLDescriptions getSensorList() throws SALException, TechnicalException{	
		printCltMsg("getSensorList");
		
		SMLDescriptions sensorList = null;
		
		if (checkCache()) {
			sensorList = salicache.getSensorList();
		} else {
			sensorList = salCon.listAllSensors();
		}

		return sensorList;
	}
	
	@Override
	public SMLDescriptions getSensorListActive() throws SALException, TechnicalException{
		printCltMsg("getSensorListActive");
		
		SMLDescriptions sensorList = null;
		
		if (checkCache()) {
			sensorList = salicache.getSensorListActive();
		} else {
			sensorList = salCon.getActiveSensors();
		}

		return sensorList;
	}
	
	@Override
	public void removeSensor(int sid) throws SALException, TechnicalException{
		printCltMsg("removeSensor, SID: " + sid);
		salCon.remSensor(Integer.toString(sid));
		updateCache();
	}
	
	@Override
	public void addSensor(String xmlDoc) throws SALException, TechnicalException{
		printCltMsg("addSensor, XML");
		salCon.addSensor(xmlDoc);
		updateCache();
	}
	
	@Override
	public String getProtocolList() throws TechnicalException{
		printCltMsg("getProtocolList");
		
		String protocolList = null;
		
		if (checkCache()) {
			protocolList = salicache.getProtocolList();
		} else {
			protocolList = salCon.getProtocolsList();
		}

		return protocolList;
	}
	
	@Override
	public void addProtocol(String xmlDoc, boolean doAssociate) throws SALException, TechnicalException{
		printCltMsg("addProtocol, XML, doAssociate: " + doAssociate);
		salCon.addProtocol(xmlDoc, doAssociate);
		updateCache();
	}
	
	@Override
	public void removeProtocol(String pid, boolean remAssociate) throws SALException, TechnicalException{
		printCltMsg("removeProtocol, PID: " + pid + "remAssociate: " + remAssociate);
		salCon.removeProtocol(pid, remAssociate);
		updateCache();
	}
	
	@Override
	public Set<CMLDescription> getCommands(int sid) throws SALException, TechnicalException{
		printCltMsg("getCommands, SID: " + sid);
		
		Set<CMLDescription> cmdList = null;
		
		if (checkCache()) {
			cmdList = salicache.getSensorCommands(Integer.toString(sid));
		} else {
			cmdList = salCon.getSensorCommands(sid);
		}

		return cmdList;	
	}
	
	private boolean checkCache() {
		if(mode != ClientMode.CACHE)
			return false;
		else{
			if (salicache == null){
				createSalCache();
			}
			return salicache.isCacheReady();
		}
	}

	@Override
	public Response sendCommand(SensorCommand scmd) throws SALException, TechnicalException{	
		printCltMsg("getCommands, SID: " + scmd.getSid() + ", CID: " + scmd.getCid());
		
		return salCon.sendCommand(scmd);
	}


	@Override
	public int getID() {
		return beanID;
	}	
	
	private void formatException(Exception e, String string) {
		printCltMsg("EX -> " + string);
		e.printStackTrace();
	}


	@Override
	public void renewConnection() {
		//Got to create a new object because of rmiregistry. 
		//unbound seems not to work
		//TODO destroy cache and re-init
		//TODO check why 2 connection attempts after renew
		salCon = null;
		createSalConnector();
		salCon.setCache(salicache);
		salicache.setConnector(salCon);	
	}
	
}
