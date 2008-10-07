package edu.sal.sali.ejb;
import java.util.ArrayList;

import javax.ejb.Local;

import jcu.sal.common.sml.SMLDescriptions;

@Local
public interface ClientLocal {
	
	public String test();

	ArrayList<String> getSensorList();
	
	
	
}
	