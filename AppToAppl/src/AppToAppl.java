/*
 * The following code is modified from SAL example code (written by Gilles Gigan)
 * and Sun Microsystems Inc.
 * 
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 * Copyright (C) 2007-2008 Gilles Gigan (gilles.gigan@gmail.com)
 * 
 */

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

//import webcamviewer.FrameGrabber;
import framefeeder.FrameFeeder;

import java.applet.Applet;
import java.nio.ByteBuffer;

public class AppToAppl extends Applet implements ActionListener {
	private static final long serialVersionUID = -753278478730513200L;

	JLabel text;
	JButton button;
	JPanel panel;
	private boolean _clickMeMode = true;

	private JLabel l;
	private JFrame f;
	private long start = 0;
	private int n;
	private boolean stop;
	
	private FrameFeeder ff;
	private Timer t;
	
	private static final int IMAGE_WIDTH = 400;
	private static final int IMAGE_HEIGHT = 400;

	private static final int IMAGE_CYCLE_SPEED = 100;

	public void init() {
		setLayout(new BorderLayout(1, 2));
		setBackground(Color.white);
		
		ff = new FrameFeeder();

		text = new JLabel("I'm a Simple Program");
		button = new JButton("Click Me");
		button.addActionListener(this);
		add("Center", text);
		add("South", button);

		initGUI();
		stop = false;
	}

	public void start() {
		t = new Timer(IMAGE_CYCLE_SPEED, this);
		t.setInitialDelay(IMAGE_CYCLE_SPEED);
		t.start();
		
		System.out.println("Applet Started");
	}

	public void stop() {
		System.out.println("Applet stopping.");
	}

	public void destroy() {
		System.out.println("Destroy method called.");
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == t)
			updateImage();
		else
		{
			if (_clickMeMode) {
				text.setText("Button Clicked");
				button.setText("Click Again");
				_clickMeMode = false;
			} else {
				text.setText("I'm a Simple Program");
				button.setText("Click Me");
				_clickMeMode = true;
			}
		}
	}
	
    /** 
     * Creates the graphical interface components and initialises them
     */
    private void initGUI(){
        f = new JFrame();
        l = new JLabel();
        f.getContentPane().add(l);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
        f.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
    }
    
    public void updateImage()
    {
		ByteBuffer bb;
		byte[] b;
		
    	try {			
//			bb = fg.getFrame();
			bb = ff.getFrame();
			b = new byte[bb.limit()];
			bb.get(b);
			setImage(b);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to capture image");
		}
    }
    
    /**
     * Updates the image shown in the JLabel
     * @param b
     */
    public void setImage(byte[] b) {
    	l.setIcon(new ImageIcon(b));
    	
    	// Computes the frame rate
    	if(start==0)
    		start = System.currentTimeMillis();
    	else if(System.currentTimeMillis()>start+10000) {
			System.out.println("FPS: "+ (((float) 1000*n/(System.currentTimeMillis()-start))  ));
			start = System.currentTimeMillis();
			n = 0;
		} else
			n++;
    }
    
}
