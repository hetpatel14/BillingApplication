package com.hetpatel;

import java.util.*;
public class Additem {
	Scanner sc=new Scanner(System.in);
	private String st;
	private int quantity;
	public Additem()
	{
		
	}
	
	private void addParams()
	{
		Item newItem=new Item();
		System.out.println("Enter the name of the item: ");
		st=sc.nextLine();
		newItem.setName(st);
		System.out.println("Enter the quantity of the item: ");
	}
	
	
	
}
