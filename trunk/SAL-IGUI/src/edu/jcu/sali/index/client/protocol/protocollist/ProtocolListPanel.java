package edu.jcu.sali.index.client.protocol.protocollist;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.protocol.ProtocolService;
import edu.jcu.sali.index.client.protocol.ProtocolServiceAsync;
import edu.jcu.sali.index.client.utilities.Utilities;

/**
 * The ProtocolListPanel contains a tab-panel displaying the protocols as
 * XML-structure.
 * 
 * @author Marc Hammerton
 * 
 */
public class ProtocolListPanel extends DockPanel {

	private TabPanel tabPanel;
	private Widget loaderWidget;
	private TextArea ta_protocolsXML;

	private Timer updateTimer;

	/**
	 * Initialize the panel. Add a headline and the tab-panel to the panel. Get
	 * the protocol-list and update it every 10 sec.
	 */
	public ProtocolListPanel() {
		this.add(new HTML("<h1>Protocol List</h1>"), DockPanel.NORTH);
		this.loaderWidget = Utilities.getLoaderWidget();

		// Create a tab panel
		tabPanel = new TabPanel();
		// Set the width to 400 pixels
		tabPanel.setWidth("700px");
		tabPanel.setHeight("400px");

		// Add a XML tab
		updateData();

		// Make the first tab selected and the tab's content visible
		tabPanel.selectTab(0);
		this.add(tabPanel, DockPanel.CENTER);

		updateTimer = new Timer() {
			public void run() {
				updateData();
			}
		};
		updateTimer.scheduleRepeating(10000);
	}

	/**
	 * Get the protocol-list from the service.
	 */
	public void updateData() {
		showLoaderWidget();

		ProtocolServiceAsync instance = ProtocolService.Util.getInstance();
		instance.getProtocolList(new AsyncCallback() {

			public void onFailure(Throwable error) {
				setFailureText();
			}

			public void onSuccess(Object retValue) {
				String protocols = (String) retValue;
				setXMLTab(protocols);
			}
		});

	}

	/**
	 * Add the protocol XML-structure to the tab-panel.
	 * 
	 * @param protocols
	 *            The protocol XML-structure
	 */
	public void setXMLTab(String protocols) {
		tabPanel.remove(0);
		ta_protocolsXML = new TextArea();
		ta_protocolsXML.setText(protocols);
		ta_protocolsXML.setVisibleLines(20);
		ta_protocolsXML.setReadOnly(true);
		tabPanel.add(ta_protocolsXML, "XML");
		tabPanel.selectTab(0);
	}

	/**
	 * Informs the user that a failure occurred while trying to retrieve the
	 * protocol-list.
	 */
	public void setFailureText() {
		tabPanel.remove(0);
		tabPanel.add(new HTML("No data available."), "XML");
		tabPanel.selectTab(0);
	}

	/**
	 * Display the loader-widget while getting the data.
	 */
	public void showLoaderWidget() {
		if (tabPanel.getTabBar().getTabCount() > 0) {
			tabPanel.remove(0);
		}
		tabPanel.add(loaderWidget, "XML");
		tabPanel.selectTab(0);
	}

}
