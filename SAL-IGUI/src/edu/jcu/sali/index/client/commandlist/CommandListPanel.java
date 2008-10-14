package edu.jcu.sali.index.client.commandlist;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeImages;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;

import edu.jcu.sali.index.client.commandlist.utils.CommandListTreeImages;

/**
 * Responsible for initializing and updating the CommandList-Panel
 * 
 * @author Marc
 * 
 */
public class CommandListPanel extends DockPanel {
	private Tree clTree;
	private ArrayList<ArrayList<String>> commands;

	public CommandListPanel() {
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


	public void updateCommandListPanel(ArrayList<ArrayList<String>> commands) {
		this.commands = commands;
		clTree.clear();
		for (ArrayList<String> command : commands) {
			TreeItem item = new TreeItem(command.get(0) + " - " + command.get(1));
			item.addStyleName("commandListItem");

			item.addItem(command.get(2));
			clTree.addItem(item);
		}
	}
	
/*	public void showCommandDetails(TreeItem item) {
		String[] itemTextArr = item.getText().split(" - "); 
		String selCommand = itemTextArr[0];
		
		for(int i=0; i<commands.size(); i++) {
			if(selCommand.equals(commands.get(i).get(0))) {
				TreeItem subItem = new TreeItem();
				subItem.addItem(commands.get(i).get(2));
				
				item.addItem(subItem);
			}
		}
	}*/
}
