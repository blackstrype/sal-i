/**
 * 
 */
package edu.jcu.sali.index.client.protocol.protocolfunctions;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.jcu.sali.index.client.protocol.addprotocolpopup.AddProtocolPopup;

/**
 * @author Marc
 *
 */
public class ProtocolFunctionsPanel extends VerticalPanel{
	
	private AddProtocolPopup addProtocolPopup;
	
	public ProtocolFunctionsPanel() {
		this.setStyleName("protocolFunctions");
		
		Hyperlink hl_addProtocol = new Hyperlink("Add Protocol","addProtocolToken");
		hl_addProtocol.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {		
				addProtocolPopup = new AddProtocolPopup();
				addProtocolPopup.setPopupPositionAndShow(new DialogBox.PositionCallback() {
			          public void setPosition(int offsetWidth, int offsetHeight) {
			              int left = (Window.getClientWidth() - offsetWidth) / 3;
			              int top = (Window.getClientHeight() - offsetHeight) / 5;
			              addProtocolPopup.setPopupPosition(left, top);
			            }
			          });

			}
			
		});
		this.add(hl_addProtocol);
		
	}

}
