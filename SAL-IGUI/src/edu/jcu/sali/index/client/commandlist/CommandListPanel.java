package edu.jcu.sali.index.client.commandlist;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * Responsible for initializing and updating the CommandList-Panel
 * 
 * @author Marc
 * 
 */
public class CommandListPanel extends DockPanel {
	private Tree clTree;

	public CommandListPanel() {
		clTree = new Tree();
		this.add(clTree, DockPanel.CENTER);
		this.add(new HTML("<h2>Command List</h2>"),DockPanel.NORTH);		
	}


	public void updateCommandListPanel(ArrayList<ArrayList<String>> commands) {
		clTree.clear();
		for (ArrayList<String> command : commands) {
			TreeItem item = new TreeItem(command.get(0));
			clTree.addItem(item);
		}
	}
}
