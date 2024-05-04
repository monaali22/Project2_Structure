package application;

public class Single_Node {

	private Cars data;   // the date in node is care information 
	private Single_Node Next;  // pointer to the next node in the list 
	
	
	public Single_Node() {  // Constructor  with no parameter
		super();
	}


	public Single_Node(Cars data) {  // Constructor  with a parameter
		super();
		this.data = data;
	}

	
	// setter and getter  for variable

	public Cars getData() {
		return data;
	}


	public void setData(Cars data) {
		this.data = data;
	}


	public Single_Node getNext() {
		return Next;
	}


	public void setNext(Single_Node next) {
		Next = next;
	}
	
	
	
	
	
}
