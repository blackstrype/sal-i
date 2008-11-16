package edu.jcu.sali.index.client.protocol;

import com.google.gwt.user.client.ui.HorizontalPanel;

import edu.jcu.sali.index.client.protocol.protocolfunctions.ProtocolFunctionsPanel;
import edu.jcu.sali.index.client.protocol.protocollist.ProtocolListPanel;

/**
 * Base class for the protocol-package. It initializes the contents for the
 * protocol-panel.
 * 
 * @author Marc Hammerton
 * 
 */
public class Protocol extends HorizontalPanel {

	private ProtocolFunctionsPanel protocolFunctionsPanel;
	private ProtocolListPanel protocolListPanel;

	/**
	 * Initialize the protocol-panel.
	 */
	public Protocol() {
		this.setStyleName("protocolPanel");
		this.buildPanel();
	}

	/**
	 * Build the components for the protocol-panel. It consists of the
	 * functions-panel and the protocol-list-panel.
	 */
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
