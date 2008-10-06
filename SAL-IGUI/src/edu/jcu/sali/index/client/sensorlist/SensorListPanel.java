/**
 * 
 */
package edu.jcu.sali.index.client.sensorlist;

import com.google.gwt.user.client.ui.VerticalPanel;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;

/**
 * @author Marc
 *
 */
public class SensorListPanel extends VerticalPanel {

	private SensorListBox sensorList;
	
	public SensorListPanel(CommandListPanel commandListPanel) {
		sensorList = new SensorListBox(commandListPanel);
		this.add(sensorList);
	}
	

}
