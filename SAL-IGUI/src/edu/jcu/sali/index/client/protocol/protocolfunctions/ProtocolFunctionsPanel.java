package edu.jcu.sali.index.client.protocol.protocolfunctions;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.protocol.addprotocolpopup.AddProtocolPopup;
import edu.jcu.sali.index.client.protocol.removeprotocolpopup.RemoveProtocolPopup;

/**
 * This panel caontains the functions for the protocols.
 * 
 * @author Marc Hammerton
 * 
 */
public class ProtocolFunctionsPanel extends VerticalPanel {

	private AddProtocolPopup addProtocolPopup;
	private RemoveProtocolPopup removeProtocolPopup;

	/**
	 * Initialize the panel. The following functions are added: Add Protocol and
	 * Remove Protocol.
	 */
	public ProtocolFunctionsPanel() {
		this.setStyleName("protocolFunctions");

		Hyperlink hl_addProtocol = new Hyperlink("Add Protocol",
				"addProtocolToken");
		hl_addProtocol.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				addProtocolPopup = new AddProtocolPopup();
				addProtocolPopup
						.setPopupPositionAndShow(new DialogBox.PositionCallback() {
							public void setPosition(int offsetWidth,
									int offsetHeight) {
								int left = (Window.getClientWidth() - offsetWidth) / 3;
								int top = (Window.getClientHeight() - offsetHeight) / 5;
								addProtocolPopup.setPopupPosition(left, top);
							}
						});

			}

		});
		this.add(hl_addProtocol);

		Hyperlink hl_removeProtocol = new Hyperlink("Remove Protocol",
				"removeProtocolToken");
		hl_removeProtocol.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				removeProtocolPopup = new RemoveProtocolPopup();
				removeProtocolPopup
						.setPopupPositionAndShow(new DialogBox.PositionCallback() {
							public void setPosition(int offsetWidth,
									int offsetHeight) {
								int left = (Window.getClientWidth() - offsetWidth) / 3;
								int top = (Window.getClientHeight() - offsetHeight) / 5;
								removeProtocolPopup.setPopupPosition(left, top);
							}
						});

			}

		});
		this.add(hl_removeProtocol);
	}

}
