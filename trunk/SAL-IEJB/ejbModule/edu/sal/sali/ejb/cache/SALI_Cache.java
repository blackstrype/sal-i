package edu.sal.sali.ejb.cache;

import java.util.Hashtable;
import java.util.Set;

import edu.sal.sali.ejb.exeption.SALException;
import edu.sal.sali.ejb.exeption.TechnicalException;
import edu.sal.sali.rmi.SalConnector;

import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;

public class SALI_Cache {

	private SMLDescriptions sensorList;
	private Hashtable<String, Set<CMLDescription>> sensorCommands;
	private SalConnector salCon;
	private PollingThread polling;
	private String protocolList;
	private SMLDescriptions sensorListActive;

	public SALI_Cache(SALI_CacheMode mode, SalConnector agent) {
		this.salCon = agent;
		sensorCommands = new Hashtable<String, Set<CMLDescription>>();

		polling = new PollingThread(this, mode);
		polling.start();
	}
	
	private void printCachMsg(String text) {
		System.out.println("SALCache --> "  + text);
	}

	public boolean isCacheReady(){
		
		boolean isReady = false;
		
		if (polling.isReady()){
			isReady = true;
		}else{
			isReady = false;
		}
		
		return isReady;
		
	}
	
	
	public void updateAll() throws SALException, TechnicalException {
		
		printCachMsg("Update all: start");
		
		updateSensorList();
		updateSensorListActive();
		updateSensorCommands();
		updateProtocolList();
		
		printCachMsg("Update all: done");
	}

	public void updateSensorList() throws SALException, TechnicalException {

		printCachMsg(" - Update sensor list: start");
		
		SMLDescriptions tmp_sensorList;

		tmp_sensorList = salCon.listAllSensors();
		sensorList = tmp_sensorList;
		
		printCachMsg(" - Update sensor list: done");
	}

	public void updateSensorListActive() throws SALException, TechnicalException {
		
		printCachMsg(" - Update sensor list active: start");

		SMLDescriptions tmp_sensorListActive;

		tmp_sensorListActive = salCon.getActiveSensors();
		sensorListActive = tmp_sensorListActive;
		
		printCachMsg(" - Update sensor list active: done");
	}

	public void updateSensorCommands() throws SALException, TechnicalException {
		
		printCachMsg(" - Update sensor commands: start");

		String sensorID;
		Set<CMLDescription> tmp_sensorCmdDescription;
		Hashtable<String, Set<CMLDescription>> tmp_sensorCommands = new Hashtable<String, Set<CMLDescription>>();

		// get command list
		for (SMLDescription sensor : sensorList.getDescriptions()) {
			sensorID = sensor.getID();

			tmp_sensorCmdDescription = salCon.getSensorCommands(Integer
					.parseInt(sensorID));

			// in case sensor got deleted meanwhile
			if (tmp_sensorCmdDescription != null) {
				tmp_sensorCommands.put(sensorID, salCon
						.getSensorCommands(Integer.parseInt(sensorID)));
			}
		}
		sensorCommands = tmp_sensorCommands;
		
		printCachMsg(" - Update sensor commands: done");
	}

	
	public void updateProtocolList() throws TechnicalException {
		
		printCachMsg(" - Update protocol list: start");

		String tmp_protocolList;

		tmp_protocolList = salCon.getProtocolsList();
		protocolList = tmp_protocolList;
		
		printCachMsg(" - Update protocol list: done");
	}

	public void setProtocolList(String protocolList) {
		this.protocolList = protocolList;
	}

	public String getProtocolList() {
		return protocolList;
	}

	public Set<CMLDescription> getSensorCommands(String sensorID) {
		return sensorCommands.get(sensorID);
	}

	public SMLDescriptions getSensorList() {
		return sensorList;
	}

	public SMLDescriptions getSensorListActive() {
		return sensorListActive;
	}

	public void setSensorListActive(SMLDescriptions sensorListActive) {
		this.sensorListActive = sensorListActive;
	}

	public void setConnector(SalConnector salCon) {
		this.salCon = salCon;
	}

}
