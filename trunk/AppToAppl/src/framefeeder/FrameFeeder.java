package framefeeder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Vector;

/**
 * This class is meant to replace FrameGrabber.java, which is implemented for a linux system
 * It's only purpose is to provide a ByteBuffer feed of pre-determined images for AppletTest
 * @author Scott Messner
 */
public class FrameFeeder {

	private static final int NUM_IMAGES = 16;
	
	private static Vector<ByteBuffer> v;
	private int iterator;
	
	/*
	 * 
	 */
	public FrameFeeder()
	{
		//Retrieve and store all images as ByteBuffers
		File file;
		byte[] b;
		ByteBuffer bb;
		v = new Vector<ByteBuffer>();
		
		for (int i=0; i < NUM_IMAGES; i++)
		{
			file = new File("framefeeder/ball/ball" + Integer.toString(i) + ".jpg");
			try {
				b = getBytesFromFile(file);
				bb = ByteBuffer.wrap(b);
				v.add(bb);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
    
    // Returns the next successive Frame in the image list
    public ByteBuffer getFrame()
    {
    	ByteBuffer bb;
    	bb = v.elementAt(iterator++);
    	if(iterator >= NUM_IMAGES)
    		iterator = 0;
    	
    	return bb;
    }
}
