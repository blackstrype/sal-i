package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Index implements EntryPoint {
	public ListBox sensorLB;
	private static final int SENSOR_LB_VISIBLE_ITEMS = 10;

	public void onModuleLoad() {
		RootPanel.get("headerDiv").add(new HTML("SAL-I - Sensor Networks Interface"));
		
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
				RootPanel.get("sensorDetailsDiv").clear();
				RootPanel.get("sensorDetailsDiv").add(new HTML("details for sensor #" + selectedIndex));
			}
        };
		sensorLB.addClickListener(listener);
		
		RootPanel.get("deviceListDiv").add(new HTML("List of connected sensors."));
		RootPanel.get("deviceListDiv").add(sensorLB);
	}
	
	// Populate the list of sensors with the retrieved data from SAL
	public void PopulateSensorList()
	{
		// This is temporary code
		for(int i = 0; i < 5; i++)
			sensorLB.addItem("sensor" + i);
		// TODO: Implement AJAX to retrieve list of sensors from SAL
	}

}
