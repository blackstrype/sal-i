package edu.jcu.sali.index.client.protocol;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProtocolServiceAsync {
	
	public void getProtocolList(AsyncCallback<String> callback);
	
	public void addProtocol(String newProtocol, AsyncCallback<?> callback);

}
