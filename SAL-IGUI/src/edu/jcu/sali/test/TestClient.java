package edu.jcu.sali.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jcu.sal.common.Parameters;
import jcu.sal.common.Response;
import jcu.sal.common.Parameters.Parameter;
import jcu.sal.common.cml.ArgumentType;
import jcu.sal.common.cml.CMLConstants;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.cml.ReturnType;
import jcu.sal.common.exceptions.SALDocumentException;
import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.ClientLocal;
import edu.sal.sali.ejb.protocol.SensorCommand;

/**
 * The TestClient can be used instead of the EJB-client. It provides sample
 * sensors and commands. To assure the compatibility with the EJB-client, the
 * ClientLocal interface is implemented.
 * 
 * @author Marc Hammerton
 * 
 */
public class TestClient implements ClientLocal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#test()
	 */
	public String test() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#getSensorListActive()
	 */
	public SMLDescriptions getSensorListActive() {
		Set<SMLDescription> smlDescSet = new HashSet<SMLDescription>();
		for (int i = 0; i < 10; i++) {
			SMLDescription smlDesc;
			try {
				List<Parameter> param = new ArrayList<Parameters.Parameter>();
				Parameters.Parameter p1 = new Parameters.Parameter("Address",
						"SystemTime");
				param.add(p1);
				Parameters.Parameter p2 = new Parameters.Parameter(
						"ProtocolType", "PlatformData");
				param.add(p2);
				Parameters.Parameter p3 = new Parameters.Parameter(
						"ProtocolName", "osdata");
				param.add(p3);
				Parameters p = new Parameters(param);
				smlDesc = new SMLDescription(i, p);
				smlDescSet.add(smlDesc);
			} catch (SALDocumentException e) {
				e.printStackTrace();
			}
		}
		SMLDescriptions listDesc = new SMLDescriptions(smlDescSet);

		return listDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#getCommands(int)
	 */
	public Set<CMLDescription> getCommands(int sid) {
		Set<CMLDescription> commands = new HashSet<CMLDescription>();

		CMLDescription cml1 = new CMLDescription("Enable", 10, "Enable",
				"Enables the sensor", new ArrayList(), new ArrayList(),
				new ReturnType(CMLConstants.RET_TYPE_VOID));
		commands.add(cml1);
		CMLDescription cml2 = new CMLDescription("Disable", 11, "Disable",
				"Disables the sensor", new ArrayList(), new ArrayList(),
				new ReturnType(CMLConstants.RET_TYPE_VOID));
		commands.add(cml2);
		CMLDescription cml3 = new CMLDescription("GetLoadAvg", 1000,
				"GetLoadAvg", "Reads the 1-minute load average",
				new ArrayList(), new ArrayList(), new ReturnType(
						CMLConstants.RET_TYPE_FLOAT));
		commands.add(cml3);
		CMLDescription cml4 = new CMLDescription("getReading", 100,
				"getReading", "Reads the 1-minute load average",
				new ArrayList(), new ArrayList(), new ReturnType(
						CMLConstants.RET_TYPE_FLOAT));
		commands.add(cml4);

		ArrayList<ArgumentType> argTypes = new ArrayList<ArgumentType>();
		argTypes.add(new ArgumentType(CMLConstants.ARG_TYPE_STRING));
		argTypes.add(new ArgumentType(CMLConstants.ARG_TYPE_INT));

		ArrayList<String> argNames = new ArrayList<String>();
		argNames.add("ParaString");
		argNames.add("ParaInt");

		CMLDescription cml5 = new CMLDescription("getArgs", 100, "getArgs",
				"Test for arguments", argTypes, argNames, new ReturnType(
						CMLConstants.RET_TYPE_FLOAT));
		commands.add(cml5);

		return commands;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#removeProtocol(int, boolean)
	 */
	public void removeProtocol(String pid, boolean remAssociate) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#addProtocol(java.lang.String, boolean)
	 */
	public void addProtocol(String xmlDoc, boolean doAssociate) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#getProtocolList()
	 */
	public String getProtocolList() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#addSensor(java.lang.String)
	 */
	public void addSensor(String xmlDoc) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#removeSensor(int)
	 */
	public void removeSensor(int sid) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#stop()
	 */
	public void stop() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeedu.sal.sali.ejb.ClientLocal#sendCommand(edu.sal.sali.ejb.protocol.
	 * SensorCommand)
	 */
	public Response sendCommand(SensorCommand scmd) {
		Response resp = null;
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.sal.sali.ejb.ClientLocal#getSensorList()
	 */
	public SMLDescriptions getSensorList() {
		Set<SMLDescription> smlDescSet = new HashSet<SMLDescription>();
		for (int i = 0; i < 10; i++) {
			SMLDescription smlDesc;
			try {
				List<Parameter> param = new ArrayList<Parameters.Parameter>();
				Parameters.Parameter p1 = new Parameters.Parameter("Address",
						"SystemTime");
				param.add(p1);
				Parameters.Parameter p2 = new Parameters.Parameter(
						"ProtocolType", "PlatformData");
				param.add(p2);
				Parameters.Parameter p3 = new Parameters.Parameter(
						"ProtocolName", "osdata");
				param.add(p3);
				Parameters p = new Parameters(param);
				smlDesc = new SMLDescription(i, p);
				smlDescSet.add(smlDesc);
			} catch (SALDocumentException e) {
				e.printStackTrace();
			}
		}
		SMLDescriptions listDesc = new SMLDescriptions(smlDescSet);

		return listDesc;
	}

}
