package com.hetpatel;

public class orders {
	
	private int orderid;
	private int customerid;
	private int productid;
	
	public orders(){
		
	}
	public void setOrderId(int orderid)
	{
		this.orderid=orderid;
	}
	
	public void setCustomerId(int customerid)
	{
		this.customerid=customerid;
	}
	
	public void setProductId(int productid)
	{
		this.productid=productid;
	}
	
	public int getOrderid()
	{
		return this.orderid;
	}
	
	public int getCustomerid()
	{
		return this.customerid;
	}
	
	public int getProductid()
	{
		return this.productid;
	}

	public String toString()
	{
		return "Order id: "+getOrderid()+"  Customer id: "+getCustomerid()+"  Product id: "+getProductid();
	}
}
