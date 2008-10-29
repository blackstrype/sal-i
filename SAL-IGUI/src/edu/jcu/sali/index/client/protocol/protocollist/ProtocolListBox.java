package edu.jcu.sali.index.client.protocol.protocollist;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.sensor.sensordisplay.SensorDisplayPanel;

public class ProtocolListBox extends Composite implements TableListener,
		ClickListener {

	private FlexTable protocolListTable = new FlexTable();
	int selectedRow;

//	private ArrayList<ArrayList<String>> protocolList;
	private String protocolList;
	private ProtocolListPanel protocolListPanel;
	private SensorDisplayPanel sensorDisplayPanel;
	private String pid;

	private Timer updateTimer;

	// constructor
	public ProtocolListBox(ProtocolListPanel sensorListPanel, 
			SensorDisplayPanel sensorDisplayPanel) {
		this.protocolListPanel = sensorListPanel;
		this.sensorDisplayPanel = sensorDisplayPanel;

		this.pid = "";

		// Setup the list
		protocolListTable.setCellSpacing(0);
		protocolListTable.setCellPadding(1);

		// Hook up events.
		protocolListTable.addTableListener(this);

		initWidget(protocolListTable);
		setStyleName("sensor-List");

		initList();

		updateTimer = new Timer() {
			public void run() {
				update();
			}
		};
		updateTimer.scheduleRepeating(10000);
	}

	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {

		if (row > 0) {
			styleRow(selectedRow, false); // unselect the previously selected
			// row
			styleRow(row, true); // set the clicked row to 'selected'
			selectedRow = row;
			pid = protocolListTable.getText(row, 1);

//			sensorDisplayPanel.displaySensorTabPanel(protocolList);

		}
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			if (selected) {
				protocolListTable.getRowFormatter().addStyleName(row,
						"sensorList-selectedRow");
			} else {
				protocolListTable.getRowFormatter().removeStyleName(row,
						"sensorList-selectedRow");
			}
		}
	}

	// Initialize the flex table for the sensor list
	private void initList() {
		// Create the header row.
		protocolListTable.setText(0, 0, "#");
		protocolListTable.setText(0, 1, "PID");
		protocolListTable.setText(0, 2, "Address");

		protocolListTable.getRowFormatter().setStyleName(0, "sensor-List-header");
		update();
	}

	public void update() {
		protocolListPanel.toggleLoaderPanel();
		ProtocolListServiceAsync instance = ProtocolListService.Util.getInstance();
		instance.getProtocolList(new AsyncCallback() {

			public void onFailure(Throwable error) {
				Window.alert("Error occured:" + error.toString());
			}

			public void onSuccess(Object retValue) {
				if (protocolList != null) {
					protocolList = "";
				}
				protocolList = (String) retValue;
				setProtocolList(protocolList);
			}
		});
	}

	// Set sensor-list
	public void setProtocolList(String protocolList) {
		protocolListPanel.toggleLoaderPanel();
		for (int i = protocolListTable.getRowCount() - 1; i >= 1; i--) {
			protocolListTable.removeRow(i);
		}

//		// Initialize the rest of the rows.
//		for (int i = 0; i < sensorList.size(); ++i) {
//			ArrayList<String> sensor = sensorList.get(i);
//			protocolListTable.setText(i + 1, 0, (i + 1) + ")");
//			protocolListTable.setText(i + 1, 1, sensor.get(0));
//			protocolListTable.setText(i + 1, 2, sensor.get(2));
//			protocolListTable.getCellFormatter().setWordWrap(i + 1, 0, false);
//			protocolListTable.getCellFormatter().setWordWrap(i + 1, 1, false);
//			protocolListTable.getCellFormatter().setWordWrap(i + 1, 2, false);
//		}
		
		protocolListTable.setText(1, 0, "1)");
		protocolListTable.setText(1, 1, protocolList);

	}

	public void onClick(Widget sender) {
		// TODO Auto-generated method stub
		
	}
}
