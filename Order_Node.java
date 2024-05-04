package application;

public class Order_Node {
	
	private Order costamerData;
	private Order_Node Next;
	
	
	public Order_Node() {
		super();
	}


	public Order_Node(Order costamerData) {
		super();
		this.costamerData = costamerData;
	}


	public Order getCostamerData() {
		return costamerData;
	}


	public void setCostamerData(Order costamerData) {
		this.costamerData = costamerData;
	}


	public Order_Node getNext() {
		return Next;
	}


	public void setNext(Order_Node next) {
		Next = next;
	}

	


}
