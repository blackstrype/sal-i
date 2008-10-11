package edu.sal.sali.ejb;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.Local;

import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.sml.SMLDescriptions;

@Local
public interface ClientLocal {
	
	public String test();

	ArrayList<String> getSensorList();

	Set<CMLDescription> getCommands(int sid);

	void removeProtocol(int pid, boolean remAssociate);

	void addProtocol(String xmlDoc, boolean doAssociate);

	String getProtocolList();

	void addSensor(String xmlDoc);

	void removeSensor(int sid);

	void stop();

	SMLDescriptions getSensorListActive();
	
	
	
}
	