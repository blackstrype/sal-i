package edu.jcu.sali.index.client.protocol;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("protocolservice")
public interface ProtocolService extends RemoteService {
	
	public String getProtocolList() throws Exception;
	
	public void addProtocol(String newProtocol) throws Exception;
	public void removeProtocol(int pid, boolean remAssociate);

	public static class Util {

		public static ProtocolServiceAsync getInstance() {

			return GWT.create(ProtocolService.class);
		}
	}

}
