package edu.sal.sali.ejb.cache;

import java.util.Hashtable;
import java.util.Set;

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

		updateSensorData();

		polling = new PollingThread(this, mode);
		polling.start();
	}


	public void updateSensorData() {
		// init
		String sensorID;
		Set<CMLDescription> tmp_sensorCmdDescription;
		SMLDescriptions tmp_sensorList;
		Hashtable<String, Set<CMLDescription>> tmp_sensorCommands = 
							new Hashtable<String, Set<CMLDescription>>();
		String tmp_protocolList;
		SMLDescriptions tmp_sensorListActive;

		// get sensor list
		tmp_sensorList = salCon.listAllSensors();

		// get ACTIVE sensorlist
		tmp_sensorListActive = salCon.getActiveSensors();

		// get command list
		for (SMLDescription sensor : tmp_sensorList.getDescriptions()) {
			sensorID = sensor.getID();
			
			tmp_sensorCmdDescription = salCon.getSensorCommands(Integer.parseInt(sensorID));
			
			//in case sensor got deleted meanwhile
			if(tmp_sensorCmdDescription != null){
				tmp_sensorCommands.put(sensorID, salCon.getSensorCommands(Integer
						.parseInt(sensorID)));
			}
		}

		// get protocol list
		tmp_protocolList = salCon.getProtocolsList();
		
		//copy tmp objects into real objects
		sensorList = tmp_sensorList;
		sensorListActive = tmp_sensorListActive;
		sensorCommands = tmp_sensorCommands;
		protocolList = tmp_protocolList;

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

	public void setSensorListActive(
			SMLDescriptions sensorListActive) {
		this.sensorListActive = sensorListActive;
	}

}
