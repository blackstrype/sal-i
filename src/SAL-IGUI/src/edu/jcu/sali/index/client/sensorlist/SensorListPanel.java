/**
 * 
 */
package edu.jcu.sali.index.client.sensorlist;

import com.google.gwt.user.client.ui.VerticalPanel;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;

/**
 * @author Marc
 *
 */
public class SensorListPanel extends VerticalPanel {

	private SensorListBox sensorList;
	
	public SensorListPanel(CommandListPanel commandListPanel, SensorDisplayPanel sensorDisplayPanel) {
		sensorList = new SensorListBox(commandListPanel, sensorDisplayPanel);
		this.add(sensorList);
	}
	

}
