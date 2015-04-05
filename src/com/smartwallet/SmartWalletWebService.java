package com.smartwallet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.smartwallet.DTOs.ContactDTO;
import com.smartwallet.DTOs.TransactionDTO;
import com.smartwallet.DTOs.UserDTO;
import com.smartwallet.toXMLconverters.ToXMLConverter;
import com.smartwallet.utils.JDBCConnector;

@WebService
public class SmartWalletWebService {

	@WebMethod	
	public int AddContact(String userName,String contactName) throws SQLException{
		int errorcode=-1;
		Connection conn = JDBCConnector.GetConnection();
		String queryString = "call smartwallet_database.sp_add_user_contact('"+userName+"','"+contactName+"')";
		CallableStatement query2 = conn.prepareCall(queryString);
		ResultSet rs2= query2.executeQuery();
		while(rs2.next()){
			errorcode = rs2.getInt("errorcode");
			break;
		}
		conn.close();
		return errorcode;
	}

	@WebMethod
	public String ChangeRequestStatus(String userName){
		int errorcode =-1;
		return Integer.toString(errorcode);
	}


	@WebMethod	
	public String GetUser(String userName,String pwd) throws SQLException{

		Connection conn = JDBCConnector.GetConnection();
		UserDTO user = null;
		String queryString = "call smartwallet_database.sp_GetUser('"+userName+"','"+pwd+"')";
		//CallableStatement query2 = conn.prepareCall("call smartwallet_database.GetUser('ank05kumar','0504')");
		CallableStatement query2 = conn.prepareCall(queryString);
		ResultSet rs2= query2.executeQuery();
		while(rs2.next()){
			int userId = rs2.getInt("userId");
			String fname = rs2.getString("firstName");
			String lname = rs2.getString("lastName");
			String email = rs2.getString("emailId");
			String mobr = rs2.getString("mobileNumber");
			user = new UserDTO(userId, userName, fname, lname, email, pwd, mobr);
			break;
		}
		conn.close();

		return (ToXMLConverter.ConvertPersonToXml(user));

	}

	@WebMethod
	public boolean CreateUser(String userinputuserId,String firstName,String lastName,String emailId,String passwordForLogin,
			String mobileNumber) throws SQLException
	{
		Connection conn = JDBCConnector.GetConnection();
		String query = "INSERT INTO smartwallet_database.users_tbl (userinputuserId,firstName,lastName,emailId,"
				+"passwordForLogin,mobileNumber)"
				+"VALUES ("
				+"?"
				+",?"
				+",?"
				+",?"
				+",?"
				+",?";
		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, userinputuserId);
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ps.setString(4,emailId);
		ps.setString(5,passwordForLogin);
		ps.setString(6,mobileNumber);

