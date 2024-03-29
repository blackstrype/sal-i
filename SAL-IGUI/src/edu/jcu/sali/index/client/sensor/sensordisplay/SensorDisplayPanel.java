package edu.jcu.sali.index.client.sensor.sensordisplay;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.utilities.Utilities;

/**
 * This panel is used to display sensor-specific data.
 * 
 * @author Marc Hammerton, Scott Messner, Xu Du
 * 
 */
public class SensorDisplayPanel extends DockPanel {

	private TabPanel tabPanel;
	private Widget loaderWidget;

	/**
	 * Initialize the panel. Create a tab-panel and add a headline.
	 */
	public SensorDisplayPanel() {
		this.add(new HTML("<h1>Sensor Display</h1>"), DockPanel.NORTH);
		this.loaderWidget = Utilities.getLoaderWidget();

		// Create a tab panel
		tabPanel = new TabPanel();
		tabPanel.setVisible(false);
		this.add(tabPanel, DockPanel.CENTER);
		// InsertVideo();
		// InsertImage();
	}

	/**
	 * Insert an HTML widget for displaying a video file.
	 */
	// TODO: Streaming video
	// TODO: Access SAL server for streaming video
	public void insertVideo() {
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

	/**
	 * Insert an HTML widget for displaying a JPEG image
	 * 
	 * @param src
	 *            Source of the image
	 */
	public void insertImage(String src) {
		String imageText = "";
		imageText += "<img src='" + src + "'>\n";

		add(new HTML(imageText), DockPanel.SOUTH);
	}

	/**
	 * Show the sensor-tab-panel. It creates two tabs: details and data. At
	 * first the sensor-details are displayed.
	 * 
	 * @param sensor
	 *            Sensor-details
	 */
	public void displaySensorTabPanel(ArrayList<String> sensor) {
		tabPanel.setVisible(true);
		tabPanel.clear();

		// Set the width
		tabPanel.setWidth("700px");
		tabPanel.setHeight("300px");

		// Add a details tab
		HTML sensorDetails = this.getSensorDetails(sensor);
		tabPanel.add(sensorDetails, "Details");

		// Add a data tab
		HTML sensorData = new HTML("<p>No command sent until now.</p>");
		tabPanel.add(sensorData, "Data");

		// Make the first tab selected and the tab's content visible
		tabPanel.selectTab(0);

	}

	/**
	 * Return the sensor-details as HTML.
	 * 
	 * @param sensor
	 *            Sensor-details
	 * @return HTML
	 */
	public HTML getSensorDetails(ArrayList<String> sensor) {

		HTML detailsText = new HTML(
				"<div class=\"sensorDetails\"><h3>SID</h3><p>" + sensor.get(0)
						+ "</p>" + "<h3>Type</h3><p>" + sensor.get(1) + "</p>"
						+ "<h3>Address</h3><p>" + sensor.get(2) + "</p>"
						+ "<h3>Name</h3><p>" + sensor.get(3) + "</p></div>");
		return detailsText;
	}

	/**
	 * Updates the data-tab.
	 * 
	 * @param data
	 *            The new data of the sensor
	 */
	public void updateData(String data) {

		if (data.equals("")) {
			data = "No data available.";
		} else if (data.indexOf("data:images/jpeg") != -1) {
			insertImage(data);
		}

		tabPanel.remove(1);
		tabPanel.add(new HTML("<p>" + data + "</p>"), "Data");
		tabPanel.selectTab(1);
	}

	/**
	 * Show the loader widget while getting data from the server.
	 */
	public void showLoaderWidget() {
		tabPanel.remove(1);
		tabPanel.add(loaderWidget, "Data");
		tabPanel.selectTab(1);
	}

}
