package com.sali.index.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

public class Index implements EntryPoint {

	public void onModuleLoad() {
		DockPanel dock = new DockPanel();
		dock.setSpacing(4);
		dock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dock.add(new HTML("North"),DockPanel.NORTH);
		dock.add(new HTML("Center"),DockPanel.CENTER);
		dock.add(new HTML("South"),DockPanel.SOUTH);
		
		
	}

}
