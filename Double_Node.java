package application;

public class Double_Node {
	
	private Brand data;
	private Double_Node Next; // pointer to the next node in the list 
	private Double_Node privous; // pointer to the previous node in the list 
	
	 
	Single_LinkedList  singleList = new Single_LinkedList ();  // create Object from Linked list in The double linked list Node


   // a default constructor with no parameter
	
	public Double_Node() {
		super();
	}


	 // a constructor with a parameter 

	public Double_Node(Brand data) {
		super();
		this.data = data;
		
	}

	// setter and getter for All variable in Double linked list Node

	public Brand getData() {
		return data;
	}


	public void setData(Brand data) {
		this.data = data;
	}


	public Double_Node getNext() {
		return Next;
	}


	public void setNext(Double_Node next) {
		Next = next;
	}


	public Double_Node getPrivous() {
		return privous;
	}


	public void setPrivous(Double_Node privous) {
		this.privous = privous;
	}


	public Single_LinkedList getSingleList() {
		return singleList;
	}


	public void setSingleList(Single_LinkedList singleList) {
		this.singleList = singleList;
	}
	
	
	
	
}
