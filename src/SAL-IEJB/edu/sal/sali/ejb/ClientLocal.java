package edu.sal.sali.ejb;
import java.util.Set;

import javax.ejb.Local;

import jcu.sal.common.Response;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.protocol.SensorCommand;

@Local
public interface ClientLocal {
	
	public String test();

	SMLDescriptions getSensorList();

	Set<CMLDescription> getCommands(int sid);

	void removeProtocol(int pid, boolean remAssociate);

	void addProtocol(String xmlDoc, boolean doAssociate);

	String getProtocolList();

	void addSensor(String xmlDoc);

	void removeSensor(int sid);

	void stop();

	SMLDescriptions getSensorListActive();

	Response sendCommand(SensorCommand scmd);
	
	
	
}
	