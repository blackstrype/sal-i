package edu.jcu.sali.index.client;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

public class SensorListBox extends Composite implements TableListener, ClickListener {

	private static final int VISIBLE_DEVICES_COUNT = 10;
	
	private FlexTable sensorList = new FlexTable();
	
	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		// TODO Auto-generated method stub
		
		// populate command list for selected item (row)
		// populate sensor details (if any)
	}

	public void onClick(Widget sender) {
		// TODO Auto-generated method stub
		
	}
	
	// constructor
	public SensorListBox() {
		// Setup the list
		sensorList.setCellSpacing(0);
		sensorList.setCellPadding(1);
		sensorList.setWidth("100%");
		
		// Hook up events.
		sensorList.addTableListener(this);
	    
		initWidget(sensorList);
		setStyleName("sensor-List");
		
		initList();
		update();
	}
	
	// Initialize the flex table for the sensor list
	private void initList()
	{
		// Create the header row.
		sensorList.setText(0, 0, "#");
		sensorList.setText(0, 1, "Device Name");
		
		sensorList.getRowFormatter().setStyleName(0, "sensor-List-header");

	    // Initialize the rest of the rows.
	    for (int i = 0; i < VISIBLE_DEVICES_COUNT; ++i) {
	    	sensorList.setText(i + 1, 0, "");
	    	sensorList.setText(i + 1, 1, "");
	    	sensorList.getCellFormatter().setWordWrap(i + 1, 0, false);
	    	sensorList.getCellFormatter().setWordWrap(i + 1, 1, false);
	    }
	}
	
	// Update
	public void update()
	{
		
	}
	
}
