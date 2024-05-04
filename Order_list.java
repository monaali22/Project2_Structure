package application;

public class Order_list {
    private Order_Node StackrNode;
    private Order_Node queueNode;
    
   
	Order_list(){
    	
    }


	public Order_list(Order_Node stackrNode, Order_Node queueNode) {
		super();
		StackrNode = stackrNode;
		this.queueNode = queueNode;
	}


	public Order_Node getStackrNode() {
		return StackrNode;
	}


	public void setStackrNode(Order_Node stackrNode) {
		StackrNode = stackrNode;
	}


	public Order_Node getQueueNode() {
		return queueNode;
	}


	public void setQueueNode(Order_Node queueNode) {
		this.queueNode = queueNode;
	}

	public boolean isEmptyQ() {
		return (queueNode == null);
	}
	
	public void insertFirstQ(Order data) {
		Order_Node newNode = new Order_Node(data);
        if (queueNode == null) {
        	queueNode = newNode;
        } else {
        	newNode.setNext(queueNode);
            queueNode = newNode;
        }
    }
	
	
	
	
	
	public Order deleteLastQ() // delete last item
	{
		Order temp;
	    Order_Node current = queueNode;
	 if (queueNode == null)
	      return null;
	 
	 if (queueNode.getNext() == null)
	 {
	      temp = queueNode.getCostamerData();
	      queueNode = null;
	      return (temp);
	}
	 
	while (current.getNext().getNext() != null)
	     current = current.getNext();
	   temp = current.getNext().getCostamerData();
	   current.setNext(null);;
	   return temp;
	}
	
	
	
	
	
	
	public void insertFirstS(Order data) {
		Order_Node newNode = new Order_Node(data);
        if (StackrNode == null) {
        	StackrNode = newNode;
        } else {
        	newNode.setNext(StackrNode);
        	StackrNode = newNode;
        }
    }
	
	public Order deleteFirstS() {
		if(StackrNode != null) {
			Order_Node temp = StackrNode;
			StackrNode = StackrNode.getNext();
			return temp.getCostamerData();
			
		}
		else 
			return null;
	
	}

	public boolean isEmptyS() {
		return (StackrNode == null);
	}
	
}
