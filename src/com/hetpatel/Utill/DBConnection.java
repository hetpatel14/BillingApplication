package com.hetpatel.Utill;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public Connection getDBConnection() {
		
		Connection myCon = null;
		try {
			myCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/itemForCustomer","root","het12345");
			System.out.println("Connection Successful");
		}catch(Exception e) {
			e.getMessage();
		}
		return myCon;
		
	}
}
