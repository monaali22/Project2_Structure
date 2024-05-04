package application;


public class Single_LinkedList {
	
    private Single_Node first;
    private Order_Node Top;

	public Single_LinkedList() {
		super();
	}

	public Single_LinkedList(Single_Node first) {
		super();
		this.first = first;
	}

	public Single_Node getFirst() {
		return first;
	}

	public void setFirst(Single_Node first) {
		this.first = first;
	}
    
	
    
	
	 public Order_Node getTop() {
		return Top;
	}

	

	public void insertSort(Cars data) {
	    	
	    	Single_Node newNode = new Single_Node(data); //Create a new node  
	    	Single_Node current = first; // Node current will point to head 
	    	Single_Node previous = null;
	    	
	    	while (current != null  && current.getData().getYear() > data.getYear()) {
	    		previous = current ;
	    		current = current.getNext();
	    		
	    	}
	    	
	    	if (first == null) {    // case 1 add to empty link list
	    		first = newNode;
	    		
	    	}
	    	
	    	else if (previous == null) {  // case 2 add to empty first element
	    		newNode.setNext(first);
	    		first = newNode;

	    	}
	    	else if (current == null) {  //case 3 add the last
	    		previous.setNext(newNode);

	    	}
	    	
	    	else {     // case 4 insert between two node (current and previous )
	    		newNode.setNext(current);
	    		previous.setNext(newNode);

	    	}
	    	
	    }
	 /*
	 public void delete (Cars data) {
			Single_Node curr = first;
			Single_Node prev = null;
			if (curr != null&&curr.getData().getModel().equals(data.getModel())&&curr.getData().getColor().equals(data.getColor())
					&&curr.getData().getCarBrand().equals(data.getCarBrand())&&curr.getData().getPrice()==(data.getPrice())
					 && curr.getData().getYear() == data.getYear()) {
				first = curr.getNext();
				return;
				
			}
			
			while (curr!=null&&curr.getData().getModel().equals(data.getModel())==false&&curr.getData().getColor().equals(data.getColor())
					==false&&curr.getData().getCarBrand().equals(data.getCarBrand())==false&&curr.getData().getPrice()==(data.getPrice())
					 ==false&& curr.getData().getYear() != data.getYear()) {
				prev = curr;
				curr = curr.getNext();
				
			}
			
			if (curr != null) { // remove from between node 
				if (prev != null) {
			        prev.setNext(curr.getNext());
			    }
				else {
			        first = curr.getNext();
			    }
				
			}



		}
		*/
	
	public void delete(Cars data) {
	    if (first == null) {
	        // Linked list is empty, nothing to delete
	        return;
	    }

	    Single_Node curr = first;
	    Single_Node prev = null;

	    while (curr != null) {
	        if (curr.getData().getModel().trim().equalsIgnoreCase(data.getModel().trim()) &&
	        		curr.getData().getColor().trim().equalsIgnoreCase(data.getColor().trim()) &&
	            curr.getData().getCarBrand().trim().equalsIgnoreCase(data.getCarBrand().trim()) &&
	            curr.getData().getPrice() == data.getPrice() &&
	            curr.getData().getYear() == data.getYear()) {
	            
	            if (prev != null) {
	                prev.setNext(curr.getNext());
	            } else {
	                first = curr.getNext();
	            }
	            
	            return;
	        }

	        prev = curr;
	        curr = curr.getNext();
	    }
	}

	 public void update(Cars oldData, Cars newData) {
		 if (searchCars(oldData)) {
		 delete(oldData);
		 insertSort(newData);
		}
 

	 }
	public void print () {
		Single_Node curr = first;
		
		while (curr != null)
		{
			System.out.println(curr.getData()+" ");
			curr = curr.getNext();
		}
	}
		
	/*
	public Order deleteLast() // delete last item
	{
		Order temp;
	    Order_Node current = Top;
	 if (Top == null)
	      return null;
	 
	 if (Top.getNext() == null)
	 {
	      temp = Top.getCostamerData();
	      Top = null;
	      return (temp);
	}
	while (current.getNext().getNext() != null)
	     current = current.getNext();
	   temp = current.getNext().getCostamerData();
	   current.setNext(null);;
	   return temp;
	}
    */
	public boolean searchCars(Cars searchCar) {
	    Single_Node curr = first;
	    while (curr != null) {
	        if (curr.getData().getModel().trim().equalsIgnoreCase(searchCar.getModel().trim())&&curr.getData().getColor().trim().equalsIgnoreCase(searchCar.getColor().trim())&&
	        		curr.getData().getCarBrand().trim().equalsIgnoreCase(searchCar.getCarBrand().trim())&&curr.getData().getPrice()==(searchCar.getPrice())
	        		&& curr.getData().getYear() == searchCar.getYear()) {
	            return true;
	        }
	        curr = curr.getNext();
	    }
	    return false;
	}
    
	
	
	
	
	/*
	public boolean searchCars(Cars searchCars) {
	    if (searchCars == null) {
	        return false;
	    }
	    
	    Single_Node curr = first;
	    while (curr != null) {
	        Cars currentVehicle = curr.getData();
	        
	        if (currentVehicle != null && currentVehicle.equals(searchCars)) {
	            return true;
	        }
	        curr = curr.getNext();
	    }
	    return false;
	}
	
	*/
	
	

	

	public void deletec(Cars data) {
        if (first == null) {
            return;
        }

        if (first.getData() == data) {
            first = first.getNext();
            return;
        }

        Single_Node current = first;
        Single_Node previous = null;

        while (current != null && current.getData() != data) {
            previous = current;
            current = current.getNext();
        }

        if (current == null) {
            return;
        }

        previous.setNext(current.getNext());
    }
	
	
	
	public boolean search(String M , String C , String B , int Y , double P) {
	    Single_Node curr = first;
	    for ( ; curr!=null ;curr=curr.getNext()) {
	    	if(curr.getData().getModel().trim().equalsIgnoreCase(M.trim()) && curr.getData().getColor().trim().equalsIgnoreCase(C.trim()) &&curr.getData().getCarBrand().trim().equalsIgnoreCase(B.trim())&&
	    			curr.getData().getPrice() == P && curr.getData().getYear()== Y) {
	    		return true;
	    	}
	    		
	    }

	    return false;
	}
	
	
	
}
