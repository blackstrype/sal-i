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

		updateAll();

		polling = new PollingThread(this, mode);
		polling.start();
	}

	public void updateAll() {
		updateSensorList();
		updateSensorListActive();
		updateSensorCommands();
		updateProtocolList();
	}

	public void updateSensorList() {

		SMLDescriptions tmp_sensorList;

		tmp_sensorList = salCon.listAllSensors();
		sensorList = tmp_sensorList;
	}

	public void updateSensorListActive() {

		SMLDescriptions tmp_sensorListActive;

		tmp_sensorListActive = salCon.getActiveSensors();
		sensorListActive = tmp_sensorListActive;
	}

	public void updateSensorCommands() {

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
	}

	
	public void updateProtocolList() {

		String tmp_protocolList;

		tmp_protocolList = salCon.getProtocolsList();
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

	public void setSensorListActive(SMLDescriptions sensorListActive) {
		this.sensorListActive = sensorListActive;
	}

}
