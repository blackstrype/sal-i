package edu.sal.sali.ejb;

import jcu.sal.common.Response;
import jcu.sal.common.events.Event;

public interface SALAgentEventHandler {
	
	public void handle(Event e);
	public void collect(Response arg0);
	public int getID();
	public void renewConnection();
}
