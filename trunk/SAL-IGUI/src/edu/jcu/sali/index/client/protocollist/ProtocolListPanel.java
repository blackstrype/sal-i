package edu.jcu.sali.index.client.protocollist;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.StackPanel;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.utilities.Utilities;

public class ProtocolListPanel extends StackPanel {

	private ProtocolListBox protocolList;
	private DeckPanel protocolLoaderPanel;

	public ProtocolListPanel(CommandListPanel commandListPanel,
			SensorDisplayPanel sensorDisplayPanel) {

		this.protocolLoaderPanel = Utilities.getLoaderPanel();
		protocolList = new ProtocolListBox(this, sensorDisplayPanel);
		protocolLoaderPanel.add(protocolList);
		this.add(protocolLoaderPanel);

	}

	public void toggleLoaderPanel() {

		if (protocolLoaderPanel.getVisibleWidget() == 0
				&& protocolLoaderPanel.getWidgetCount() > 0) {
			protocolLoaderPanel.showWidget(1);
		} else {
			protocolLoaderPanel.showWidget(0);
		}
	}

}
