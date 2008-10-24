package edu.jcu.sali.index.client.utilities;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class Utilities {
	
	private static final String LOADER_IMG = "<img class=\"loaderImage\" src=\"images/ajax-loader.gif\">";

	public static DeckPanel getLoaderPanel() {
		DeckPanel loaderPanel = new DeckPanel();
		loaderPanel.setStyleName("loaderPanel");
		loaderPanel.add(new HTML(LOADER_IMG));
		
		return loaderPanel;
	}
	
	public static Widget getLoaderWidget() {		
		return new HTML(LOADER_IMG);
	}
}
