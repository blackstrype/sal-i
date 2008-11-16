package edu.jcu.sali.index.client.sensor.addsensorpopup;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.sensor.SensorService;
import edu.jcu.sali.index.client.sensor.SensorServiceAsync;

/**
 * A pop-up for adding a sensor.
 * 
 * @author Marc Hammerton
 * 
 */
public class AddSensorPopup extends DialogBox {
	
	private TextArea textarea;

	/**
	 * Initializes the pop-up.
	 */
	public AddSensorPopup() {
		setText("Add new Sensor");
		setWidget(getInputFormPanel());
	}

	/**
	 * Creates the input-form to get all the information for the new sensor.
	 * It contains of text-area for the XML for the new sensor and two buttons
	 * to add the sensor or to cancel the action.
	 * 
	 * @return inputFormPanel
	 */
	
	public VerticalPanel getInputFormPanel() {
		VerticalPanel inputFormPanel = new VerticalPanel();
		inputFormPanel.setStyleName("dialogInputForm");

		// XML-structure
		Label la_textarea = new Label(
				"Please enter the new sensor in XML format");
		inputFormPanel.add(la_textarea);

		textarea = new TextArea();
		textarea.setText("");
		textarea.setVisibleLines(15);
		textarea.setCharacterWidth(80);
		inputFormPanel.add(textarea);

		// Buttons
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		Button b_add = new Button("Add");
		b_add.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				SensorServiceAsync instance = SensorService.Util
						.getInstance();
				instance.addSensor(textarea.getText(), new AsyncCallback() {

					public void onFailure(Throwable error) {
						AddSensorPopup.this.hide();
					}

					public void onSuccess(Object retValue) {
						AddSensorPopup.this.hide();
					}
				});
			}
		});
		buttonPanel.add(b_add);

		Button b_cancel = new Button("Cancel");
		b_cancel.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				AddSensorPopup.this.hide();
			}
		});
		buttonPanel.add(b_cancel);
		inputFormPanel.add(buttonPanel);
		this.add(inputFormPanel);

		return inputFormPanel;
	}
}
