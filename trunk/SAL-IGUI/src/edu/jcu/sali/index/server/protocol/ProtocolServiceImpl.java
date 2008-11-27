package edu.jcu.sali.index.server.protocol;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.jcu.sali.index.client.protocol.ProtocolService;
import edu.sal.sali.ejb.ClientLocal;
import edu.sal.sali.ejb.exeption.SALException;
import edu.sal.sali.ejb.exeption.TechnicalException;

/**
 * Implementation of the protocol service. Communicates with the SAL-client.
 * 
 * @author Marc Hammerton
 * 
 */
public class ProtocolServiceImpl extends RemoteServiceServlet implements
		ProtocolService {

	@EJB
	ClientLocal client;

	// private TestClient client;

	public ProtocolServiceImpl() {
		// client = new TestClient();
	}

	/**
	 * Serial-Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see edu.jcu.sali.index.client.protocol.ProtocolService#getProtocolList()
	 */
	public String getProtocolList() throws Exception {

		String protocolList = "";

		try {
			protocolList = client.getProtocolList();
		} catch (Exception ex) {
			protocolList = "no protocols available";
		}
		return protocolList;
	}

	/**
	 * @see edu.jcu.sali.index.client.protocol.ProtocolService#addProtocol(String)
	 */
	public void addProtocol(String newProtocol) {
		try {
			client.addProtocol(newProtocol, false);
		} catch (Exception ex) {
			System.out.println("Error: no sensor added");
		}
	}

	/**
	 * @see edu.jcu.sali.index.client.protocol.ProtocolService#removeProtocol(int,
	 *      boolean)
	 */
	public void removeProtocol(String pid, boolean remAssociate) {
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
