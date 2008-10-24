/**
 * 
 */
package edu.jcu.sali.index.client.sensorlist;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.StackPanel;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.utilities.Utilities;

/**
 * @author Marc
 * 
 */
public class SensorListPanel extends StackPanel {

	private SensorListBox sensorList;
	private DeckPanel sensorLoaderPanel;
	private ProtocolListBox protocolList;
	private DeckPanel protocolLoaderPanel;

	public SensorListPanel(CommandListPanel commandListPanel,
			SensorDisplayPanel sensorDisplayPanel) {
		this.sensorLoaderPanel = Utilities.getLoaderPanel();
		sensorList = new SensorListBox(this, commandListPanel,
				sensorDisplayPanel);
		sensorLoaderPanel.add(sensorList);
		this.add(sensorLoaderPanel);

//		this.protocolLoaderPanel = Utilities.getLoaderPanel();
//		protocolList = new ProtocolListBox(this, sensorDisplayPanel);
//		protocolLoaderPanel.add(protocolList);
//		this.add(protocolLoaderPanel);

	}

	public void toggleLoaderPanel() {
//		if (this.getSelectedIndex() == 0) {
			if (sensorLoaderPanel.getVisibleWidget() == 0
					&& sensorLoaderPanel.getWidgetCount() > 0) {
				sensorLoaderPanel.showWidget(1);
			} else {
				sensorLoaderPanel.showWidget(0);
//			}
//		} else {
//			if (protocolLoaderPanel.getVisibleWidget() == 0
//					&& protocolLoaderPanel.getWidgetCount() > 0) {
//				protocolLoaderPanel.showWidget(1);
//			} else {
//				protocolLoaderPanel.showWidget(0);
//			}
		}
	}
}
