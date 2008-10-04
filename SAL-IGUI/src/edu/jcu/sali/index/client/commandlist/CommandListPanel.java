package edu.jcu.sali.index.client.commandlist;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.DeviceOutputPanel;

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
	}

	public void initCommandListPanel() {
		this.add(clTree, DockPanel.CENTER);
		this.add(new HTML("<h2>Command List</h2>"),DockPanel.NORTH);
	}

	public void updateCommandListPanel(String[] commands) {
		clTree.clear();
		for (String command : commands) {
			TreeItem item = new TreeItem(command);
			clTree.addItem(item);
		}
	}
}
