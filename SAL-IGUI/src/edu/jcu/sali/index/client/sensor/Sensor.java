package edu.jcu.sali.index.client.sensor;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.jcu.sali.index.client.sensor.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensor.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.sensor.sensorlist.SensorListPanel;

/**
 * Base class for the sensor-package. It initializes the contents for the
 * sensor-panel.
 * 
 * @author Marc Hammerton
 * 
 */
public class Sensor extends HorizontalPanel {
	
	private SensorDisplayPanel sensorDisplayPanel;
	private SensorListPanel sensorListPanel;
	private CommandListPanel commandListPanel;

	/**
	 * Initialize the sensor-panel.
	 */
	public Sensor() {
		this.setStyleName("sensorPanel");
		sensorDisplayPanel = new SensorDisplayPanel();
		commandListPanel = new CommandListPanel(sensorDisplayPanel);

		this.buildPanel();
	}
	
	
	/**
	 * Build the components for the sensor-panel. It consists of the
	 * sensor-list-panel, sensor-display-panel and the command-list-panel.
	 */
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

		// Command list
		commandListPanel.setWidth("700px");
		commandListPanel.setHeight("200px");
		commandListPanel.setStyleName("commandListPanel");
		rightMiddlePanel.add(commandListPanel);
		this.add(rightMiddlePanel);		
	}

}
