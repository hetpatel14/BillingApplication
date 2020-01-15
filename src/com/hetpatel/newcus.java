package com.hetpatel;

import java.util.*;
import java.sql.*;

public class newcus {
	private String name;
	private int customerid;
	private int productid;
	
	public newcus()
	{
		
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setCustomerid(int id)
	{
		this.customerid=id;
	}
	
	public int getCustomerid()
	{
		return this.customerid;
	}
	
	public int sendToDB()
	{
		try 
		{
			Connection myCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/itemForCustomer","root","het12345");
			Statement myStat=myCon.createStatement();
			ResultSet myRs=null;
			
			myStat.executeUpdate("INSERT INTO TABLE cusdet VALUES('"+this.name+"',"+this.customerid+");");
			
		}
		catch(Exception e)
		{
			
		}
		return 1;
	}
}
