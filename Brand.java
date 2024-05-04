package application;

public class Brand {

	private String CarBrand;

	public Brand() {
		super();
	}

	
	public Brand(String carBrand) {
		super();
		CarBrand = carBrand;
	}


	public String getCarBrand() {
		return CarBrand;
	}


	public void setCarBrand(String carBrand) {
		CarBrand = carBrand;
	}
	/*
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Brand otherBrand = (Brand) obj;
	    return this.CarBrand.equals(otherBrand.CarBrand);
	}
	*/
	
	@Override
	public String toString() {
		return  CarBrand ;
	}
	
	public int compareTo(Brand o) {
		return (this.getCarBrand()).compareTo(o.getCarBrand());
	}
}
