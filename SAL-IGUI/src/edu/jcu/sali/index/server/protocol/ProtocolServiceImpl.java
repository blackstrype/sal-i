package edu.jcu.sali.index.server.protocol;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.jcu.sali.index.client.protocol.ProtocolService;
import edu.jcu.sali.test.TestClient;

public class ProtocolServiceImpl extends RemoteServiceServlet implements
		ProtocolService {

//	@EJB
//	ClientLocal client;

	 private TestClient client;

	public ProtocolServiceImpl() {
		 client = new TestClient();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getProtocolList() throws Exception {

		String protocolList = "";
		
		try {
			protocolList = client.getProtocolList();
		} catch (Exception ex) {
			protocolList = "no protocols available";
		}
		return protocolList;
	}

	public void addProtocol(String newProtocol) {
		try {
			client.addProtocol(newProtocol, false);
		}
		catch (Exception ex) {
			System.out.println("Error: no sensor added");
		}		
	}
	
}
