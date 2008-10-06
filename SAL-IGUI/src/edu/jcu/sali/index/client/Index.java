package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.sensorlist.SensorListPanel;



public class Index implements EntryPoint {
	
	private VerticalPanel wrapperPanel;
	private DockPanel sensorDisplayPanel;
	private SensorListPanel sensorListPanel;
	private CommandListPanel commandListPanel;
	private DeviceOutputPanel deviceOutputPanel;

	
	public void onModuleLoad() {
		commandListPanel = new CommandListPanel();
		BuildBaseUI();
	}

	
	// Establish the base layout for display using GWT's panels
	private void BuildBaseUI() {
		wrapperPanel = new VerticalPanel();
		wrapperPanel.setStyleName("wrapperPanel");

		// Title of header panel
		DockPanel headerPanel = new DockPanel();
		headerPanel.setStyleName("headerPanel");
		headerPanel.add(new HTML("<h1>SAL-I</h1><h2>Sensor Networks Interface</h2>"),DockPanel.WEST);
		Hyperlink hl_about = new Hyperlink("About","about");
		headerPanel.add(hl_about,DockPanel.EAST);
		wrapperPanel.add(headerPanel);

		// Upper middle panel
		HorizontalPanel upperMiddlePanel = new HorizontalPanel();
		upperMiddlePanel.setStyleName("upperMiddlePanel");
		// Sensor list
		sensorListPanel = new SensorListPanel(commandListPanel);
		sensorListPanel.setWidth("200px");
		sensorListPanel.setHeight("430px");
		sensorListPanel.setStyleName("sensorListPanel");
		upperMiddlePanel.add(sensorListPanel);
		// Sensor display
		sensorDisplayPanel = new SensorDisplayPanel();
		sensorDisplayPanel.setWidth("780px");
		sensorDisplayPanel.setHeight("430px");
		sensorDisplayPanel.setStyleName("sensorDisplayPanel");
		upperMiddlePanel.add(sensorDisplayPanel);
		wrapperPanel.add(upperMiddlePanel);

		// Lower middle panel
		HorizontalPanel lowerMiddlePanel = new HorizontalPanel();
		lowerMiddlePanel.setStyleName("lowerMiddlePanel");
		// Command list
		commandListPanel.setHeight("200px");
		commandListPanel.setWidth("495px");
		commandListPanel.setStyleName("commandListPanel");
		lowerMiddlePanel.add(commandListPanel);
		// Device output
		deviceOutputPanel = new DeviceOutputPanel();
		deviceOutputPanel.setHeight("200px");
		deviceOutputPanel.setWidth("495px");
		deviceOutputPanel.setStyleName("deviceOutputPanel");
		lowerMiddlePanel.add(deviceOutputPanel);
		wrapperPanel.add(lowerMiddlePanel);

		RootPanel.get("wrapperDiv").add(wrapperPanel);
		return;
	}

}


