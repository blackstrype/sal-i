package edu.sali.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcu.sal.common.Response;
import jcu.sal.common.cml.CMLDescription;
import jcu.sal.common.exceptions.NotFoundException;
import jcu.sal.common.exceptions.SensorControlException;

import edu.sal.sali.ejb.CMLCommand;
import edu.sal.sali.ejb.ClientLocal;
import edu.sal.sali.ejb.protocol.SensorCommand;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	ClientLocal client;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doGet(request, response);
		
		// set headers before accessing the Writer
        response.setContentType("text/html");
        //response.setBufferSize(8192);
        PrintWriter out = response.getWriter();

        // then write the response
        out.println("<html>" +
            "<head><title>" +
            "TEST SERVLET" +
            "</title></head><body>");
        
        out.println("</br>test servlet calls Client test... :)");
        out.println("</br>Return val: " + client.test());
        //out.println("</br>test servlet calls Client initialization... )");
        //client.connectToAgent();
        
        out.println("</br>Try to get Sensor List...");
        out.println(client.getSensorList().toString());
        out.println("</br>Try to delete Sensor 2 + 4...");
        client.removeSensor(4);
        client.removeSensor(2);
        
        out.println("</br>Try to get new Sensor List...");
        out.println(client.getSensorList().toString());
        
        out.println("</br>Try to get new Sensor Commands...");
        for (CMLDescription command : client.getCommands(5)){
        	out.println("</br>CID: " + command.getCID() + " - " + command.getName() + " - " + command.getDesc());
        	
        	for (String cmd : command.getArgNames()){
        		out.println("</br>  arg: " + cmd);
        		try {
					out.println("</br>    arg type: " + command.getArgType(cmd).toString());
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        
        out.println("</br>Try to get execute Sensor cmd 100 with sensor 5...");
        SensorCommand scmd = new SensorCommand("5", 100);
        Response resp = client.sendCommand(scmd);
        try {
			out.println("</br>Result: " + resp.getString());
		} catch (SensorControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        out.println("</br>Try to get Protocol List...");
        String list = client.getProtocolList();
        out.println("<code>" + list + "</code>");

//        out.println("</br>Stop bean...");
//        client.stop();	
        
		out.println("</body></html>");
        out.close();

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
