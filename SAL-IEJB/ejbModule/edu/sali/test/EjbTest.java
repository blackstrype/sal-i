package edu.sali.test;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ejbTest
 */
@Stateless
public class EjbTest implements EjbTestRemote, EjbTestLocal {

    /**
     * Default constructor. 
     */
    public EjbTest() {
        System.out.println("ejbTest --> Im online!! :)");
    }

	@Override
	public void test() {
		System.out.println("ejbTest --> The test method has been called!!");
		
		
	}

}
