package com.smartwallet;

import java.sql.SQLException;
import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.smartwallet.jaxws.AddTen;
import com.smartwallet.jaxws.GetContactOfUser;
import com.smartwallet.jaxws.GetUser;
import com.smartwallet.jaxws.DoTransaction;
import com.smartwallet.jaxws.GetBalance;
import com.smartwallet.jaxws.GetLastNTransactions;
import com.smartwallet.jaxws.CreateUser;
import com.smartwallet.jaxws.SendUdhaarRequest;
public class SmartWalletSoapHandler {

	private static final String NAMESPACE_URI= "http://smartwallet.com/";
	private static final QName ADD_TEN = new QName(NAMESPACE_URI,"AddTen");
	private static final QName Do_Transaction = new QName(NAMESPACE_URI,"DoTransaction");
	private static final QName GET_Balance = new QName(NAMESPACE_URI,"GetBalance");
	private static final QName Get_User = new QName(NAMESPACE_URI,"GetUser");
	private static final QName GetLastNTransactions = new QName(NAMESPACE_URI,"GetLastNTransactions");
	private static final QName Create_User = new QName(NAMESPACE_URI,"CreateUser");
	private static final QName Add_Contact = new QName(NAMESPACE_URI,"AddContact");
	private static final QName Send_Udhaar_Request = new QName(NAMESPACE_URI,"SendUdhaarRequest");
	private static final QName Change_Request_Status = new QName(NAMESPACE_URI,"SendUdhaarRequest");
	private static final QName Get_Contacts_Of_User = new QName(NAMESPACE_URI,"GetContactOfUser");
	
	private MessageFactory messageFactory;
	private Adapter adapter;

	public SmartWalletSoapHandler() throws SOAPException{
		messageFactory = MessageFactory.newInstance();
		adapter = new Adapter();
	}



	@SuppressWarnings("rawtypes")
	public SOAPMessage handleSOAPRequest(SOAPMessage soapRequest) throws SOAPException, SQLException {
		SOAPBody soapBody = soapRequest.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();
		Object responsePojo=null;

		while(iterator.hasNext()){
			Object next = iterator.next();

			if(next instanceof SOAPElement){
				SOAPElement soapElement= (SOAPElement) next;
				javax.xml.namespace.QName qname = soapElement.getElementQName();

				if(ADD_TEN.equals(qname)){
					responsePojo = handleAddTen(soapElement);
				}

				else if(Get_User.equals(qname)){
					responsePojo = GetUser(soapElement);
				}

				else if(Do_Transaction.equals(qname)){
					responsePojo = DoTransaction(soapElement);
				}

				else if(GET_Balance.equals(qname)){
					responsePojo = GetBalance(soapElement);
				}

				else if(GetLastNTransactions.equals(qname)){
					responsePojo = GetLastNTransactions(soapElement);
				}

				else if(Create_User.equals(qname)){
					responsePojo = CreateUser(soapElement);
				}
				else if(Add_Contact.equals(qname)){
					responsePojo = AddContact(soapElement);
				}
				else if(Send_Udhaar_Request.equals(qname)){
					responsePojo = SendUdhaarRequest(soapElement);
				}
				else if(Change_Request_Status.equals(qname)){
					responsePojo = SendUdhaarRequest(soapElement);
				}
				else if(Get_Contacts_Of_User.equals(qname)){
					responsePojo = GetContactsOfUser(soapElement);
				}
				
			}
		}


		SOAPMessage soapResponse = messageFactory.createMessage();

		soapBody = soapResponse.getSOAPBody();

		if(responsePojo!=null){
			JAXB.marshal(responsePojo, new SAAJResult(soapBody));	
		}
		else{
			SOAPFault fault = soapBody.addFault();
			fault.setFaultString("There is some Falut");
		}

		return soapResponse;
	}

	private Object GetContactsOfUser(SOAPElement soapElement) throws SQLException {
		GetContactOfUser ob = JAXB.unmarshal(new DOMSource(soapElement), GetContactOfUser.class);
		
		// TODO Auto-generated method stub
		return adapter.GetContactsOfUser(ob);
	}



	private Object SendUdhaarRequest(SOAPElement soapElement) throws SQLException {
		// TODO Auto-generated method stub
		SendUdhaarRequest obj =JAXB.unmarshal(new DOMSource(soapElement), SendUdhaarRequest.class);
		return adapter.SendUdhaarRequest(obj);
	}



	private Object AddContact(SOAPElement soapElement) throws SQLException {
		// TODO Auto-generated method stub
		com.smartwallet.jaxws.AddContact obj = JAXB.unmarshal(new DOMSource(soapElement),com.smartwallet.jaxws.AddContact.class);
		return adapter.AddContact(obj);
		
	}



	private Object CreateUser(SOAPElement soapElement) throws SQLException {
		// TODO Auto-generated method stub
		CreateUser obj =  JAXB.unmarshal(new DOMSource(soapElement),CreateUser.class);
		return adapter.CreateUser(obj);

	}



	private Object GetLastNTransactions(SOAPElement soapElement) throws SQLException {
		// TODO Auto-generated method stub
		GetLastNTransactions transactions = JAXB.unmarshal(new DOMSource(soapElement),GetLastNTransactions.class);

		return adapter.GetLastTransactions(transactions);
	}



	private Object GetBalance(SOAPElement soapElement) throws SQLException {
		GetBalance balance = JAXB.unmarshal(new DOMSource(soapElement),GetBalance.class);
		return adapter.GetBalance(balance);

	}



	private Object DoTransaction(SOAPElement soapElement) throws SQLException {
		DoTransaction tran = JAXB.unmarshal(new DOMSource(soapElement),DoTransaction.class);
		return adapter.DoTransaction(tran);
	}



	private Object handleAddTen(SOAPElement soapElement) {
		AddTen i = JAXB.unmarshal(new DOMSource(soapElement),AddTen.class);
		return adapter.AddTenAndReturn(i);
	}



	private Object GetUser(SOAPElement soapElement) throws SQLException {
		// TODO Auto-generated method stub
		GetUser getUser = JAXB.unmarshal(new DOMSource(soapElement),GetUser.class);
		return adapter.GetUser(getUser);

	}


}

