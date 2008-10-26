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

	public SensorListPanel(CommandListPanel commandListPanel,
			SensorDisplayPanel sensorDisplayPanel) {
		this.sensorLoaderPanel = Utilities.getLoaderPanel();
		sensorList = new SensorListBox(this, commandListPanel,
				sensorDisplayPanel);
		
		sensorLoaderPanel.add(sensorList);
		this.add(sensorLoaderPanel);
	}

	public void toggleLoaderPanel() {
		if (sensorLoaderPanel.getVisibleWidget() == 0
				&& sensorLoaderPanel.getWidgetCount() > 0) {
			sensorLoaderPanel.showWidget(1);
		} else {
			sensorLoaderPanel.showWidget(0);
		}
	}
}
