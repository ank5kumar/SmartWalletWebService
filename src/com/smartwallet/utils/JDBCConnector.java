package com.smartwallet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class JDBCConnector {

	private static String url;
	public static Connection conn;
	public static void Load(){
		{
			try {
				url = "jdbc:google:mysql://smartwallet02:smartwalletserver/smartwallet_database?user=root";
				Class.forName("com.mysql.jdbc.GoogleDriver");

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}

	public static Connection GetConnection() throws SQLException{

		return (conn =  DriverManager.getConnection(url));
	}



}
