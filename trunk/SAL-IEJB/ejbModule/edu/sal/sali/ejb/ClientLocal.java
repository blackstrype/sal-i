package edu.sal.sali.ejb;
import java.util.Set;

import javax.ejb.Local;

import jcu.sal.common.Response;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.exeption.SALException;
import edu.sal.sali.ejb.exeption.TechnicalException;
import edu.sal.sali.ejb.protocol.SensorCommand;

@Local
public interface ClientLocal {
	
	public String test();

	SMLDescriptions getSensorList() throws SALException, TechnicalException;

	Set<CMLDescription> getCommands(int sid) throws SALException, TechnicalException;

	void removeProtocol(String pid, boolean remAssociate) throws SALException, TechnicalException;

	void addProtocol(String xmlDoc, boolean doAssociate) throws SALException, TechnicalException;

	String getProtocolList() throws TechnicalException;

	void addSensor(String xmlDoc) throws SALException, TechnicalException;

	void removeSensor(int sid) throws SALException, TechnicalException;

	void stop();

	SMLDescriptions getSensorListActive() throws SALException, TechnicalException;

	Response sendCommand(SensorCommand scmd) throws SALException, TechnicalException;
	
	
	
}
	