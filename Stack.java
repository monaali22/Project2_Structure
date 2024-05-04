package application;

public class Stack {
	
	private Order_list Linked= new Order_list ();
	
	

	public Stack() {
		super();
	}

	
	public boolean isEmpty()
	{
		return (Linked.isEmptyS());
	}

	
	public void push(Order costamers)
	{
		Linked.insertFirstS(costamers);
	}
	
	
	public Order pop()
	{
		return (Linked.deleteFirstS());
	}
	
	
	
	
	
}

