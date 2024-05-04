package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Double_LinkedList {

private Double_Node first;  // the first Node in double linked list
	
	

	public Double_LinkedList() { //  Default constructed with no argument
		super();
	}

	public Double_LinkedList(Double_Node first) { // constructed with parameter
		super();
		this.first = first;
	}

	// setter and getter for first Node
	public Double_Node getFirst() {
		return first;
	}

	public void setFirst(Double_Node first) {
		this.first = first;
	}
	
	
    public void insertSort(Brand object) {
		
		Double_Node newNode = new Double_Node(object);//Create a new node  
		
	     // case 1 Add to Empty List 

		if (first == null) {
			                     // means LinkedList is empty, make first point to new Node.
			first = newNode;
		}
		
		
		// case 2 to Add to first place in List
		else if (first.getData().compareTo(object) > 0) { // means that the new Node less than the first Node in list
			newNode.setNext(first);
			first.setPrivous(newNode);
			first = newNode;
		}
		
		else { // case 3 Add between tow Node and at the end of List 
			
			Double_Node current = first; // Node current will point to head  
			
			while (current.getNext() != null &&  (current.getNext().getData().compareTo(object) < 0)) {
				
			
				current = current.getNext(); // move current to the Node 
			}
			
			newNode.setNext(current.getNext());
			
			if (current.getNext() != null) { //make previous of current's next node point to newNode.
				newNode.getNext().setPrivous(newNode);
			}
			
			current.setNext(newNode); //make current's next point to newNode.
			newNode.setPrivous(current);  //make newNode's previous point to current.
			
		}
		
    }

		public void delete(Brand object) {
			
			if (first == null) {  // case 1 Empty list 
				return ;
			}
			else if (first.getData().compareTo(object) == 0) { // case 2 Remove first
				first = first.getNext();
				
			}
			
			else {
				
				Double_Node current = first; // Node current will point to first 
				Double_Node Tail = first.getNext();
				
				while (Tail != null && Tail.getData().compareTo(object) != 0) {
					 current = Tail;
					 Tail = Tail.getNext(); // move current to the Node 
				}
				
				if (Tail == null) // case 3 if not found 
					return;
				
				else if(Tail.getNext() == null) {  // case 4 Remove last
					current.setNext(null);
				}
				
				else {
					current.setNext(Tail.getNext());  // case 5 Remove Node between tow Node 
					Tail.getNext().setPrivous(current);
				}
			}
	}
		
		
		
		// this method to update a Location Name 
		public void update(String oldBrand , String newBrabd) { 
			Double_Node current = first;   // Node current will point to first 
			
			while (current != null) { // if the double linked list still has a node 
				if (current.getData().getCarBrand().equals(oldBrand) == true) { // Check if the the node has the same mane you want to change
					current.getData().setCarBrand(newBrabd); // change the Location name 
					
					
				}
				
				current = current.getNext(); //  move current to the Next Node 
			}
		}
		
		
		
		public Double_Node search(Brand object) {
			
			Double_Node current = first; // Node current will point to first 
			
			if (current == null) { // case1 if the double list is empty 
				return null;
			}
			
			else {
				for (  ; current != null ; current = current.getNext() ) { // for loop to check if the location you search is exist
					if (current.getData().getCarBrand().trim().equalsIgnoreCase(object.getCarBrand().trim())) // check
						return current;
					
				}
				return null;	

			}
		}
		
public Double_Node searchCareForOrder(Order object) {
			
			Double_Node current = first; // Node current will point to first 
			
			if (current == null) { // case1 if the double list is empty 
				return null;
			}
			
			else {
				for (  ; current != null ; current = current.getNext() ) { // for loop to check if the location you search is exist
					if (current.getData().getCarBrand().equals(object.getCarBrand())) // check
						return current;
					
				}
				
			}
			return null;	
		}
		
		// this method to show all martyr information in the location you select
		 public void textArea(Brand brandName) { 
	    	 Stage stage = new Stage(); // create a new stage 
	    	 TextArea textArea = new TextArea(); // create a new TextArea
	    	 GridPane pane = new GridPane (); 
			 Button back = new Button ("Back");  // this button to back to a before screen 
			 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
			 
			 textArea.setMinWidth(600);
			 textArea.setMaxHeight(500);


	    	 pane.setAlignment(Pos.CENTER);
	    	 pane.add(textArea, 0, 0);
	    	 pane.add(back, 3, 7);
	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));

				if (search(brandName) != null) { // if the location is exist
				Single_Node curr2 = search(brandName).singleList.getFirst() ; // first node in single list
				 
			     	for ( ; curr2 != null ; curr2 = curr2.getNext()) { // for loop to get information from each node  
			     		 stringBuilder.append(curr2.getData().toString()).append("\n"); // and the information to stringBuilder
			     		textArea.setText(stringBuilder.toString());	// Add all information to a text Area as string 
			     		textArea.setFont(newFont);
				}
				
			     	
				}
				
				else {
					textArea.setText(" The given Brand is Not found "); // if the location does not exist
				}
		     
				 back.setOnAction(e->{ // back button action 
					 Main main = new Main();
				//	main.adminStage();
					 
				 });
				 Scene scene = new Scene(pane , 700 , 600); // create a new scene 
				 stage.setScene(scene); // add the scene to the stage 
				 stage.setTitle("Report for Location "); // set a title to  the stage 
				 stage.show();
				
	    	 
	    	 }
		
		
		
	public boolean chick (String B) {
		Double_Node current = first; // Node current will point to first 

		for ( ; current!=null ; current=current.getNext()) {
			if (current.getData().getCarBrand().trim().equalsIgnoreCase(B.trim()))
				return true;
		}
		
		return false;
	}
}
