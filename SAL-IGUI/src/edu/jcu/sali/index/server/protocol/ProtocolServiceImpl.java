package edu.jcu.sali.index.server.protocol;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.jcu.sali.index.client.protocol.ProtocolService;
import edu.sal.sali.ejb.ClientLocal;
import edu.sal.sali.ejb.exeption.SALException;
import edu.sal.sali.ejb.exeption.TechnicalException;

public class ProtocolServiceImpl extends RemoteServiceServlet implements
		ProtocolService {

	@EJB
	ClientLocal client;

//	 private TestClient client;

	public ProtocolServiceImpl() {
//		 client = new TestClient();
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

	public void removeProtocol(int pid, boolean remAssociate) {
		try {
			client.removeProtocol(pid, remAssociate);
		} catch (SALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
