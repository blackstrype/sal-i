package edu.jcu.sali.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jcu.sal.common.Parameters;
import jcu.sal.common.Response;
import jcu.sal.common.Parameters.Parameter;
import jcu.sal.common.cml.CMLConstants;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.cml.ReturnType;
import jcu.sal.common.exceptions.SALDocumentException;
import jcu.sal.common.sml.SMLDescription;
import jcu.sal.common.sml.SMLDescriptions;
import edu.sal.sali.ejb.ClientLocal;
import edu.sal.sali.ejb.protocol.SensorCommand;


public class TestClient implements ClientLocal {
	public String test() {
		return null;
	}

	
	public SMLDescriptions getSensorListActive() {
		Set<SMLDescription> smlDescSet = new HashSet<SMLDescription>();
		for(int i=0; i<10; i++) {
			SMLDescription smlDesc;
			try {
				List<Parameter> param = new ArrayList<Parameters.Parameter>();
				Parameters.Parameter p1 = new Parameters.Parameter("Address","SystemTime");
				param.add(p1);
				Parameters.Parameter p2 = new Parameters.Parameter("ProtocolType","PlatformData");
				param.add(p2);
				Parameters.Parameter p3 = new Parameters.Parameter("ProtocolName","osdata");
				param.add(p3);
				Parameters p = new Parameters(param);
				smlDesc = new SMLDescription(i,p);
				smlDescSet.add(smlDesc);
			} catch (SALDocumentException e) {
				e.printStackTrace();
			}
		}
		SMLDescriptions listDesc = new SMLDescriptions(smlDescSet);

		
//		String tempSensor;
//		ArrayList<String> sensorList = new ArrayList<String>();
//		
//		SMLDescriptions listDesc = salCon.getActiveSensors();
//		for(SMLDescription singleDesc : listDesc.getDescriptions()){
//			tempSensor = singleDesc.getSID() + "##" + singleDesc.getType();	
//			sensorList.add(tempSensor);
//		}
		
		return listDesc;
	}

	public Set<CMLDescription> getCommands(int sid) {
		Set<CMLDescription> commands = new HashSet<CMLDescription>();	
//		for(int i=0; i<5; i++) {
//			CMLDescription cml = new CMLDescription("method"+i,i,"name"+i,"some desc",new ArrayList(),new ArrayList(),new ReturnType(CMLConstants.RET_TYPE_STRING));		
//			commands.add(cml);
//		}
		
		CMLDescription cml1 = new CMLDescription("Enable",10,"Enable","Enables the sensor",new ArrayList(),new ArrayList(),new ReturnType(CMLConstants.RET_TYPE_VOID));
		commands.add(cml1);
		CMLDescription cml2 = new CMLDescription("Disable",11,"Disable","Disables the sensor",new ArrayList(),new ArrayList(),new ReturnType(CMLConstants.RET_TYPE_VOID));
		commands.add(cml2);
		CMLDescription cml3 = new CMLDescription("GetLoadAvg",1000,"GetLoadAvg","Reads the 1-minute load average",new ArrayList(),new ArrayList(),new ReturnType(CMLConstants.RET_TYPE_FLOAT));
		commands.add(cml3);
		CMLDescription cml4 = new CMLDescription("getReading",100,"getReading","Reads the 1-minute load average",new ArrayList(),new ArrayList(),new ReturnType(CMLConstants.RET_TYPE_FLOAT));
		commands.add(cml4);
		return commands;
	}

	public void removeProtocol(int pid, boolean remAssociate) {
	}

	public void addProtocol(String xmlDoc, boolean doAssociate) {
	}

	public String getProtocolList() {
		return null;
	}

	public void addSensor(String xmlDoc) {
	}

	public void removeSensor(int sid) {
	}

	public void stop() {
	}


	public ArrayList<String> getSensorList() {
		// TODO Auto-generated method stub
		return null;
	}


	public Response sendCommand(SensorCommand scmd) {
		// TODO Auto-generated method stub
		return null;
	}

}
