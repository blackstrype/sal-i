package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Index implements EntryPoint {
	public ListBox sensorLB;
	private static final int SENSOR_LB_VISIBLE_ITEMS = 10;
	private VerticalPanel wrapperPanel;
	private DockPanel sensorDisplayPanel;
	private VerticalPanel sensorListPanel;

	public void onModuleLoad() {
		
		BuildBaseUI();
		InitializeSensorList();
		PopulateSensorList();
	}
	
	// Initialize the Widgets and such for displaying and configuring the list of sensors
	private void InitializeSensorList()
	{
		sensorLB = new ListBox();
		sensorLB.setVisibleItemCount(SENSOR_LB_VISIBLE_ITEMS);
		
		ClickListener listener = new ClickListener()
        {
			public void onClick(Widget sender) {
				int selectedIndex = ((ListBox) sender).getSelectedIndex();
				//clear the device display panel
				sensorDisplayPanel.clear();
				//add the details for the selected device
				sensorDisplayPanel.add(new HTML("details for sensor #" + selectedIndex), DockPanel.CENTER);
			}
        };
		sensorLB.addClickListener(listener);
		
		sensorListPanel.add(new HTML("List of connected sensors."));
		sensorListPanel.add(sensorLB);
	}
	
	// Populate the list of sensors with the retrieved data from SAL
	public void PopulateSensorList()
	{
		// This is temporary code
		for(int i = 0; i < 5; i++)
			sensorLB.addItem("sensor" + i);
		// TODO: Implement AJAX to retrieve list of sensors from SAL
	}
	
	// Establish the base layout for display using GWT's panels
	private void BuildBaseUI()
	{
		wrapperPanel = new VerticalPanel();
		wrapperPanel.setStyleName("wrapperPanel");
		
		// Title of header panel
		VerticalPanel headerPanel = new VerticalPanel();
		headerPanel.add(new HTML("SAL-I - Sensor Networks Interface"));
		wrapperPanel.add(headerPanel);
		
		String panelStyleString = "genericPanel";
		
		// Upper middle panel
		HorizontalPanel upperMiddlePanel = new HorizontalPanel();
		// Sensor list
		sensorListPanel = new VerticalPanel();
		sensorListPanel.setWidth("250px");
		sensorListPanel.setHeight("600px");
		sensorListPanel.setStyleName(panelStyleString);
		upperMiddlePanel.add(sensorListPanel);
		// Sensor display
		sensorDisplayPanel = new DockPanel();
		sensorDisplayPanel.setWidth("600px");
		sensorDisplayPanel.setHeight("600px");
		sensorDisplayPanel.setStyleName(panelStyleString);
		sensorDisplayPanel.add(new HTML("sensorDisplay"), DockPanel.CENTER);
		upperMiddlePanel.add(sensorDisplayPanel);
		wrapperPanel.add(upperMiddlePanel);
		
		// Lower middle panel
		HorizontalPanel lowerMiddlePanel = new HorizontalPanel();
		// Command list
		DockPanel commandListPanel = new DockPanel();
		commandListPanel.setHeight("250px");
		commandListPanel.setWidth("425px");
		commandListPanel.setStyleName(panelStyleString);
		commandListPanel.add(new HTML("commandList"), DockPanel.CENTER);
		lowerMiddlePanel.add(commandListPanel);
		// Device output
		DockPanel deviceOutputPanel = new DockPanel();
		deviceOutputPanel.setHeight("250px");
		deviceOutputPanel.setWidth("425px");
		deviceOutputPanel.setStyleName(panelStyleString);
		deviceOutputPanel.add(new HTML("deviceOutput"), DockPanel.CENTER);
		lowerMiddlePanel.add(deviceOutputPanel);
		wrapperPanel.add(lowerMiddlePanel);
		
		// Footer
		wrapperPanel.add(new HTML("FooterHTML"));
		
		RootPanel.get("wrapperDiv").add(wrapperPanel);
		return;
	}

}
