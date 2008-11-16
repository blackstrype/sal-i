package edu.jcu.sali.index.client.sensor.sensorlist;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.sensor.addsensorpopup.AddSensorPopup;
import edu.jcu.sali.index.client.sensor.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensor.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.utilities.Utilities;

/**
 * Panel to hold the sensor-list.
 * 
 * @author Marc Hammerton
 * 
 */
public class SensorListPanel extends DockPanel {

	private AddSensorPopup addSensorPopup;
	private SensorListBox sensorList;
	private DeckPanel sensorLoaderPanel;

	/**
	 * Initialize the panel. Add a headline and create the list-box. Add a link
	 * to add a sensor.
	 * 
	 * @param commandListPanel
	 *            Reference to the command-list-panel.
	 * @param sensorDisplayPanel
	 *            Reference to the sensor-display-panel.
	 */
	public SensorListPanel(CommandListPanel commandListPanel,
			SensorDisplayPanel sensorDisplayPanel) {

		// Headline
		this.add(new HTML("<h2>Sensor List</h2>"), DockPanel.NORTH);

		this.sensorLoaderPanel = Utilities.getLoaderPanel();

		// Sensor-list-box
		sensorList = new SensorListBox(this, commandListPanel,
				sensorDisplayPanel);

		sensorLoaderPanel.add(sensorList);
		this.add(sensorLoaderPanel, DockPanel.CENTER);

		// Add sensor
		Hyperlink hl_addSensor = new Hyperlink("Add Sensor", "addSensorToken");
		hl_addSensor.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				addSensorPopup = new AddSensorPopup();
				addSensorPopup
						.setPopupPositionAndShow(new DialogBox.PositionCallback() {
							public void setPosition(int offsetWidth,
									int offsetHeight) {
								int left = (Window.getClientWidth() - offsetWidth) / 3;
								int top = (Window.getClientHeight() - offsetHeight) / 5;
								addSensorPopup.setPopupPosition(left, top);
							}
						});

			}

		});
		this.add(hl_addSensor, DockPanel.SOUTH);
	}

	/**
	 * Show and hide loader-panel while getting data from the server.
	 */
	public void toggleLoaderPanel() {
		if (sensorLoaderPanel.getVisibleWidget() == 0
				&& sensorLoaderPanel.getWidgetCount() > 0) {
			sensorLoaderPanel.showWidget(1);
		} else {
			sensorLoaderPanel.showWidget(0);
		}
	}
}
