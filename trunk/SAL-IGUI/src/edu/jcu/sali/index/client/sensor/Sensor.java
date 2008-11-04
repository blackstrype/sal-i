/**
 * 
 */
package edu.jcu.sali.index.client.sensor;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.jcu.sali.index.client.sensor.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensor.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.sensor.sensorlist.SensorListPanel;

/**
 * @author Marc
 *
 */
public class Sensor extends HorizontalPanel {
	
	private SensorDisplayPanel sensorDisplayPanel;
	private SensorListPanel sensorListPanel;
	private CommandListPanel commandListPanel;

	public Sensor() {
		this.setStyleName("sensorPanel");
		sensorDisplayPanel = new SensorDisplayPanel();
		commandListPanel = new CommandListPanel(sensorDisplayPanel);

		this.buildPanel();
	}
	
	public void buildPanel() {
		
		// Sensor list
		sensorListPanel = new SensorListPanel(commandListPanel,sensorDisplayPanel);
		sensorListPanel.setWidth("200px");
		sensorListPanel.setStyleName("sensorListPanel");
		this.add(sensorListPanel);
		
		
		VerticalPanel rightMiddlePanel = new VerticalPanel();
		// Sensor display
		sensorDisplayPanel.setWidth("780px");
		sensorDisplayPanel.setHeight("360px");
		sensorDisplayPanel.setStyleName("sensorDisplayPanel");
		rightMiddlePanel.add(sensorDisplayPanel);

		commandListPanel.setWidth("700px");
		commandListPanel.setHeight("200px");
		commandListPanel.setStyleName("commandListPanel");
		rightMiddlePanel.add(commandListPanel);
		this.add(rightMiddlePanel);		
	}

}
