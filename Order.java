package application;

import java.util.Date;

public class Order extends Cars {


	private String CostamerName  , orderStatus ;
	private Date OrderDate;
	private int CostamerMobil ;

	
	public Order() {
		super();
	}
	
	
	


	public Order(String model, String color, String carBrand, int year, double price , String CostamerName , Date OrderDate ,  int CostamerMobil 
			,String orderStatus) 
	{
		super(model, color, carBrand, year, price);
		this.CostamerName =  CostamerName;
		this.OrderDate = OrderDate;
		this.CostamerMobil = CostamerMobil;
		this.orderStatus= orderStatus;
	}



	public String getCostamerName() {
		return CostamerName;
	}


	public void setCostamerName(String costamerName) {
		CostamerName = costamerName;
	}


	public Date getOrderDate() {
		return OrderDate;
	}


	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public int getCostamerMobil() {
		return CostamerMobil;
	}


	public void setCostamerMobil(int costamerMobil) {
		CostamerMobil = costamerMobil;
	}
	
	





	@Override
	public String toString() {
		return "" + CostamerName + " , " +CostamerMobil+" , "+ getCarBrand()+" , "+ getModel()+" , "+ getColor()+" , "
	             + getYear()+ " ,"+OrderDate + "  , " + getPrice() + " , "+getOrderStatus()	;
	}




	
	
	
	

}
