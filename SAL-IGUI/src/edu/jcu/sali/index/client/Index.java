package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.deviceoutput.DeviceOutputPanel;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.sensorlist.SensorListPanel;



public class Index implements EntryPoint {
	
	private VerticalPanel wrapperPanel;
	private SensorDisplayPanel sensorDisplayPanel;
	private SensorListPanel sensorListPanel;
	private CommandListPanel commandListPanel;

	
	public void onModuleLoad() {
		sensorDisplayPanel = new SensorDisplayPanel();
		commandListPanel = new CommandListPanel(sensorDisplayPanel);
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
		sensorListPanel = new SensorListPanel(commandListPanel,sensorDisplayPanel);
		sensorListPanel.setWidth("200px");
		//sensorListPanel.setHeight("430px");
		sensorListPanel.setStyleName("sensorListPanel");
		upperMiddlePanel.add(sensorListPanel);
		
		VerticalPanel rightMiddlePanel = new VerticalPanel();
		// Sensor display
		sensorDisplayPanel.setWidth("780px");
		sensorDisplayPanel.setHeight("430px");
		sensorDisplayPanel.setStyleName("sensorDisplayPanel");
		rightMiddlePanel.add(sensorDisplayPanel);

		commandListPanel.setWidth("700px");
		commandListPanel.setHeight("200px");
		commandListPanel.setStyleName("commandListPanel");
		rightMiddlePanel.add(commandListPanel);
		upperMiddlePanel.add(rightMiddlePanel);
		
		wrapperPanel.add(upperMiddlePanel);

		RootPanel.get("wrapperDiv").add(wrapperPanel);
		return;
	}

}


