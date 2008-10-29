/**
 * 
 */
package edu.jcu.sali.index.client.sensor.sensorlist;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.sensor.addsensorpopup.AddSensorPopup;
import edu.jcu.sali.index.client.sensor.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.sensor.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.utilities.Utilities;

/**
 * @author Marc
 * 
 */
public class SensorListPanel extends VerticalPanel {

	private AddSensorPopup addSensorPopup;
	private SensorListBox sensorList;
	private DeckPanel sensorLoaderPanel;

	public SensorListPanel(CommandListPanel commandListPanel,
			SensorDisplayPanel sensorDisplayPanel) {
		
		Hyperlink hl_addSensor = new Hyperlink("Add Sensor","addSensorToken");
		hl_addSensor.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {		
				addSensorPopup = new AddSensorPopup();
				addSensorPopup.setPopupPositionAndShow(new DialogBox.PositionCallback() {
			          public void setPosition(int offsetWidth, int offsetHeight) {
			              int left = (Window.getClientWidth() - offsetWidth) / 3;
			              int top = (Window.getClientHeight() - offsetHeight) / 5;
			              addSensorPopup.setPopupPosition(left, top);
			            }
			          });

			}
			
		});
		this.add(hl_addSensor);
		
		this.sensorLoaderPanel = Utilities.getLoaderPanel();
		sensorList = new SensorListBox(this, commandListPanel,
				sensorDisplayPanel);
		
		sensorLoaderPanel.add(sensorList);
		this.add(sensorLoaderPanel);
	}

	public void toggleLoaderPanel() {
		if (sensorLoaderPanel.getVisibleWidget() == 0
				&& sensorLoaderPanel.getWidgetCount() > 0) {
			sensorLoaderPanel.showWidget(1);
		} else {
			sensorLoaderPanel.showWidget(0);
		}
	}
}
