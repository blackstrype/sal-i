package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Button;
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

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.commandlist.CommandListService;
import edu.jcu.sali.index.client.commandlist.CommandListServiceAsync;

public class Index implements EntryPoint {
	public ListBox sensorLB;
	public SensorListBox sensorList;
	private static final int SENSOR_LB_VISIBLE_ITEMS = 10;
	private VerticalPanel wrapperPanel;
	private DockPanel sensorDisplayPanel;
	private VerticalPanel sensorListPanel;
	private CommandListPanel commandListPanel;
	private DeviceOutputPanel deviceOutputPanel;

	public void onModuleLoad() {

		BuildBaseUI();
		InitializeSensorList();
	}

	// Initialize the Widgets and such for displaying and configuring the list
	// of sensors
	private void InitializeSensorList() {
		sensorList = new SensorListBox();
		sensorListPanel.add(sensorList);
	}

	// Populate the list of sensors with the retrieved data from SAL
	public void PopulateSensorList() {
		// This is temporary code
		for (int i = 0; i < 5; i++)
			sensorLB.addItem("sensor" + i);
		// TODO: Implement AJAX to retrieve list of sensors from SAL
	}

	// Establish the base layout for display using GWT's panels
	private void BuildBaseUI() {
		wrapperPanel = new VerticalPanel();
		wrapperPanel.setStyleName("wrapperPanel");

		// Title of header panel
		VerticalPanel headerPanel = new VerticalPanel();
		headerPanel.setStyleName("headerPanel");
		headerPanel.add(new HTML("<h1>SAL-I</h1><h2>Sensor Networks Interface</h2>"));
		wrapperPanel.add(headerPanel);

		String panelStyleString = "genericPanel";

		// Upper middle panel
		HorizontalPanel upperMiddlePanel = new HorizontalPanel();
		upperMiddlePanel.setStyleName("upperMiddlePanel");
		// Sensor list
		sensorListPanel = new VerticalPanel();
		sensorListPanel.setWidth("250px");
		sensorListPanel.setHeight("600px");
		sensorListPanel.setStyleName("sensorListPanel");
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
		commandListPanel = new CommandListPanel();
		commandListPanel.setHeight("250px");
		commandListPanel.setWidth("425px");
		commandListPanel.setStyleName(panelStyleString);
		commandListPanel.initCommandListPanel();
		lowerMiddlePanel.add(commandListPanel);
		// Device output
		deviceOutputPanel = new DeviceOutputPanel();
		deviceOutputPanel.setHeight("250px");
		deviceOutputPanel.setWidth("425px");
		deviceOutputPanel.setStyleName(panelStyleString);
		lowerMiddlePanel.add(deviceOutputPanel);
		wrapperPanel.add(lowerMiddlePanel);

		// Footer
		wrapperPanel.add(new HTML("FooterHTML"));

		RootPanel.get("wrapperDiv").add(wrapperPanel);
		return;
	}
}
