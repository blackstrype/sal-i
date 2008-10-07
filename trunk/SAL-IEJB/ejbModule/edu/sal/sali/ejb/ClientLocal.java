package edu.sal.sali.ejb;
import javax.ejb.Local;

import jcu.sal.common.sml.SMLDescriptions;

@Local
public interface ClientLocal {
	
	public String test();

	SMLDescriptions getSensorList();
	
	
	
}
	