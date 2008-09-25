package edu.jcu.sali.index.client;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;

public class DeviceOutputPanel extends DockPanel {
	
	private TextArea outputArea;
	
	public DeviceOutputPanel() {
		outputArea = new TextArea();
		
		outputArea.setCharacterWidth(47);
		outputArea.setVisibleLines(12);
		this.add(outputArea, DockPanel.CENTER);
		this.add(new HTML("Device Output:"), DockPanel.NORTH);
	}
	
	public void displayOutput(String text) {
		outputArea.setText(outputArea.getText() + text);
	}
}