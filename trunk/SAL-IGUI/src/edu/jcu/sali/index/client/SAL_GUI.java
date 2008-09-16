package org.vivianj.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import agent.listSensors;
import agent.listActiveSensor;
import agent.getCML;
import jcu.sal.common.cml.StreamCallback;
import RMICommand;
import jcu.sal.common.CommandFactory;
import jcu.sal.common.RMICommandFactory;

public class SAL_GUI implements EntryPoint
{
	public void onModuleLoad()
	{
		final Button Sbutton1 = new Button(new SensorConfiguration() // sid);
		final Button Sbutton2 = new Button(new SensorConfiguration() // sid);
		// depending on the number of the sensors

		Sbutton1.addClickListener(new ClickListener()
		{
			public void onClick(Widget Ssender1)
			{
				final Button Cbutton1 = new Button(new CommandDescription() // cid);
				final Button Cbutton2 = new Button(new CommandDescription() // cid);
				// denpending on the number of the commands
				final Button Rbutton = new Button("Go");

				final Label label1 = new Label(new CommandDescription() // cid);
				final Label label2 = new Label(new CommandDescription() // cid);
				// denpending on the number of the commands

				Cbutton1.addClickListener(new ClickListener()
				{
					public void onClick(Widget Csender1)
					{
						label1.setText().equals(new CommandDescription() // cid);
					}
				}
					
			}
		});

		RootPanel.get("Sslot1").add(Sbutton1);
		RootPanel.get("Sslot2").add(Sbutton2);
		RootPanel.get("Cslot1").add(Cbutton1);
		RootPanel.get("Cslot1").add(Cbutton2);
		RootPanel.get("Lslot1").add(label1);
		// depending on the code
	}
}