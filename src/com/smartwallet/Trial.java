package com.smartwallet;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import com.smartwallet.DTOs.TransactionDTO;
import com.smartwallet.toXMLconverters.ToXMLConverter;
import com.smartwallet.utils.JDBCConnector;

public class Trial {

	public static void main(String[] args) throws SQLException {
		JDBCConnector.Load();

		/*String getUserPrc = "{ call GetUser(??) }";
		CallableStatement cs = JDBCConnector.conn.prepareCall(getUserPrc);
	    cs.setString(1, "asdas");
	    cs.setString(2, "asdasd");
	    cs.executeQuery();*/
		int amount = 133;
		System.out.println("_ve amount"+(-amount));
		//String query = "select balance from smartwallet_database.balance_tbl where userId= ?";

		String query =   " SELECT debitor,creditor,amount,timeOfTransaction,placeLatitude,placeLongitude"
				+" FROM smartwallet_database.transaction_tbl"
				+" where debitor= ? or creditor= ?"
				+" ORDER BY timeOfTransaction DESC "
				+" LIMIT ?";

		System.out.println(query);
		java.util.Date dt = new java.util.Date();
		String userInputId="ank05kumar";
		String query6 = "select userId from smartwallet_database.users_tbl where userinputuserId= '"+userInputId+"'";
		System.out.println(query6);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		ArrayList<TransactionDTO> listOfTransactions = new ArrayList<TransactionDTO>();
		String xml=ToXMLConverter.ConvertListOfTransactionToXml(listOfTransactions);
		System.out.println(xml);
		ArrayList<TransactionDTO> list = (ArrayList<TransactionDTO>)ToXMLConverter.xstream.fromXML(xml);
		String str =   (list.toString());
		System.out.println(str);
		String quer4 =" SELECT u1.firstName AS creditorFirstName"
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
		System.out.println(quer4);

		String str45 = "INSERT INTO smartwallet_database.users_tbl (userinputuserId,firstName,lastName,emailId,"
		+"passwordForLogin,mobileNumber,activeFlag,userType)"
		+"VALUES ("
		+"'kbnkj'"
		+",'jnjk'"
		+",'kjnkj'"
		+",'ank05kumar@gmail.com'"
		+",'0504'"
		+",'8376987791'"
		+",1"
		+",1)";
		
		System.out.println(str45);
		String userName = "ank05kumar";
		String p= "kfhcgf";
		int a=78;
		
		String str9= "call smartwallet_database.GetUser('ank05kumar','0504')";
		
		String queryString = "call smartwallet_database.sp_add_user_contact('"+userName+"','"+p+"')";
		
		System.out.println(queryString);
		
		java.util.Date dt1 = new java.util.Date();
		
		String queryString1 = "call smartwallet_database.sp_send_udhar_request('"+userName+"','"+p+"' ,"+amount+","+1+","+dt1.toString()+")";
		
		
		System.out.println(queryString1);
		
		System.out.println(dt1.toString());
		
		String  userIdOfuser="ank05kumar";
		String ds="call smartwallet_database.sp_GetContactsOfUser('"+userIdOfuser+"'"+")";
		System.out.println(ds);
		
		// TODO Auto-generated method stub

	}
}