		int code = ps.executeUpdate();
		conn.close();
		if(code!=0)
		{
			return true;
		}
		return false;
	}


	@WebMethod	
	public int AddTen(int n){
		return (n+20);
	}

	@WebMethod
	public int GetBalance(String userId) throws SQLException{
		int balance=-1;
		Connection conn = JDBCConnector.GetConnection();
		String query = "select balance from smartwallet_database.balance_tbl where userId= ?";
		PreparedStatement  ps = conn.prepareStatement(query);
		ps.setInt(1,GetUserIdFromDB(userId) );
		ResultSet result = ps.executeQuery();
		while(result.next()){	
			balance = result.getInt("balance");
			break;	
		}
		conn.close();
		return balance;
	}

	@WebMethod	
	public boolean DoTransaction(String giver,String taker,int amount) throws SQLException{
		int giverId= GetUserIdFromDB(giver);
		int takerId= GetUserIdFromDB(taker);
		if(GetBalance(giver)<amount||takerId==-1)
			return false;

		boolean credit = UpdateBalance(takerId,amount,true);
		boolean debit  = UpdateBalance(giverId,amount,false);
		boolean insertRecord = InsertInTransaction(giverId,takerId,amount);

		if(credit&&debit&&insertRecord)
			return true;

		return false;
	}


	private int GetUserIdFromDB(String userInputId) throws SQLException{

		Connection conn = JDBCConnector.GetConnection();
		String query = "select userId from smartwallet_database.users_tbl where userinputuserId= '"+userInputId+"'";
		Statement ps = JDBCConnector.conn.prepareStatement(query);

		ResultSet rs = ps.executeQuery(query);

		while(rs.next())
		{
			int id = rs.getInt("userId");
			return id;
		}
		conn.close();
		return -1;


	}

	private boolean InsertInTransaction(int giver, int taker, int amount) throws SQLException {

		Connection conn = JDBCConnector.GetConnection();
		java.util.Date dt = new java.util.Date();
		/*TimeZone tz1 = TimeZone.getTimeZone("GMT+5:30");

		java.text.SimpleDateFormat sdf = 
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(tz1);*/
		String query = "INSERT INTO smartwallet_database.transaction_tbl VALUES (?,?,?,?,null,null)";                             ;

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, taker);
		ps.setInt(2, giver);
		ps.setInt(3, amount);
		ps.setString(4,GetCurrentDateTimeInISt(dt));

		ps.executeUpdate();
		conn.close();
		return true;
	}


	public String  GetCurrentDateTimeInISt(java.util.Date dt)
	{
		TimeZone tz1 = TimeZone.getTimeZone("GMT+5:30");
		java.text.SimpleDateFormat sdf = 
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(tz1);
		return sdf.format(dt);
	}	
	
	@WebMethod
	public String GetContactOfUser(String  userIdOfuser) throws SQLException{
		
		Connection conn = JDBCConnector.GetConnection();
		ArrayList<ContactDTO> contacts = new ArrayList<ContactDTO>();
		String queryString = "call smartwallet_database.sp_GetContactsOfUser('"+userIdOfuser+"'"+")";
		//CallableStatement query2 = conn.prepareCall("call smartwallet_database.GetUser('ank05kumar','0504')");
		CallableStatement query2 = conn.prepareCall(queryString);
		ResultSet rs2= query2.executeQuery();
		while(rs2.next()){
			String userId = rs2.getString("userinputuserId");
			String fname = rs2.getString("firstName");
			String lname = rs2.getString("lastName");
			String email = rs2.getString("emailId");
			String mob = rs2.getString("mobileNumber");
			contacts.add(new ContactDTO(userId,fname,lname,email,mob));
			
		}
		conn.close();

		return (ToXMLConverter.ConvertContactsToXml(contacts));

	
	}


	public boolean UpdateBalance(int userId ,int amount,boolean add) throws SQLException {
		Connection conn = JDBCConnector.GetConnection();

		if(!add)
			amount = - amount;
		String query = "update smartwallet_database.balance_tbl  set smartwallet_database.balance_tbl.balance  = balance + ? where smartwallet_database.balance_tbl.userId = ?";                             ;

		PreparedStatement ps = JDBCConnector.conn.prepareStatement(query);
		ps.setInt(1, amount);
		ps.setInt(2, userId);
		ps.executeUpdate();
		conn.close();
		return true;
	}


	@WebMethod
	public String SendUdhaarRequest(String userName,String contactName,int amount,int requeststatus) throws SQLException{
		int errorcode=-1;
		Connection conn = JDBCConnector.GetConnection();
		java.util.Date dt = new java.util.Date();
		String queryString = "call smartwallet_database.sp_send_udhar_request('"+userName+"','"+contactName+"' ,"+amount+","+1+","+GetCurrentDateTimeInISt(dt)+")";

		CallableStatement query2 = conn.prepareCall(queryString);
		ResultSet rs2= query2.executeQuery();
		while(rs2.next()){
			errorcode = rs2.getInt("errorcode");
			break;
		}
		conn.close();
		return Integer.toString(errorcode);
	}

	@WebMethod
	public String GetLastNTransactions(int n , String userInputId) throws SQLException{
		ArrayList<TransactionDTO> listOfTransactions = new ArrayList<TransactionDTO>();
		Connection conn = JDBCConnector.GetConnection();
		String query =
				" SELECT u1.firstName AS creditorFirstName"
						+",u2.firstName AS debitorFirstName"
						+",t1.amount"
						+",t1.timeOfTransaction"
						+",t1.placeLatitude"
						+",t1.placeLongitude "
						+" FROM smartwallet_database.transaction_tbl AS t1" 
						+" INNER JOIN users_tbl AS u1 ON t1.creditor = u1.userId"
						+" INNER JOIN users_tbl AS u2 ON u2.userId = t1.debitor WHERE debitor = ?"
						+" OR creditor = ?"
						+" ORDER BY timeOfTransaction DESC LIMIT ?";

		PreparedStatement ps = JDBCConnector.conn.prepareStatement(query);
		int userId= GetUserIdFromDB(userInputId);
		ps.setInt(1, userId);
		ps.setInt(2, userId);
		ps.setInt(3,n);

		ResultSet rs = ps.executeQuery();

		while(rs.next())
		{
			String debitor= rs.getString("debitorFirstName");
			String creditor = rs.getString("creditorFirstName");
			int amount = rs.getInt("amount") ;
			String timeOfTransaction= rs.getString("timeOfTransaction");
			listOfTransactions.add(new TransactionDTO(debitor,creditor, amount,timeOfTransaction));
		}

		conn.close();

		return(ToXMLConverter.ConvertListOfTransactionToXml(listOfTransactions));
		//return (ps.toString());
	}


}
