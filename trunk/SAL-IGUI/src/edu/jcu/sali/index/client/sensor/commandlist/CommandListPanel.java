package edu.jcu.sali.index.client.sensor.commandlist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeImages;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.sensor.commandlist.utils.CommandListTreeImages;
import edu.jcu.sali.index.client.sensor.sensordisplay.SensorDisplayPanel;
import edu.jcu.sali.index.client.utilities.Utilities;

/**
 * Responsible for initializing and updating the CommandList-Panel
 * 
 * @author Marc
 * 
 */
public class CommandListPanel extends DockPanel {
	private String sid;
	private SensorDisplayPanel sensorDisplayPanel;
	private DeckPanel loaderPanel;
	private Tree clTree;
	private String[] argNames;

	public CommandListPanel(SensorDisplayPanel sensorDisplayPanel) {
		this.sid = "0";
		this.sensorDisplayPanel = sensorDisplayPanel;
		this.loaderPanel = Utilities.getLoaderPanel();

		TreeImages images = (TreeImages) GWT
				.create(CommandListTreeImages.class);
		clTree = new Tree(images);

		clTree.addTreeListener(new TreeListener() {

			public void onTreeItemSelected(TreeItem item) {

				item.getTree().setSelectedItem(item, false);
				if (item.getState() == false) {
					item.setState(true);
				} else {
					item.setState(false);
				}

			}

			public void onTreeItemStateChanged(TreeItem item) {
				// TODO Auto-generated method stub

			}

		});

		loaderPanel.add(clTree);
		this.add(loaderPanel, DockPanel.CENTER);
		this.add(new HTML("<h2>Command List</h2>"), DockPanel.NORTH);
	}

	public void updateCommandListPanel(String currentSid,
			ArrayList<ArrayList<String>> commands) {
		this.sid = currentSid;
		clTree.clear();
		for (ArrayList<String> command : commands) {
			TreeItem item = new TreeItem(command.get(0) + " - "
					+ command.get(1));
			item.addStyleName("commandListItem");

			item.addItem(command.get(2));

			if (command.get(4).length() > 0) {
				argNames = command.get(4).split("##");
				for(String argName : argNames) {
					Label argLabel = new Label(argName);
					argLabel.addStyleName("argLabel");
					TextBox argInput = new TextBox();
					DOM.setElementAttribute(argInput.getElement(), "id", argName);
					argInput.addStyleName("argInput");
					HorizontalPanel argPanel = new HorizontalPanel();
					argPanel.add(argLabel);
					argPanel.add(argInput);
					item.addItem(argPanel);
				}
			}

			Button bSendCommand = new Button("Send Command",
					new ClickListener() {
						public void onClick(Widget sender) {
							sensorDisplayPanel.showLoaderWidget();
							String cid = clTree.getSelectedItem()
									.getParentItem().getText().split(" - ")[0];
							
							String args = "";
							if(argNames != null) {
								for(String arg : argNames) {
									com.google.gwt.user.client.Element el = DOM.getElementById(arg);
									String value = el.getAttribute("value");
									args += arg + "##" + value + "####";
								}
							} 

							CommandListServiceAsync instance = CommandListService.Util
									.getInstance();
							instance.sendCommand(sid,args, Integer.parseInt(cid),
									new AsyncCallback() {

										public void onFailure(Throwable error) {
											Window.alert("Error occured:"
													+ error.toString());
										}

										public void onSuccess(Object retValue) {
											String data = (String) retValue;
											sensorDisplayPanel.updateData(data);
										}
									});

						}
					});
			item.addItem(bSendCommand);

			clTree.addItem(item);
		}
	}

	public void setFailureText() {
		clTree.addItem("No commandlist available");
	}

	public void toggleLoaderPanel() {
		if (loaderPanel.getVisibleWidget() == 0
				&& loaderPanel.getWidgetCount() > 0) {
			loaderPanel.showWidget(1);
		} else {
			loaderPanel.showWidget(0);
		}
	}

}
