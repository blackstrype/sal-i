/**
 * 
 */
package edu.sal.sali.ejb;

import java.util.List;

import javax.naming.ConfigurationException;

import jcu.sal.common.cml.ArgumentType;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.cml.ReturnType;

/**
 * @author jse
 *
 */
public class CMLCommand extends CMLDescription {

	public CMLCommand(String methodName, Integer id, String name, String desc,
			List<ArgumentType> argTypes, List<String> argNames,
			ReturnType returnType) throws ConfigurationException {
		super(methodName, id, name, desc, argTypes, argNames, returnType);
		// TODO Auto-generated constructor stub
	}

}
