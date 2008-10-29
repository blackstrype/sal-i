package edu.jcu.sali.index.server.protocollist;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.jcu.sali.index.client.protocol.protocollist.ProtocolListService;
import edu.sal.sali.ejb.ClientLocal;

public class ProtocolListServiceImpl extends RemoteServiceServlet implements
		ProtocolListService {

	@EJB
	ClientLocal client;

	// private TestClient client;

	public ProtocolListServiceImpl() {
		// client = new TestClient();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public ArrayList<ArrayList<String>> getProtocolList() throws Exception {
//		ArrayList<ArrayList<String>> protocolList = new ArrayList<ArrayList<String>>();
//
//		try {
//			protocolList = client.getProtocolList();
//			System.out.println(protocolList);
//		} catch (Exception ex) {
//			ArrayList<String> sensor = new ArrayList<String>();
//			sensor.add("0");
//			sensor.add(null);
//			sensor.add(null);
//			sensor.add(null);
//			sensorList.add(sensor);
//		}
	public String getProtocolList() throws Exception {

		String protocolList = "";
		
		try {
			protocolList = client.getProtocolList();
			System.out.println(protocolList);
		} catch (Exception ex) {
			protocolList = "no protocols available";
		}
		return protocolList;
	}

}
