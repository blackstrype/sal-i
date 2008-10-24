package edu.jcu.sali.index.client.commandlist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeImages;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.commandlist.utils.CommandListTreeImages;
import edu.jcu.sali.index.client.sensordisplay.SensorDisplayPanel;

/**
 * Responsible for initializing and updating the CommandList-Panel
 * 
 * @author Marc
 * 
 */
public class CommandListPanel extends DockPanel {
	private String sid;
	private SensorDisplayPanel sensorDisplayPanel;
	private Tree clTree;

	public CommandListPanel(SensorDisplayPanel sensorDisplayPanel) {
		this.sid = "0";
		this.sensorDisplayPanel = sensorDisplayPanel;
		
		TreeImages images = (TreeImages)GWT.create(CommandListTreeImages.class);
		clTree = new Tree(images);
		
		clTree.addTreeListener(new TreeListener() {

			public void onTreeItemSelected(TreeItem item) {
				
				item.getTree().setSelectedItem(item, false);
				if(item.getState() == false) {
					item.setState(true);
				}
				else {
					item.setState(false);
				}
				
			}

			public void onTreeItemStateChanged(TreeItem item) {
				// TODO Auto-generated method stub
				
			}
            
        });

		this.add(clTree, DockPanel.CENTER);
		this.add(new HTML("<h2>Command List</h2>"),DockPanel.NORTH);
	}


	public void updateCommandListPanel(String currentSid, ArrayList<ArrayList<String>> commands) {
		this.sid = currentSid;
		clTree.clear();
		for (ArrayList<String> command : commands) {
			TreeItem item = new TreeItem(command.get(0) + " - " + command.get(1));
			item.addStyleName("commandListItem");

			item.addItem(command.get(2));
			
			Button bSendCommand = new Button("Send Command", new ClickListener() {
			      public void onClick(Widget sender) {
			        String cid = clTree.getSelectedItem().getParentItem().getText().split(" - ")[0];
			        
					CommandListServiceAsync instance = CommandListService.Util.getInstance();
					instance.sendCommand(sid, Integer.parseInt(cid), new AsyncCallback() {
				
						public void onFailure(Throwable error) {
							Window.alert("Error occured:" + error.toString());
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

}
