package edu.jcu.sali.index.client.sensorlist;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.commandlist.CommandListPanel;
import edu.jcu.sali.index.client.commandlist.CommandListService;
import edu.jcu.sali.index.client.commandlist.CommandListServiceAsync;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;

public class SensorListBox extends Composite implements TableListener,
		ClickListener {

	private FlexTable sensorListTable = new FlexTable();
	int selectedRow;

	private ArrayList<ArrayList<String>> sensorList;
	private CommandListPanel commandListPanel;
	private SensorDisplayPanel sensorDisplayPanel;
	private String sid;

	private Timer updateTimer;

	// constructor
	public SensorListBox(CommandListPanel commandListPanel,
			SensorDisplayPanel sensorDisplayPanel) {
		this.commandListPanel = commandListPanel;
		this.sensorDisplayPanel = sensorDisplayPanel;

		this.sid = "";

		// Setup the list
		sensorListTable.setCellSpacing(0);
		sensorListTable.setCellPadding(1);

		// Hook up events.
		sensorListTable.addTableListener(this);

		initWidget(sensorListTable);
		setStyleName("sensor-List");

//		initList();
//		updateTimer = new Timer() {
//			public void run() {
//				update();
//			}
//		};
//		updateTimer.scheduleRepeating(3000);
	}

	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		// TODO Auto-generated method stub

		// Fetch Sensor details (commands, sensor display)
		// populate command list for selected item (row)
		// populate sensor details (if any)

		if (row > 0) {
			styleRow(selectedRow, false); // unselect the previously selected
											// row
			styleRow(row, true); // set the clicked row to 'selected'
			selectedRow = row;
			sid = sensorListTable.getText(row, 1);

			// sensorDisplayPanel.displaySensorData(sensorList.get(row-1));
			sensorDisplayPanel.displaySensorTabPanel(sensorList.get(row - 1));

			// TODO: change
			CommandListServiceAsync instance = CommandListService.Util
					.getInstance();
			instance.getCommandList(Integer.parseInt(sid), new AsyncCallback() {

				public void onFailure(Throwable error) {
//					Window.alert("Error occured:" + error.toString());
				}

				public void onSuccess(Object retValue) {
					ArrayList<ArrayList<String>> commandList = (ArrayList<ArrayList<String>>) retValue;
					commandListPanel.updateCommandListPanel(sid, commandList);
				}
			});
		}
	}

	public void onClick(Widget sender) {
		// TODO Auto-generated method stub
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			if (selected) {
				sensorListTable.getRowFormatter().addStyleName(row,
						"sensorList-selectedRow");
			} else {
				sensorListTable.getRowFormatter().removeStyleName(row,
						"sensorList-selectedRow");
			}
		}
	}

	// Initialize the flex table for the sensor list
	private void initList() {
		// Create the header row.
		sensorListTable.setText(0, 0, "#");
		sensorListTable.setText(0, 1, "SID");
		sensorListTable.setText(0, 2, "Address");

		sensorListTable.getRowFormatter().setStyleName(0, "sensor-List-header");
		update();
	}

	public void update() {
		SensorListServiceAsync instance = SensorListService.Util.getInstance();
		instance.getSensorList(new AsyncCallback() {

			public void onFailure(Throwable error) {
				Window.alert("Error occured:" + error.toString());
			}

			public void onSuccess(Object retValue) {
				sensorList = (ArrayList<ArrayList<String>>) retValue;
				setSensorList(sensorList);
			}
		});
	}

	// Set sensor-list
	public void setSensorList(ArrayList<ArrayList<String>> sensorList) {
		sensorListTable.clear();
		
		// Initialize the rest of the rows.
		for (int i = 0; i < sensorList.size(); ++i) {
			ArrayList<String> sensor = sensorList.get(i);
			sensorListTable.setText(i + 1, 0, (i + 1) + ")");
			sensorListTable.setText(i + 1, 1, sensor.get(0));
			sensorListTable.setText(i + 1, 2, sensor.get(2));
			sensorListTable.getCellFormatter().setWordWrap(i + 1, 0, false);
			sensorListTable.getCellFormatter().setWordWrap(i + 1, 1, false);
			sensorListTable.getCellFormatter().setWordWrap(i + 1, 2, false);
		}

	}

}
