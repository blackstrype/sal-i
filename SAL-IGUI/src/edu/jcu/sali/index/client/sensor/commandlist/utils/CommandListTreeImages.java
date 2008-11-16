package edu.jcu.sali.index.client.sensor.commandlist.utils;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.TreeImages;

/**
 * Images for the command-list-tree.
 * 
 * @author Marc Hammerton
 *
 */
public interface CommandListTreeImages extends TreeImages {
	@Resource("edu/jcu/sali/index/public/images/arrow_down.gif")
    AbstractImagePrototype treeOpen();
    
    @Resource("edu/jcu/sali/index/public/images/arrow_right.gif")
    AbstractImagePrototype treeClosed();

}
