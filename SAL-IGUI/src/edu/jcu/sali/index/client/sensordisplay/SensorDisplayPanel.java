package edu.jcu.sali.index.client.sensordisplay;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SensorDisplayPanel extends DockPanel {

	// private VerticalPanel dataPanel;
	private TabPanel tabPanel;

	public SensorDisplayPanel() {
		this.add(new HTML("<h1>Sensor Display</h1>"), DockPanel.NORTH);
		// Create a tab panel
		tabPanel = new TabPanel();
		tabPanel.setVisible(false);
		this.add(tabPanel, DockPanel.CENTER);
		// dataPanel = new VerticalPanel();
		// this.add(dataPanel, DockPanel.CENTER);
		// InsertVideo();
		// InsertImage();
	}

	// Insert an HTML widget for displaying a video file.
	// TODO: Streaming video
	// TODO: Access SAL server for streaming video
	public void InsertVideo() {
		String videoText = "";
		videoText += "<object CLASSID='clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B' width='700' height='700' CODEBASE='http://www.apple.com/qtactivex/qtplugin.cab'>\n"
				+ "<param name='src' value='media/sample_iTunes.mov'>\n"
				+ "<param name='qtsrc' value='media/sample_iTunes.mov'>\n"
				+ "<param name='autoplay' value='false'>\n"
				+ "<param name='loop' value='false'>\n"
				+ "<param name='controller' value='true'>\n"
				+ "<embed src='media/sample_iTunes.mov' qtsrc='media/sample_iTunes.mov'></embed>\n"
				+ "</object>\n";

		add(new HTML(videoText), DockPanel.CENTER);
	}

	// Insert an HTML widget for displaying a JPEG image
	public void InsertImage() {
		String imageText = "";
		imageText += "<img src='images/sample.jpg'>\n";

		add(new HTML(imageText), DockPanel.SOUTH);
	}

	public void displaySensorTabPanel(ArrayList<String> sensor) {
		tabPanel.setVisible(true);
		tabPanel.clear();
		// Set the width to 400 pixels
		tabPanel.setWidth("700px");
		tabPanel.setHeight("400px");

		// Add a details tab
		HTML sensorDetails = this.getSensorDetails(sensor);
		tabPanel.add(sensorDetails, "Details");

		// Add a data tab
		HTML sensorData = new HTML(
				"<h2>Here comes the data from the sensor</h2>");
		tabPanel.add(sensorData, "Data");

		// Make the first tab selected and the tab's content visible
		tabPanel.selectTab(0);

	}

	public HTML getSensorDetails(ArrayList<String> sensor) {

		// dataPanel.clear();
		//		
		// Label sidLabel = new Label("SID");
		// dataPanel.add(sidLabel);
		//
		// Label sidText = new Label(sensor.get(0));
		// dataPanel.add(sidText);
		//		
		// Label typeLabel = new Label("Type");
		// dataPanel.add(typeLabel);
		//		
		// Label typeText = new Label(sensor.get(1));
		// dataPanel.add(typeText);
		//		
		// Label addressLabel = new Label("Address");
		// dataPanel.add(addressLabel);
		//		
		// Label addressText = new Label(sensor.get(2));
		// dataPanel.add(addressText);
		//		
		// Label nameLabel = new Label("Name");
		// dataPanel.add(nameLabel);
		//		
		// Label nameText = new Label(sensor.get(3));
		// dataPanel.add(nameText);

		HTML detailsText = new HTML(
				"<div class=\"sensorDetails\"><h3>SID</h3><p>" + sensor.get(0)
						+ "</p>" + "<h3>Type</h3><p>" + sensor.get(1) + "</p>"
						+ "<h3>Address</h3><p>" + sensor.get(2) + "</p>"
						+ "<h3>Name</h3><p>" + sensor.get(3) + "</p></div>");
		return detailsText;
	}

}
