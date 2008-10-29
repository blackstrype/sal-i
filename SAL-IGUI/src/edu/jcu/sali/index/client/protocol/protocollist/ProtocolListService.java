package edu.jcu.sali.index.client.protocol.protocollist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("protocollistservice")
public interface ProtocolListService extends RemoteService {
	
	public String getProtocolList() throws Exception;

	public static class Util {

		public static ProtocolListServiceAsync getInstance() {

			return GWT.create(ProtocolListService.class);
		}
	}

}
