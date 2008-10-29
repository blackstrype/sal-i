/**
 * 
 */
package edu.jcu.sali.index.client.protocol;

import com.google.gwt.user.client.ui.HorizontalPanel;

import edu.jcu.sali.index.client.protocol.protocolfunctions.ProtocolFunctionsPanel;
import edu.jcu.sali.index.client.protocol.protocollist.ProtocolListPanel;

/**
 * @author Marc
 *
 */
public class Protocol extends HorizontalPanel {

	private ProtocolFunctionsPanel protocolFunctionsPanel;
	private ProtocolListPanel protocolListPanel;

	public Protocol() {
		this.setStyleName("protocolPanel");
		this.buildPanel();
	}
	
	public void buildPanel() {		
		
		// Protocol Functions
		protocolFunctionsPanel = new ProtocolFunctionsPanel();
		protocolFunctionsPanel.setWidth("200px");
		protocolFunctionsPanel.setStyleName("protocolFunctionsPanel");
		this.add(protocolFunctionsPanel);		
		
		// Sensor list
		protocolListPanel = new ProtocolListPanel();
		protocolListPanel.setWidth("780px");
		protocolListPanel.setStyleName("protocolListPanel");
		this.add(protocolListPanel);
		
	}
}
