package edu.jcu.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.protocol.Protocol;
import edu.jcu.sali.index.client.sensor.Sensor;

/**
 * Base class for the module. It initializes the contents for the
 * application.
 * 
 * @author Marc Hammerton
 *
 */
public class Index implements EntryPoint {
	
	private VerticalPanel wrapperPanel;
	
	private Sensor sensor;
	private Protocol protocol;
	
	/**
	 * Initialize the components.
	 */
	public void onModuleLoad() {
		wrapperPanel = new VerticalPanel();
		wrapperPanel.setStyleName("wrapperPanel");

		// Header panel
		VerticalPanel headerPanel = new VerticalPanel();
		headerPanel.setStyleName("headerPanel");
		headerPanel.add(new HTML("<h1>SAL-I</h1><h2>Sensor Networks Interface</h2>"));

		FlowPanel linkPanel = new FlowPanel ();
		linkPanel.addStyleName("linkPanel");
		
		// Sensor Link
		Hyperlink hl_sensor = new Hyperlink("Sensors","sensorsToken");
		hl_sensor.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				sensor = new Sensor();
				setMainPanel(sensor);				
			}
			
		});
		linkPanel.add(hl_sensor);
		
		// Protocol Link
		Hyperlink hl_protocol = new Hyperlink("Protocols","protocolToken");
		hl_protocol.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				protocol = new Protocol();
				setMainPanel(protocol);				
			}
			
		});	
		linkPanel.add(hl_protocol);
		
		// About Link
		Hyperlink hl_about = new Hyperlink("About","aboutToken");
		hl_about.setStyleName("linkRight");
		linkPanel.add(hl_about);

		headerPanel.add(linkPanel);
		wrapperPanel.add(headerPanel);
		
		sensor = new Sensor();
		wrapperPanel.add(sensor);

		RootPanel.get("wrapperDiv").add(wrapperPanel);
	}
	
	/**
	 * Sets the contents of the main panel.
	 * @param newPanel
	 */
	public void setMainPanel(Panel newPanel) {
		wrapperPanel.remove(wrapperPanel.getWidgetCount()-1);
		wrapperPanel.add(newPanel);
	}
}


