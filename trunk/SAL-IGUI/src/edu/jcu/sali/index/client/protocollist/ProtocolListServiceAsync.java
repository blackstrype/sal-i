package edu.jcu.sali.index.client.protocollist;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProtocolListServiceAsync {
	
	public void getProtocolList(AsyncCallback<String> callback);

}
