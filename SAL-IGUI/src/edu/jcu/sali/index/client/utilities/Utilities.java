package edu.jcu.sali.index.client.utilities;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for utility methods.
 * 
 * @author Marc Hammerton
 * 
 */
public class Utilities {

	/**
	 * Gif-animation which is displayed when loading data from the server.
	 */
	private static final String LOADER_IMG = "<img class=\"loaderImage\" src=\"images/ajax-loader.gif\">";

	/**
	 * Return a panel with the loader-image.
	 * 
	 * @return DeckPanel
	 */
	public static DeckPanel getLoaderPanel() {
		DeckPanel loaderPanel = new DeckPanel();
		loaderPanel.setStyleName("loaderPanel");
		loaderPanel.add(new HTML(LOADER_IMG));

		return loaderPanel;
	}

	/**
	 * Return a widget with the loader-image.
	 * 
	 * @return HTML
	 */
	public static Widget getLoaderWidget() {
		return new HTML(LOADER_IMG);
	}
}
