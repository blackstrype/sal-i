package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.sensor.Sensor;



public class Index implements EntryPoint {
	
	private VerticalPanel wrapperPanel;
	
	
	public void onModuleLoad() {
		
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

		Sensor sensor = new Sensor();
		wrapperPanel.add(sensor);

		RootPanel.get("wrapperDiv").add(wrapperPanel);
		return;
	}

}


