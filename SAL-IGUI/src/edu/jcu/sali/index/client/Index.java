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
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

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
		sensorListPanel = new VerticalPanel();
		sensorListPanel.setWidth("200px");
		sensorListPanel.setHeight("430px");
		sensorListPanel.setStyleName("sensorListPanel");
		upperMiddlePanel.add(sensorListPanel);
		// Sensor display
		sensorDisplayPanel = new DockPanel();
		sensorDisplayPanel.setWidth("780px");
		sensorDisplayPanel.setHeight("430px");
		sensorDisplayPanel.setStyleName("sensorDisplayPanel");
		sensorDisplayPanel.add(new HTML("<h1>Sensor Display</h1>"), DockPanel.CENTER);
		upperMiddlePanel.add(sensorDisplayPanel);
		wrapperPanel.add(upperMiddlePanel);

		// Lower middle panel
		HorizontalPanel lowerMiddlePanel = new HorizontalPanel();
		lowerMiddlePanel.setStyleName("lowerMiddlePanel");
		// Command list
		commandListPanel = new CommandListPanel();
		commandListPanel.setHeight("200px");
		commandListPanel.setWidth("495px");
		commandListPanel.setStyleName("commandListPanel");
		commandListPanel.initCommandListPanel();
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

	// Initialize the Widgets and such for displaying and configuring the list
	// of sensors
	private void InitializeSensorList() {
		sensorList = new SensorListBox(commandListPanel);
		sensorListPanel.add(sensorList);
	}

	// Populate the list of sensors with the retrieved data from SAL
	public void PopulateSensorList() {
		// This is temporary code
		for (int i = 0; i < 5; i++)
			sensorLB.addItem("sensor" + i);
		// TODO: Implement AJAX to retrieve list of sensors from SAL
	}


}


