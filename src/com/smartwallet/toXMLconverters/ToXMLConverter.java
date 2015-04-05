package com.smartwallet.toXMLconverters;

import java.util.ArrayList;

import com.smartwallet.DTOs.ContactDTO;
import com.smartwallet.DTOs.TransactionDTO;
import com.smartwallet.DTOs.UserDTO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ToXMLConverter {
	
public static XStream xstream = new XStream(new DomDriver());
	
	public static String ConvertPersonToXml(UserDTO user){
		
		xstream.alias("User",UserDTO.class );
		String xml = xstream.toXML(user);
		
		return xml;
		
	}
	
	public static String ConvertListOfTransactionToXml(ArrayList<TransactionDTO> tran){

		xstream.alias("Transaction",com.smartwallet.DTOs.TransactionDTO.class );
		String xml = xstream.toXML(tran);
		return xml;
		
	}

	public static String ConvertContactsToXml(ArrayList<ContactDTO> contacts) {
		// TODO Auto-generated method stub
		xstream.alias("Contacts", com.smartwallet.DTOs.ContactDTO.class);
		String xml = xstream.toXML(contacts);
		return xml;
	}
	

}
