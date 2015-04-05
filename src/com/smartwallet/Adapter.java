package com.smartwallet;

import java.sql.SQLException;

import com.smartwallet.jaxws.AddTen;
import com.smartwallet.jaxws.AddContact;
import com.smartwallet.jaxws.AddTenResponse;
import com.smartwallet.jaxws.CreateUserResponse;
import com.smartwallet.jaxws.DoTransactionResponse;
import com.smartwallet.jaxws.GetBalanceResponse;
import com.smartwallet.jaxws.GetContactOfUser;
import com.smartwallet.jaxws.GetContactOfUserResponse;
import com.smartwallet.jaxws.GetLastNTransactions;
import com.smartwallet.jaxws.GetLastNTransactionsResponse;
import com.smartwallet.jaxws.GetUser;
import com.smartwallet.jaxws.GetUserResponse;
import com.smartwallet.jaxws.AddContactResponse;
import com.smartwallet.jaxws.SendUdhaarRequestResponse;
public class Adapter {
	SmartWalletWebService wb = new SmartWalletWebService();

	public Object GetUser(GetUser request) throws SQLException {
		GetUserResponse response = new GetUserResponse();
		String u = wb.GetUser(request.getArg0(), request.getArg1());
		response.setReturn(u);
		return response;

	}

	public Object AddTenAndReturn(AddTen request) {
		// TODO Auto-generated method stub
		int i = request.getArg0();
		AddTenResponse resp = new AddTenResponse();
		// TODO Auto-generated method stub
		resp.setReturn(wb.AddTen(request.getArg0()));
		return resp;
	}

	public Object DoTransaction(com.smartwallet.jaxws.DoTransaction req) throws SQLException {
		String giver = req.getArg0();
		String taker = req.getArg1();
		int amount = req.getArg2();
		DoTransactionResponse response = new DoTransactionResponse();
		boolean b = wb.DoTransaction(giver, taker, amount);
		response.setReturn(b);

		return response;
	}

	public Object GetBalance(com.smartwallet.jaxws.GetBalance balance) throws SQLException {
		// TODO Auto-generated method stub
		GetBalanceResponse resp = new GetBalanceResponse();
		int b = wb.GetBalance(balance.getArg0());
		resp.setReturn(b);
		return resp;
	}

	public Object GetLastTransactions(GetLastNTransactions request ) throws SQLException {
		// TODO Auto-generated method stub
		GetLastNTransactionsResponse  resp = new GetLastNTransactionsResponse();
		String s = wb.GetLastNTransactions(request.getArg0(), request.getArg1());
		resp.setReturn(s);
		return resp;
	}

	public Object CreateUser(com.smartwallet.jaxws.CreateUser req) throws SQLException {
		// TODO Auto-generated method stub
		CreateUserResponse resp = new CreateUserResponse();
		boolean result = wb.CreateUser(req.getArg0(), req.getArg1(), req.getArg2(), req.getArg3(), req.getArg4(), req.getArg5());
		resp.setReturn(result);
		return resp;
	}

	public Object AddContact(com.smartwallet.jaxws.AddContact req) throws SQLException {
		// TODO Auto-generated method stub
		AddContactResponse resp= new AddContactResponse();
		int code = wb.AddContact(req.getArg0(), req.getArg1());
		resp.setReturn(code);
		return resp;
	}

	public Object SendUdhaarRequest(com.smartwallet.jaxws.SendUdhaarRequest req) throws SQLException {
		// TODO Auto-generated method stub
		SendUdhaarRequestResponse resp = new SendUdhaarRequestResponse();
		String code = wb.SendUdhaarRequest(req.getArg0(), req.getArg1(), req.getArg2(), req.getArg3());
		resp.setReturn(code);
		return resp;
	}

	public Object GetContactsOfUser(GetContactOfUser req) throws SQLException {
		GetContactOfUserResponse resp = new GetContactOfUserResponse();
		String contacts = wb.GetContactOfUser(req.getArg0());
		resp.setReturn(contacts);
		// TODO Auto-generated method stub
		return resp;
	}

}
