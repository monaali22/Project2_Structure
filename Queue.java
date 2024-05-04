package application;

public class Queue {

	private Order_list Linked = new Order_list ();

	
	public Queue() {
		super();
	}
	

	public boolean isEmpty()
	{
		return (Linked.isEmptyQ());
	}
	
	
	public void enqueue (Order order) {
		Linked.insertFirstQ(order);
	}
	
	public Order dequeue() {
		return Linked.deleteLastQ();
	}


	public Order_list getLinked() {
		return Linked;
	}


	public void setLinked(Order_list linked) {
		Linked = linked;
	}
	
	
	public Order_Node  Top() {
		return Linked.getQueueNode();
	}
	
}
