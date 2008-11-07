package edu.sal.sali.ejb.exeption;

import jcu.sal.common.exceptions.ConfigurationException;
import jcu.sal.common.exceptions.NotFoundException;
import jcu.sal.common.exceptions.SALDocumentException;
import jcu.sal.common.exceptions.SensorControlException;

public class SALException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2692678946151776803L;


	public SALException() {
		// TODO Auto-generated constructor stub
	}

	public SALException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SALException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SALException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean isSALDocumentException(){
		
		if (this.getCause() instanceof SALDocumentException)
			return true;
		
		return false;
	}
	
	
	public boolean isConfigurationException(){
		
		if (this.getCause() instanceof ConfigurationException)
			return true;
		
		return false;
	}
	
	public boolean isNotFoundException(){
		
		if (this.getCause() instanceof NotFoundException)
			return true;
		
		return false;
	}
	
	
	public boolean isSensorControlException(){
		
		if (this.getCause() instanceof SensorControlException)
			return true;
		
		return false;
	}
}
