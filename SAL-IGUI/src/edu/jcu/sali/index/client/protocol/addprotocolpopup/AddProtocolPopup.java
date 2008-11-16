package edu.jcu.sali.index.client.protocol.addprotocolpopup;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.protocol.ProtocolService;
import edu.jcu.sali.index.client.protocol.ProtocolServiceAsync;

/**
 * A pop-up for adding a protocol.
 * 
 * @author Marc Hammerton
 * 
 */
public class AddProtocolPopup extends DialogBox {

	private TextArea textarea;

	/**
	 * Initializes the pop-up.
	 */
	public AddProtocolPopup() {
		setText("Add new Protocol");
		setWidget(getInputFormPanel());
	}

	/**
	 * Creates the input-form to get all the information for the new protocol.
	 * It contains of text-area for the XML for the new protocol and two buttons
	 * to add the protocol or to cancel the action.
	 * 
	 * @return inputFormPanel
	 */
	public VerticalPanel getInputFormPanel() {
		VerticalPanel inputFormPanel = new VerticalPanel();
		inputFormPanel.setStyleName("dialogInputForm");

		// XML-Structure
		Label la_textarea = new Label(
				"Please enter the new protocol in XML format");
		inputFormPanel.add(la_textarea);

		textarea = new TextArea();
		textarea.setText("");
		textarea.setVisibleLines(15);
		textarea.setCharacterWidth(80);
		inputFormPanel.add(textarea);

		// Buttons
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		Button b_add = new Button("Add");
		b_add.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				ProtocolServiceAsync instance = ProtocolService.Util
						.getInstance();
				instance.addProtocol(textarea.getText(), new AsyncCallback() {

					public void onFailure(Throwable error) {

					}

					public void onSuccess(Object retValue) {
						AddProtocolPopup.this.hide();
					}
				});
			}
		});
		buttonPanel.add(b_add);

		Button b_cancel = new Button("Cancel");
		b_cancel.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				AddProtocolPopup.this.hide();
			}
		});
		buttonPanel.add(b_cancel);
		inputFormPanel.add(buttonPanel);
		this.add(inputFormPanel);

		return inputFormPanel;
	}
}
