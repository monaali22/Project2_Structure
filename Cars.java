package application;

import java.util.Objects;

public class Cars extends Brand {
	
	private String model , Color    ;
	private int Year;
	private double Price;
	
	
	public Cars() {
		super();
	}
	
	

	public Cars(String model, String color, String CarBrand , int year, double price ) {
		super(CarBrand);
		this.model = model;
		this.Color = color;
		this.Year = year;
		this.Price = price;
	}


	public String getModel() {
		return model;
	}
	


	public void setModel(String model) {
		this.model = model;
	}


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
	}


	public int getYear() {
		return Year;
	}


	public void setYear(int year) {
		Year = year;
	}


	public double getPrice() {
		return Price;
	}


	public void setPrice(double price) {
		Price = price;
	}
	
	


	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Cars otherCar = (Cars) obj;
	    return this.getCarBrand().equals(otherCar.getCarBrand()) &&
	            this.getModel().equals(otherCar.getModel()) &&
	            this.getColor().equals(otherCar.getColor()) &&
	            this.getYear() == otherCar.getYear() &&
	            Double.compare(this.getPrice(), otherCar.getPrice()) == 0;
	}




	@Override
	public String toString() {
		return " "+ getCarBrand()+" ,"+ model + ", " + Color + ", " + Year + ", " + Price + "";
	}


	public int compareTo(Cars o) {
		return (this.getYear() - o.getYear());
	}
	
	
	
	
	

}
