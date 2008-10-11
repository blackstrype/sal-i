package edu.jcu.sali.index.client.sensordisplay;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;

public class SensorDisplayPanel extends DockPanel {
	
	public SensorDisplayPanel() {
		this.add(new HTML("<h1>Sensor Display</h1>"), DockPanel.NORTH);
		InsertVideo();
		//InsertImage();
	}
	
	// Insert an HTML widget for displaying a video file.
	// TODO: Streaming video
	// TODO: Access SAL server for streaming video
	public void InsertVideo()
	{
		String videoText = "";
//		videoText += "<object CLASSID='clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B' width='700' height='700' CODEBASE='http://www.apple.com/qtactivex/qtplugin.cab'>\n"
//				   + "<param name='src' value='media/sample_iTunes.mov'>\n"
//				   + "<param name='qtsrc' value='media/sample_iTunes.mov'>\n"
//				   + "<param name='autoplay' value='false'>\n"
//				   + "<param name='loop' value='false'>\n"
//				   + "<param name='controller' value='true'>\n"
//				   + "<embed src='media/sample_iTunes.mov' qtsrc='media/sample_iTunes.mov'></embed>\n"
//				   + "</object>\n";
		
		add(new HTML(videoText), DockPanel.CENTER);
	}
	
	// Insert an HTML widget for displaying a JPEG image
	public void InsertImage()
	{
		String imageText = "";
		imageText += "<img src='images/sample.jpg'>\n";
		
		add(new HTML(imageText), DockPanel.SOUTH);
	}

}
