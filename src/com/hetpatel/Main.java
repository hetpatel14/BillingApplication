package com.hetpatel;

import java.util.*;

import com.hetpatel.Utill.DBConnection;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		int check=0;
		
		DBConnection dbConnection = new DBConnection();
		
		try 
		{
			Connection myCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/itemForCustomer","root","het12345");
			Statement myStat=myCon.createStatement();
			ResultSet myRs=null;
			//int counter=0;
		// TODO Auto-generated method stub
		int enter;
		Scanner sc=new Scanner(System.in);
		String ans="N";
		
		while(ans.equals("N")) {
			/** create another method for display */ 
		System.out.println("Enter 1 to add item to the database");
		System.out.println("Enter 2 to create a new bill.	");
		System.out.println("Enter 3 to view all the items");
		System.out.println("Enter 4 to add new customer details");
		System.out.println("Enter 5 to delete an order");
		System.out.println("Enter 6 to delete an item");
		System.out.println("Enter 7 to delete customer details");
		System.out.println("Enter 8 to view itemlist");
		System.out.println("Enter 9 to view customer list");
		System.out.println("Enter 10 to view orderlist");
		enter=sc.nextInt();
		
		switch(enter) {
		
		case 1:		/** to add an item to the database */
			// getting the max id from the database so that we can set the id for different id
			
			myRs=myStat.executeQuery("SELECT MAX(id) FROM itemin;");
			//ResultSetMetaData myRsmd=myRs.getMetaData();
			
			//int column=myRsmd.getColumnCount();
		
			myRs.next();
			int maxid=myRs.getInt(1);
			
			System.out.println("The max id in the table is: "+maxid);
			Item add=new Item(maxid);
				
			 //adding the information to the database
			
			myStat.executeUpdate(""
					+ "INSERT INTO itemin values("+add.itemid+",'"
			+add.getName()
			+"',"
			+add.getQuantity()+
			","
			+add.getPrice()+
			")");	
			
		
			break;
			/** -------------------------------End of case 1---------------------------------------	*/
		
		case 2:	/** to create a new bill */
			// will diplay customer details
			
			myRs=myStat.executeQuery("select * from cusdet");
			
			if(!myRs.next()) {
				System.out.println("No data available");
				System.out.println("Would you like to enter a new customer Y || N?");
				String r=sc.nextLine();
			
				if(r.equals('Y'))
				{
					addCus(1);
					view("cusdet");
				}
						
			}
			else
			{
				view("cusdet");
			}
			
			System.out.println("Enter the customer id who is buying: ");
			
			int customerid=sc.nextInt();
			
			
			/**   purchasing part	*/
			System.out.println("What would you like to purchase? Enter the name of the product");
			view("itemin");
			
			String n=new Scanner(System.in).nextLine();
			
		    myRs=null;
			myRs=myStat.executeQuery(""+"select id from itemin where name='"+n+"'");
			myRs.next();
			
			int productid=myRs.getInt("id");
			System.out.println("productid is "+productid);
			
			/*if(!myRs.next())
			{
				myStat.executeUpdate("INSERT INTO customer VALUE(1,"+cusid+","+id+");");
			}*/
			
			
			
			/** will decrease the quantity from the stock as the customer buy the item	*/
			
			System.out.println("How many items: ");
			int quantity=sc.nextInt();
			
			myRs=null;
			myRs=myStat.executeQuery(""+"select quantity from itemin where id="+productid);
			myRs.next();
			
			System.out.println("Current quantity is "+myRs.getInt(1));
			int new_quantity=myRs.getInt(1)-quantity;
			System.out.println("New quantity is "+new_quantity);
			
			myStat.executeUpdate("UPDATE itemin SET quantity="+new_quantity+" WHERE id="+productid+";");
			
			myRs=null;
			myRs=myStat.executeQuery("Select MAX(orderid) FROM customer");

			/** validating	*/
			
			if(!myRs.next())
			{
				myStat.executeUpdate("INSERT INTO customer VALUES(1,"+customerid+ "," +productid+ ","+quantity+");");
				check=1;
			}
			
			int orderid=myRs.getInt(1)+1;
			System.out.println("Orderid is: "+orderid);
			if(check==1)
				myStat.executeUpdate("INSERT INTO customer VALUES(1"+"," +customerid+ ","
						+productid+ ","+quantity+");");
			else	
				myStat.executeUpdate("INSERT INTO customer VALUES("+orderid+"," +customerid+ ","
										+productid+ ","+quantity+");");
			
			/** displays the total order */
			System.out.println("Order summary: ");
			
			myRs=myStat.executeQuery("select c.orderid, cd.name, i.name, price, c.quantity from customer c inner join itemin i ON i.id=c.productid inner join cusdet cd ON cd.customerid=c.customerid WHERE c.orderid="+orderid+";");
			
			myRs.next();
			
				int orderid_temp =myRs.getInt(1);
				String customername_temp =myRs.getString(2);
				String itemname_temp=myRs.getString(3);
				int price_temp=myRs.getInt(4);
				int quantity_temp=myRs.getInt(5);
				int totalprice_temp=price_temp*quantity_temp;
				
				
				System.out.println("Orderid:\t\t"+orderid_temp+
						"\nCustomer Name:\t\t"+customername_temp+
						"\nItem Name:\t\t"+itemname_temp+
						"\nPrice:\t\t\t"+price_temp+
						"\nQuantity:\t\t"+quantity_temp+
						"\nTotal Price:\t\t"+totalprice_temp);
		
			
			break;
			
			
			/** -------------------------------End of case 2---------------------------------------	*/
			
		case 3: /** to view all the items */
			
			view("itemin");
			
			break;
			/** -------------------------------End of case 3---------------------------------------	*/
			
		case 4:	/** to add the customer details */
			myRs=myStat.executeQuery("SELECT MAX(customerid) FROM customer;");
			if(!myRs.next()) {
			addCus(1);
			}
			else
			{
				addCus(myRs.getInt(1));
			}
			
			break;
			/** -------------------------------End of case 4---------------------------------------	*/
		case 5:
		
			view("customerList");
			
			System.out.println("Enter the orderid of the order you want to delete");
			int enterid=sc.nextInt();
			
			myStat.executeUpdate("delete from customer where orderid="+enterid);
		break;
		
		case 6:
			
			view("itemin");
			
			System.out.println("Enter the productid you want to delete");
			int enprid=sc.nextInt();
			
			myStat.executeUpdate("delete from itemin where id="+enprid);
		break;
			
		case 7:
			
			view("cusdet");
			
			System.out.println("Enter the customerid you want to delete");
			int encdid=sc.nextInt();
			
			myStat.executeUpdate("delete from cusdet where customerid="+encdid);
		break;
		
		case 8:
			view("itemin");
			break;
		
		case 9:
			view("cusdet");
			break;
		
			
		}
		
		System.out.println("Would you like to exit? Y/N");
		
		 ans =sc.next();
		if(ans.equals("N"))
		{
			System.out.println("Good bye");
		}
		
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	public static void addCus(int id)
	{
		newcus n=new newcus();
		String name=new Scanner(System.in).nextLine();
		n.setName(name);
		
		if(id==1) {
			n.setCustomerid(1);
			n.sendToDB();
		}
		else
		{
			n.setCustomerid(id);
			n.sendToDB();
		}
		
	}
	
	public static void view(String s)
	{	
		try {
		Connection myCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/itemForCustomer","root","het12345");
		Statement myStat=myCon.createStatement();
		ResultSet myRs=null;
		
		//if(!s.equals("customerList"))
		myRs=myStat.executeQuery("Select * from "+s+";");
		
		if(s.equals("itemin")) {
		while(myRs.next())
			{
				System.out.println("id: "+myRs.getInt(1)+"  name: "+myRs.getString(2)+""
						+ "  quantity: "+myRs.getInt(3)+"  price: "+myRs.getInt(4)+"\n");
			}
		System.out.println();
		}
		
		if(s.equals("cusdet")) {
			while(myRs.next())
			{
				System.out.println("name: "+myRs.getString(1)+"  customerid: "+myRs.getInt(2)+"\n");
			}
			System.out.println();
		}
		
		if(s.equals("customer")) {
			while(myRs.next())
			{
				System.out.println("orderid: "+myRs.getInt(1)+"  customerid: "+myRs.getInt(2)+
						"productid: "+myRs.getInt(3)+"\n");
			}
			System.out.println();
		}
		
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
		
	}
}
