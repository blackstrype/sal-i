package edu.jcu.sali.index.client.protocol;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This interface determines the functionality of the services for the
 * protocol-package.
 * 
 * @author Marc Hammerton
 * 
 */
public interface ProtocolServiceAsync {

	/**
	 * Returns the protocol-list.
	 * 
	 * @return protocol-list
	 * @throws Exception
	 */
	public void getProtocolList(AsyncCallback<String> callback);

	/**
	 * Adds a new protocol.
	 * 
	 * @param newProtocol
	 *            XML-structure for the new protocol.
	 * @throws Exception
	 */
	public void addProtocol(String newProtocol, AsyncCallback<?> callback);

	/**
	 * Removes a protocol.
	 * 
	 * @param pid
	 *            The id of the protocol to be removed.
	 * @param remAssociate
	 *            If the associated sensors should be removed.
	 */
	public void removeProtocol(String pid, boolean remAssociate, AsyncCallback<?> callback);

}
