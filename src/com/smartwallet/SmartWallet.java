package com.smartwallet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.smartwallet.utils.JDBCConnector;


public class SmartWallet extends HttpServlet {

	static MessageFactory messageFactory;
	static SmartWalletSoapHandler soapHandler;
	private static final Logger logger =
			Logger.getLogger(SmartWallet.class.getName());



	static{
		try{
			messageFactory = MessageFactory.newInstance();
			soapHandler = new SmartWalletSoapHandler();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "Hello From Ankit" + "</h1>");
		logger.info("Call to getMethod");
	}
	public void init() throws ServletException {
		JDBCConnector.Load();
	}

	public void doPost(HttpServletRequest req ,HttpServletResponse resp) throws IOException{

		MimeHeaders headers = null ;
		InputStream is = req.getInputStream();
		try {
			SOAPMessage soapRequest  = messageFactory.createMessage(headers,is);

			SOAPMessage soapResponse;
			soapResponse= soapHandler.handleSOAPRequest(soapRequest);

			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	static MimeHeaders getHeaders(HttpServletRequest req){
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while(headerNames.hasMoreElements()){
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue,",");
			while(values.hasMoreTokens()){
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}

}











