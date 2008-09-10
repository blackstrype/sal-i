package edu.jcu.sali.server;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class Clientwrapper
 */
@Stateless
public class Clientwrapper implements ClientwrapperLocal {

    /**
     * Default constructor. 
     */
    public Clientwrapper() {
    	initSalClient();
        // TODO Auto-generated constructor stub
    }

	private void initSalClient() {
		// TODO Auto-generated method stub
		
	}

}
