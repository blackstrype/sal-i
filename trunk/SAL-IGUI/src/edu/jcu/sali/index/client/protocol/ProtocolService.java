package edu.jcu.sali.index.client.protocol;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * This interface determines the functionality of the services for the
 * protocol-package.
 * 
 * @author Marc Hammerton
 * 
 */
@RemoteServiceRelativePath("protocolservice")
public interface ProtocolService extends RemoteService {

	/**
	 * Returns the protocol-list.
	 * 
	 * @return protocol-list
	 * @throws Exception
	 */
	public String getProtocolList() throws Exception;

	/**
	 * Adds a new protocol.
	 * 
	 * @param newProtocol
	 *            XML-structure for the new protocol.
	 * @throws Exception
	 */
	public void addProtocol(String newProtocol) throws Exception;

	/**
	 * Removes a protocol.
	 * 
	 * @param pid
	 *            The id of the protocol to be removed.
	 * @param remAssociate
	 *            If the associated sensors should be removed.
	 */
	public void removeProtocol(String pid, boolean remAssociate);

	/**
	 * Inner-class to return an instance of the service.
	 * 
	 * @author Marc Hammerton
	 * 
	 */
	public static class Util {

		/**
		 * Returns an instance of the service.
		 * 
		 * @return ProtocolServiceAsync
		 */
		public static ProtocolServiceAsync getInstance() {

			return GWT.create(ProtocolService.class);
		}
	}

}
