package edu.jcu.sali.index.client.protocol.removeprotocolpopup;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.protocol.ProtocolService;
import edu.jcu.sali.index.client.protocol.ProtocolServiceAsync;

/**
 * @author Marc
 * 
 */
public class RemoveProtocolPopup extends DialogBox {
	
	private TextBox pidInput;
	private CheckBox cb_removeAss;

	public RemoveProtocolPopup() {
		// Set the dialog box's caption.
		setText("Remove Protocol");
		setWidget(getInputFormPanel());
	}

	public VerticalPanel getInputFormPanel() {
		VerticalPanel inputFormPanel = new VerticalPanel();
		inputFormPanel.setStyleName("dialogInputForm");

		Label pidLabel = new Label("PID");
		inputFormPanel.add(pidLabel);
		pidInput = new TextBox();
		DOM.setElementAttribute(pidInput.getElement(), "id", "pid");
		inputFormPanel.add(pidInput);

		cb_removeAss = new CheckBox("Remove Associated");
		cb_removeAss.setChecked(true);
		inputFormPanel.add(cb_removeAss);
		
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		Button b_remove = new Button("Remove");
		b_remove.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				boolean checked = cb_removeAss.isChecked();
				
				ProtocolServiceAsync instance = ProtocolService.Util
						.getInstance();
				instance.removeProtocol(Integer.parseInt(pidInput.getText()), checked, new AsyncCallback() {

					public void onFailure(Throwable error) {
						RemoveProtocolPopup.this.hide();
					}

					public void onSuccess(Object retValue) {
						RemoveProtocolPopup.this.hide();
					}
				});
			}
		});
		buttonPanel.add(b_remove);

		Button b_cancel = new Button("Cancel");
		b_cancel.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				RemoveProtocolPopup.this.hide();
			}
		});
		buttonPanel.add(b_cancel);
		inputFormPanel.add(buttonPanel);
		this.add(inputFormPanel);

		return inputFormPanel;
	}
}
