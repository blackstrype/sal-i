package edu.jcu.sali.index.client.commandlist;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
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
		final Button button = new Button("update");
		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				CommandListServiceAsync instance = CommandListService.Util
						.getInstance();
				instance.getCommandList(new AsyncCallback() {

					public void onFailure(Throwable error) {
						Window.alert("Error occured:" + error.toString());
					}

					public void onSuccess(Object retValue) {
						String[] commandList = (String[]) retValue;
						updateCommandListPanel(commandList);
					}
				});
			}
		});
		this.add(clTree, DockPanel.CENTER);
		this.add(button, DockPanel.WEST);

	}

	public void updateCommandListPanel(String[] commands) {
		clTree.clear();
		for (String command : commands) {
			TreeItem item = new TreeItem(command);
			clTree.addItem(item);
		}
	}
}
