package com.hetpatel;

import java.util.*;
import java.sql.*;

public class Item {

	int itemid;
	
	private String name;
	
	private int quantity;
	
	private double price;
	Scanner sc=new Scanner(System.in);

	public String getName() {
		return name;
	}
	public Item() {
	
		
	}
	public Item(int maxid) {
		
		promptForName();
		promptForPrice();
		promptForQuantity();
		itemid=++maxid;
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	private void promptForName()
	{
		System.out.println("Enter the name of the item");
		String s=sc.nextLine();
		setName(s);
	}
	private void promptForPrice()
	{
		System.out.println("Enter the price of the item");
		Double p=sc.nextDouble();
		setPrice(p);
	}
	
	private void promptForQuantity()
	{
		System.out.println("Enter the qunatity of the item");
		int q=sc.nextInt();
		setQuantity(q);
	}
	
	public void purchase()
	{
		System.out.print("How many qunatity you want to purchase");
		int quantity=sc.nextInt();
		this.quantity-=quantity;		
	}
	
	public  String toString() {
		return "ID: "+itemid
			+" Name: "+name
		   +" price: "+price
		+" qunatity: "+quantity+"\n";
	}
	
}
