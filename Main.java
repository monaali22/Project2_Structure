package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList; // import the ArrayList class

public class Main extends Application {
	 String CostemerNAme;
	 int CostemerNumber;
	 
	Cars cars;
	Brand brand;
	Double_LinkedList doubleLis = new Double_LinkedList ();
	Double_LinkedList doubleLisForCostamer = new Double_LinkedList ();

	ComboBox<String> comboBox = new ComboBox<>();
	Order order;
	Stack stack = new Stack();
	Stack tempstack = new Stack();
	Queue queue = new Queue();
	Queue Tempqueue = new Queue();
     Single_LinkedList list = new Single_LinkedList();
	@Override
	public void start(Stage primaryStage) {
		

		list.searchCars(cars);
		firstScreen () ;
		
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Pane firstScreen() {  // The first Scene in the InterFace 
		
		Stage stage = new Stage ();
		BorderPane pane1 = new BorderPane();
		Label label1 = new Label();
		label1.setText(" Welcom in car agency "); // add text to the label
		label1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
		label1.setStyle("-fx-text-fill:red");// set colour to the label font 

	    Image image1 = new Image("C:\\Users\\Lenovo\\Downloads\\gazaPhoto1.jpg"); // add image to the scene 
		ImageView imageView = new ImageView(image1);
		imageView.setFitWidth(500);
		imageView.setFitHeight(400);
		
		Button Admain = new Button ("Admin");
		Admain.setMaxWidth(100);
		Button Client = new Button ("Client");
		Client.setMaxWidth(100);
		Button ReadC = new Button ("Read-Cars-File");
		ReadC.setMaxWidth(120);
		Button ReadO = new Button ("Read-Order-File");
		ReadO.setMaxWidth(120);
		
		HBox hBox = new HBox (20);
		VBox vBox0 = new VBox (5);
		vBox0.setAlignment(Pos.CENTER);
		vBox0.getChildren().addAll(ReadC ,ReadO );
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(Client , Admain , vBox0);
		
		VBox vBox = new VBox(20);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(label1 , imageView , hBox);
		
		pane1.setPadding(new Insets (5 ,5,5,5));
		pane1.setCenter(vBox); // add imageView to pane 
		pane1.setStyle("-fx-background-color: slategrey");
		
		Admain.setOnAction(e->{
			adminStage ();
			
		});
		Client.setOnAction(e->{
			ClientScreen () ;
			//doublel();
			});
		
		ReadC.setOnAction(e->{
			readcarsfile(stage) ;
			
		});
		
		ReadO.setOnAction(e->{
			try {
				readOrderFile(stage);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		});
		Scene scene = new Scene (pane1 , 850 ,800);
		stage.setScene(scene);
		stage.show();
		return pane1;
	}
	
	public void readOrderFile(Stage primaryStage) throws ParseException { // Read the Order File 
		
		 FileChooser fileChooser = new FileChooser();
    	 fileChooser.setTitle("Open Resource File ");
    	 fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*")); // show the file in stage
    	 File Orderfile = fileChooser.showOpenDialog(primaryStage);
		//File Orderfile = new File ("C:\\Users\\Lenovo\\Downloads\\orders.txt"); // path of the file 
		
			if (Orderfile != null) {
				try {
					Scanner scanner = new Scanner (Orderfile);
					
					while (scanner.hasNextLine()) {
						String Line = scanner.nextLine(); // take line line from file 
						String [] split = Line.split(","); // split the file by ,
						
						String name = split[0];
						
						String mobelString = split[1].replaceAll("[^\\d.]", "");
						int mobel = Integer.parseInt(mobelString);// take the  index two from line  and convert it to integer
						
						String beandName = split[2];
						String model = split[3];
						
						String yearString = split[4].replaceAll("[^\\d.]", "");
						int year = Integer.parseInt(yearString);// take the  index two from line  and convert it to integer
						
						String color = split[5];
						String PriceString = split[6].replaceAll("[^\\d.]", "");
						double price = Double.parseDouble(PriceString)*1000;
						Date Date = new SimpleDateFormat("dd/MM/yyyy").parse(split[7]);
						String Status = split[8];

						order = new Order(model,color,beandName,year,price,name,Date,mobel,Status);
						
						if (Status.trim().equalsIgnoreCase("Finished"))
							stack.push(order);
						else 
							queue.enqueue(order);
						
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

		}
	
	
	 public void readcarsfile(Stage primaryStage)  { // this method read all date from a file you choose and save it
		 FileChooser fileChooser = new FileChooser();
    	 fileChooser.setTitle("Open Resource File ");
    	 fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*")); // show the file in stage
    	 File Carsfile = fileChooser.showOpenDialog(primaryStage);
   	 
   	 //`	File Carsfile = new File ("C:\\Users\\Lenovo\\Downloads\\cars.txt");

   	 if (Carsfile != null) { // check if the file has data 
   		 try {
				Scanner scanner = new Scanner (Carsfile); // read from file 
				while (scanner.hasNextLine()) {
					String Line = scanner.nextLine(); // take line line from file 
					String [] split = Line.split(","); // split the file by ,

					
					String Brandcare = split[0]; // take the first index from line 
					String model = split[1] ; // take the  index two from line 
					
					String yearString = split[2].replaceAll("[^\\d.]", "");
					int year = Integer.parseInt(yearString);// take the  index two from line  and convert it to integer
					
					String color = split[3];
					String priceString = split[4].replaceAll("[^\\d.]", "");
					double price = Double.parseDouble(priceString)*1000  ;      
					
					
					cars = new Cars (model ,  color , Brandcare ,year  ,price ); // create cars object with the data you read from file 
					brand = new Brand(Brandcare); // create a brand object with the data you read from file 


				   if (doubleLis.search(brand) == null) { // if the brand he read from file not found 
					   comboBox.getItems().add(Brandcare);
					   doubleLis.insertSort(brand);// insert brand to double list
					   doubleLis.search(brand).singleList.insertSort(cars);	// insert car information to single list
					   doubleLisForCostamer.insertSort(brand);// insert brand to double list
					   doubleLisForCostamer.search(brand).singleList.insertSort(cars);	// insert car information to single list	

				   }
				   else { // if the location he read from file  found 
					   doubleLis.search(brand).singleList.insertSort(cars); // insert car information to single list
					   doubleLisForCostamer.search(brand).singleList.insertSort(cars); // insert car information to single list

				   
				   }
					
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
   		 
   	 }
}
	 
   	 public Pane adminStage () {  // this method to show the Admain interface 
   	    Stage stage = new Stage ();
	    BorderPane border = new BorderPane();
	    Button back = new Button ("Back");

	    back.setMinWidth(200);
	    back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
	    

 	    TabPane tabPane = new TabPane(); // create a new Tab pane 
 	    Tab tab1 = new Tab("Brand Screen"); // create tab 1
 	    Tab tab2 = new Tab("Cars Screen"); // create tab 2
 	    Tab tab3 = new Tab("Report"); // create tab 3
 	    Tab tab4 = new Tab("Read Orders"); // create tab 3


 	  
 	    tabPane.getTabs().addAll( tab1 , tab2 ,tab3 , tab4); // add all tab to the tab pane 
		border.setTop(tabPane);	 // add the Tab pane to the border pane 
		border.setBottom(back);
		tab1.setContent(brandScreen ());
		tab2.setContent(CarsScreen() );
		tab3.setContent(ReportScreen ());
		tab4.setContent(OrderTab());
		
		Scene scene = new Scene(border , 850 ,800);

		stage.setScene(scene);
		stage.setTitle("Admin Screen");
		stage.show();

		
		back.setOnAction(e->{
			firstScreen() ;
		});
		return border;
   	 }
   	 
   	 public Pane  OrderTab() {   // This method to Read  and Show  the Queue by admin.
   		 GridPane pane = new GridPane ();
   		 Label  label2 = new Label();
   		 Button button = new Button ("Read Order");
   		 Button ShowOrder = new Button ("Show Order");

   		label2.setText( "Read the Costamer order from Queue:"); // add text to the label
		label2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
		label2.setStyle("-fx-text-fill:blue");// set colour to the label font 
		button.setPrefWidth(300);
		ShowOrder.setPrefWidth(300);
		pane.setHgap(15);
		pane.setVgap(15);
		pane.add(label2, 0, 0);
		pane.add(button, 0, 5);
		pane.add(ShowOrder, 0, 8);
		
		button.setOnAction(e->{
			readCustomerOrder();
		});
		ShowOrder.setOnAction(e->{
			printQueue ();
		});
		
   		 return pane;
   		 
   	 }
   	 
   	 public Pane brandScreen () {  // This method to do operation in cars brand 
   		 
   		GridPane gridPane = new GridPane(); // create a new GridPane
		Label label2 = new Label();
		Label label = new Label();
		Label labe3 = new Label();
		Label labe4 = new Label();


		TextField text = new TextField();
		TextField newText = new TextField();

		HBox hBox = new HBox(10);
		HBox hBox1 = new HBox(10);
		HBox hBox2 = new HBox(10);
		HBox hBox3 = new HBox(10);

		VBox vBox = new VBox(15);

		//Image image1 = new Image("C:\\Users\\Lenovo\\Desktop\\brand22.png"); // add image to the scene 
		//ImageView imageView = new ImageView(image1);
		//imageView.setFitWidth(500);
		//imageView.setFitHeight(200);
		
		
		  // Create a for Radio Button 
		Button button1 = new Button(" Add "); //  RadioButton 1 
		Button button2 = new Button("Delete"); // RadioButton 2
		Button button3 = new Button("Update"); //RadioButton 3
		Button button4 = new Button("Search"); // RadioButton 4
		Button Done = new Button("Done"); // RadioButton 4


		ToggleGroup group = new ToggleGroup(); // create ToggleGroup and put all button inside it
				
	    button1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		button2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
	    button3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		button4.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(label , text , button1) ;
		
		hBox1.setAlignment(Pos.CENTER);
		hBox1.getChildren().addAll(labe3 , comboBox) ;
		
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(button2, button3 , button4) ;
		
		hBox3.setAlignment(Pos.CENTER);
		hBox3.getChildren().addAll(labe4 ,newText , Done ) ;
		hBox3.setVisible(false);
		
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(hBox , hBox1);
				
		label2.setText( "Please Select which Operations you want\r\n"); // add text to the label
		label2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
		label2.setStyle("-fx-text-fill:blue");// set colour to the label font 
		
		label.setText( "New Brand Name"); // add text to the label
		label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		label.setStyle("-fx-text-fill:black");// set colour to the label font 
		
		labe3.setText( "Valid Brand"); // add text to the label
		labe3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		labe3.setStyle("-fx-text-fill:black");// set colour to the label font 
		
		labe4.setText( "New Name"); // add text to the label
		labe4.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,12)); // set font to the label
		labe4.setStyle("-fx-text-fill:red");// set colour to the label font 
		
		gridPane.setPadding(new Insets (5 ,5,5,5));
		gridPane.setHgap(20); //horizontal gap in pixels
		gridPane.setVgap(10); //vertical gap in pixels
		gridPane.add(label2, 0, 0);
		gridPane.add(vBox, 0, 1);
		gridPane.add(hBox2, 0, 8);
	//	gridPane.add(imageView, 0, 15);

		gridPane.add(hBox3, 0, 10);
		gridPane.setStyle("-fx-background-color: lightcyan ");


		button1.setOnAction(e->{ // Add button for the Brand 
			if (doubleLis.search(new Brand (text.getText())) == null) { // this statement to check if the location is exist before 
				doubleLis.insertSort(new Brand (text.getText())); // add the new location to the double list
				doubleLisForCostamer.insertSort(new Brand (text.getText())); // add the new location to the double list

				comboBox.getItems().add(text.getText());
				   
			}
		});
		
		
		button2.setOnAction(e->{ //  Delete Button to delete Brand 
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();
			brand = new Brand(selectBrand);
			
			if (comboBox.getSelectionModel().getSelectedItem() != null) {
				doubleLis.delete(brand);
				doubleLisForCostamer.delete(brand);
				comboBox.getItems().remove(selectBrand);
			}
			
		});
		
		
		button3.setOnAction(e->{  
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();
			if (comboBox.getSelectionModel().getSelectedItem() != null) {
			hBox3.setVisible(true);
			
			
			}
			
			
		});
		
		
		button4.setOnAction(e->{  // search Button to search for Brand add show the cars in this brand 
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();
			doubleLis.textArea(new Brand(selectBrand));
		});
		
		Done.setOnAction(e->{      // update Button 
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();
			doubleLis.update(selectBrand, newText.getText()); // update a location name 
			doubleLisForCostamer.update(selectBrand, newText.getText()); // update a location name 
			comboBox.getItems().remove(selectBrand);
			comboBox.getItems().add(newText.getText());


		});
   		 
   		 return gridPane ;
   	 }
   	 
   	 
   	 public Pane CarsScreen() {  // This method Show Cars Stage 
   		 Stage stage = new Stage();
   		 GridPane pane = new GridPane();
   	    HBox hBox = new HBox(10); // create a HBox
   		Label labe2 = new Label(" Selected which option you need to do "); // create a label 


      	// Create a for Radio Button 
   		RadioButton button1 = new RadioButton("Insert new car"); //  RadioButton 1 
   		RadioButton button2 = new RadioButton("Update a car"); // RadioButton 2
   		RadioButton button3 = new RadioButton("Delete a car "); //RadioButton 3
   		RadioButton button4 = new RadioButton("Search for a car"); // RadioButton 4
   		
   		ToggleGroup group = new ToggleGroup(); // create ToggleGroup and put all button inside it 
   		button1.setToggleGroup(group);
   		button2.setToggleGroup(group);
   		button3.setToggleGroup(group);
   		button4.setToggleGroup(group);

   		labe2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25)); // set font to the label
   		labe2.setStyle("-fx-text-fill:blue");// set colour to the label font 
   		
    	hBox.setAlignment(Pos.CENTER); // put a hBox in the centre
        hBox.getChildren().addAll(button1 , button2 , button3 ); // add all button to the HBox
   		 
        pane.setHgap(5);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER); // put the pan in the Centre 
		pane.add(labe2, 0, 1); // add the label to the pane  
		pane.add(hBox, 0, 7); // add the HBox to the pane 
		pane.setStyle("-fx-background-color: lightcyan ");
		
		button1.setOnAction(e->{   // The the Action for first radio button  
			
			if (button1.isSelected()) {
				Scene scene1 = new Scene(insertNewCars() , 850 ,800);
				stage.setScene(scene1);
				stage.setTitle("Insert a new Care ");
				stage.show();
			}
		});
		
         button2.setOnAction(e->{   // The the Action for first radio button  
			
			if (button2.isSelected()) {
				Scene scene1 = new Scene(updateNewCars() , 700 ,600);
				stage.setScene(scene1);
				stage.setTitle("Updat a new Care ");
				stage.show();
			}
		});

		button3.setOnAction(e->{   // The the Action for first radio button  
			
			if (button3.isSelected()) {
				Scene scene1 = new Scene(deletetNewCars() , 700 ,600);
				stage.setScene(scene1);
				stage.setTitle("Delete a new Care ");
				stage.show();
			}
		});
		
		

   		 return pane ;
   		 
   	 }
   	 
   	 
   	 public Pane insertNewCars() { // In this method the admin can insert new Cars
   		 GridPane pane = new GridPane ();
   		 Label label = new Label ("choose Brand");
   		 Label titl = new Label ("Car Informations : ");

   		 Label model = new Label ("Car Model ");
   		 Label color = new Label ("Car Color");
   		 Label year = new Label ("Car Year");
   		 Label price = new Label ("Car Price");
   		 Button insert = new Button ("Insert");
   		 Button back = new Button ("Back");

   		 
 		TextField modeltext = new TextField();
		TextField colortext = new TextField();
		TextField yeartext = new TextField();
		TextField pricetext = new TextField();
		

		titl.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
		titl.setStyle("-fx-text-fill:blue");// set colour to the label font 
   		 


		label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
		label.setStyle("-fx-text-fill:red");// set colour to the label font 
   		 
		model.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		model.setStyle("-fx-text-fill:black");// set colour to the label font 
		color.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		color.setStyle("-fx-text-fill:black");// set colour to the label font 
		year.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		year.setStyle("-fx-text-fill:black");// set colour to the label font 
		price.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		price.setStyle("-fx-text-fill:black");// set colour to the label font 
   		 

   		 HBox hBox = new HBox(20);
   		 
   		 hBox.setAlignment(Pos.CENTER);
   		 hBox.getChildren().addAll(insert , back);
   		 
   		pane.setHgap(30);
   		pane.setVgap(30);
   		pane.setPadding(new Insets (5,5,5,5));
   		pane.add(titl, 1, 0);   // add tilt to the pane 
   		pane.add(label, 0, 1);  // add label to the pane 
   		pane.add(model, 0, 2);  // add model to the pane 
   		pane.add(color, 0, 3);  // add colour to the pane 
   		pane.add(year, 0, 4);   // add year to the pane 
   		pane.add(price, 0, 5);   // add price to the pane 
   		
   		pane.add(comboBox, 1, 1);    // add comboBox to the pane 
   		pane.add(modeltext, 1, 2);     // add model text to the pane 
   		pane.add(colortext, 1, 3);    // add colour text to the pane 
   		pane.add(yeartext, 1, 4);       // add year text to the pane 
   		pane.add(pricetext, 1, 5);     // add price text to the pane 
   		pane.add(hBox, 1, 6);


   		insert.setOnAction(e -> { // the action for the insert method in single list
   		    if (comboBox.getSelectionModel().getSelectedItem() != null) {
   		        String brandname = (String) comboBox.getSelectionModel().getSelectedItem();
   		        brand = new Brand(brandname);
   		        cars = new Cars(modeltext.getText(), colortext.getText(), brandname, Integer.parseInt(yeartext.getText()), Double.parseDouble(pricetext.getText()));
   		    }

   		    if (doubleLis.search(brand).singleList.searchCars(cars)) {
   		        doubleLisForCostamer.search(brand).singleList.insertSort(cars);
   		    } else {
   		        doubleLis.search(brand).singleList.insertSort(cars);
   		        doubleLisForCostamer.search(brand).singleList.insertSort(cars);
   		        //doubleLis.search(brand).singleList.delete(cars);

   		    }
   		});

   		back.setOnAction(e->{  // action to return in back stage 
   			adminStage ();
   		});
   		
   		 
   		 
   		 return pane ;
   	 }
   	 public void printCarslist1() {
		 Double_Node curr ;
		 
		 for (curr = doubleLisForCostamer.getFirst() ; curr != null ; curr = curr.getNext()) {
			 for (Single_Node curr1 = curr.getSingleList().getFirst();curr1!=null;curr1=curr1.getNext()) {
				 System.out.println(curr1.getData());
			 }
	 }
}
   	 
   	public Pane deletetNewCars(){  // This method to remove cars from single list
   	 GridPane pane = new GridPane ();  // create a Grid pane 
		 Label label = new Label ("choose Brand");  // Create new label 
		 Label titl = new Label ("Car Informations : ");

		 // create four label 
		 Label model = new Label ("Car Model ");   
		 Label color = new Label ("Car Color");
		 Label year = new Label ("Car Year");
		 Label price = new Label ("Car Price");
		 Button Delete = new Button ("Delete");
		 Button back = new Button ("Back");

		 // create four text filed 
	TextField modeltext = new TextField();
	TextField colortext = new TextField();
	TextField yeartext = new TextField();
	TextField pricetext = new TextField();
	

	titl.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
	titl.setStyle("-fx-text-fill:blue");// set colour to the label font 
		 

	// set front and colour  for  the label 

	label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
	label.setStyle("-fx-text-fill:red");// set colour to the label font 
		 
	// set front and colour  for all the label 
	model.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	model.setStyle("-fx-text-fill:black");// set colour to the label font 
	color.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	color.setStyle("-fx-text-fill:black");// set colour to the label font 
	year.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	year.setStyle("-fx-text-fill:black");// set colour to the label font 
	price.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	price.setStyle("-fx-text-fill:black");// set colour to the label font 
		 

		 HBox hBox = new HBox(20);
		 
		 hBox.setAlignment(Pos.CENTER);
		 hBox.getChildren().addAll(Delete , back);
		 
		 // add All label and text Field to the pane 
		pane.setHgap(30);
		pane.setVgap(30);
		pane.setPadding(new Insets (5,5,5,5));
		pane.add(titl, 1, 0);
		pane.add(label, 0, 1);
		pane.add(model, 0, 2);
		pane.add(color, 0, 3);
		pane.add(year, 0, 4);
		pane.add(price, 0, 5);
		
		pane.add(comboBox, 1, 1);
		pane.add(modeltext, 1, 2);
		pane.add(colortext, 1, 3);
		pane.add(yeartext, 1, 4);
		pane.add(pricetext, 1, 5);
		pane.add(hBox, 1, 6);


		Delete.setOnAction(e->{  // action for delete button 
			if (comboBox.getSelectionModel().getSelectedItem() != null ) {
				String brandname = (String) comboBox.getSelectionModel().getSelectedItem();
				brand = new Brand(brandname);
				// create a new car 
				cars = new Cars(modeltext.getText() ,colortext.getText(),brandname , Integer.parseInt(yeartext.getText()) ,Double.parseDouble(pricetext.getText()));
				doubleLis.search(brand).singleList.delete(cars); // delete from single list 
				doubleLisForCostamer.search(brand).singleList.delete(cars); // delete from single list 
					
				
			}
		});

		back.setOnAction(e->{  // action for the back button to return to the before stage 
			adminStage ();
		});
		
		 
		 
		 return pane ;
   		
   	}
   	 
   	 
   	 public Pane ReportScreen () {  // this method to the print the top ten element from stack
   		 Stage stage = new Stage ();
   		 CheckBox box1 = new CheckBox("Write in File");
   		 CheckBox box2 = new CheckBox("Text Area");
   		 CheckBox box3 = new CheckBox("Write care File");

   		 Label label = new Label ("The following option can be used to print report :");
   		 HBox hBox = new HBox (20);
   		 GridPane pane = new GridPane ();

   		pane.setPadding(new Insets (5 ,5,5,5));
   		 
   		// add front and colour to the label
   		label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20));
		label.setStyle("-fx-text-fill:red");// set colour to the label font 

   		// add front and colour to the box
   		box1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   		box2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   		box3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   		hBox.setAlignment(Pos.CENTER);
   		hBox.getChildren().addAll(box1 , box2 , box3);
   		 

   		// add all control to the pane 
   		pane.setHgap(10);
   		pane.setVgap(10);
   		pane.setPadding(new Insets (5,5,5,5));
   		pane.add(label, 0, 0);
   		pane.add(hBox, 0, 3);

   		 
   		box2.setOnAction(e->{
   			if (box2.isSelected())
   				printStack();
   		});
   		box1.setOnAction(e->{
   			if (box1.isSelected()) {
   				writeFile(stage);
   			}
   		});
   		 
   		box3.setOnAction(e->{
   			if (box3.isSelected())
   				writeCareFile(stage);
   		});
   		 return pane ;
   	 }
   	 
   	 
   	 
	 
   	 public Pane ClientScreen () {
   		 Stage stage = new Stage ();
   		 BorderPane pane = new BorderPane ();
   		 Button show = new Button ("Show");
   		 Button Buy = new Button ("Buy");
   		 Button Back = new Button ("Back");

   		 Label label = new Label ("Welcome to the car agency");
   		 HBox hBox = new HBox (15);
   		 VBox vBox = new VBox (20);
   		 /*
   		Image image1 = new Image("C:\\Users\\Lenovo\\Desktop\\blackCar2.jpg"); // add image to the scene 
		ImageView imageView = new ImageView(image1);
		imageView.setFitWidth(600);
		imageView.setFitHeight(400);
 		*/

		vBox.getChildren().addAll(label  , hBox);
		vBox.setAlignment(Pos.CENTER);

   		show.setPrefWidth(100);
   		Buy.setPrefWidth(100);
   		Back.setPrefWidth(100);
   		label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20));
		label.setStyle("-fx-text-fill:black");// set colour to the label font 
		
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(show , Buy , Back);
		
		pane.setCenter(vBox);
		pane.setStyle("-fx-background-color: lightcyan ");
		
		Scene scene = new Scene(pane , 850 ,800);
		stage.setScene(scene);
		stage.setTitle("Client Stage");
		stage.show();
   		 
		show.setOnAction(e->{
			showCarsInAgency () ;
		});
		
		Buy.setOnAction(e->{
			buyCars();
		});
		
		Back.setOnAction(e->{
			firstScreen();
		});
   		 return pane ;
   		 
   		 
   	 }
   	 
  
	
   	 
   	 
	 public void buyCars() {
		 Stage stage = new Stage ();
		 BorderPane pane = new BorderPane ();
		 
		 Label label = new Label ("Customer Name");
		 Label label2 = new Label ("Customer Mobile");
		 Label label3 = new Label ("Customer Information ");

		 Image image1 = new Image("C:\\Users\\Lenovo\\Desktop\\user.png"); // add image to the scene 
		 ImageView imageView = new ImageView(image1);
			
		 TextField text1 = new TextField();
		 TextField text2 = new TextField();

		 HBox hBox1 = new HBox(15);
		 HBox hBox2 = new HBox(15);
		 HBox hBox3 = new HBox(30);
		 VBox vBox = new VBox(50);

		 
		 Button logIn = new Button("Login ");
		 Button logOut = new Button("LogOut ");
		 
		 logIn.setFont(Font.font ("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		 logOut.setFont(Font.font ("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));

		 

		 imageView.setFitWidth(300);
		 imageView.setFitHeight(250);
			
		 label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		 label.setStyle("-fx-text-fill:black");// set colour to the label font 
		 
		 
		 label2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		 label2.setStyle("-fx-text-fill:black");// set colour to the label font 

	 
		 
		 label3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30));
		 label3.setStyle("-fx-text-fill:blue");// set colour to the label font 



		 
		 hBox1.setAlignment(Pos.CENTER);
		 hBox1.getChildren().addAll(label , text1);
		 
		 hBox2.setAlignment(Pos.CENTER);
		 hBox2.getChildren().addAll(label2 , text2);
		 
		 hBox3.setAlignment(Pos.CENTER);
		 hBox3.getChildren().addAll(logOut , logIn);
		 
		 vBox.setAlignment(Pos.CENTER);
		 vBox.getChildren().addAll(label3 , hBox1 , hBox2 , hBox3 ) ;
		 
		 pane.setStyle("-fx-background-color: lightskyblue  ");
		 pane.setCenter(vBox);
		 pane.setLeft(imageView);
		 
		 Scene scene = new Scene (pane , 850 ,800);
		 stage.setScene(scene);
		 stage.setTitle("Customer Information ");
		 stage.show();
		 
		 logOut.setOnAction(e->{
			 ClientScreen ();
		 });
		 
		 logIn.setOnAction(e -> {
			    if (!text1.getText().isEmpty() && !text2.getText().isEmpty()) {
			        CostemerNAme = text1.getText();
			        CostemerNumber = Integer.parseInt(text2.getText());
			        tasbelview();
			    } else {
			    	
			    }
			});
		 
	 }
	 
	 
	 
   	
	 public void showCarsInAgency () { // this method show All cars in Agency 
  	     Stage stage = new Stage(); // create new Stage 
  	     GridPane pane = new GridPane (); // Create a grid pane 
		 Button back = new Button ("Back"); // this Button to return in first scene
         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));

	   StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save Martyr information 
  	   TextArea textArea = new TextArea(); // create a text Area
  	   
  	 textArea.setPrefWidth(600);
  	textArea.setPrefHeight(500);
  	  
  	pane.setStyle("-fx-background-color: lightsteelblue ");
  	 pane.setAlignment(Pos.CENTER); // put the grid pane to the centre of scene
	 pane.add(textArea, 0, 0);
	 pane.add(back, 3, 7);

	    Double_Node curr ; // Double Node 
		Single_Node curr2; // single Node 
		
	/// the you 
		
		for (curr = doubleLis.getFirst() ; curr != null ; curr = curr.getNext()) { // for loop to Spins in double list
			if (curr.getSingleList().getFirst() == null) {
				 stringBuilder.append(curr.getData().getCarBrand()+" : \n");
			}
			else {
			for (curr2 = curr.singleList.getFirst() ;curr2 != null ; curr2 = curr2.getNext()) { // for loop to Spins in single list
					 stringBuilder.append(curr2.getData().toString()).append("\n"); // append string to stringBuilder
				}
			}
			 stringBuilder.append("****************************\n"); // append string to stringBuilder

				   textArea.setText(stringBuilder.toString()); // print all information to Text Area
		     		textArea.setFont(newFont);
			}
		
		
		
		 Scene scene = new Scene(pane , 850 , 800); // Create a Scene 
		 stage.setScene(scene); // add a scene to the stage 
		 stage.setTitle("Report for Location "); // Add title to the stage 
		 stage.show();  // show the stage 
		 
		 back.setOnAction(e->{
			 ClientScreen ();

		 });
	 }
	 
	 
	 
	 
	 public  Pane updateNewCars() {   // this method to update care information 
		 GridPane pane = new GridPane ();   // create a new Grid pane 
		 Label label = new Label ("choose Brand");   // Create  a new label 
		 Label titl = new Label ("Car Informations : "); // Create  a new label 

		 
		 // create 8 label for old data and new data
		 Label model = new Label ("Car Model ");
		 Label color = new Label ("Car Color");
		 Label year = new Label ("Car Year");
		 Label price = new Label ("Car Price");
		 Label newmodel = new Label ("New Car Model ");
		 Label newcolor = new Label ("New Car Color");
		 Label newyear = new Label ("New Car Year");
		 Label newprice = new Label ("New Car Price");
		 
		 // create two button 
		 Button Updat = new Button ("Updat");
		 Button back = new Button ("Back");

		 // create 8 Text Field for old data and new data
	TextField modeltext = new TextField();
	TextField colortext = new TextField();
	TextField yeartext = new TextField();
	TextField pricetext = new TextField();
	
	TextField newmodeltext = new TextField();
	TextField newcolortext = new TextField();
	TextField newyeartext = new TextField();
	TextField newpricetext = new TextField();
	
	

	titl.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
	titl.setStyle("-fx-text-fill:blue");// set colour to the label font 
		 

   // set font and colour to all labels
	label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
	label.setStyle("-fx-text-fill:red");// set colour to the label font 
		 
	model.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	model.setStyle("-fx-text-fill:black");// set colour to the label font 
	color.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	color.setStyle("-fx-text-fill:black");// set colour to the label font 
	year.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	year.setStyle("-fx-text-fill:black");// set colour to the label font 
	price.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	price.setStyle("-fx-text-fill:black");// set colour to the label font 
	
	newmodel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newmodel.setStyle("-fx-text-fill:red");// set colour to the label font 
	newcolor.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newcolor.setStyle("-fx-text-fill:red");// set colour to the label font 
	newyear.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newyear.setStyle("-fx-text-fill:red");// set colour to the label font 
	newprice.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newprice.setStyle("-fx-text-fill:red");// set colour to the label font 
	
		 

		 HBox hBox = new HBox(20);
		 
		 hBox.setAlignment(Pos.CENTER);
		 hBox.getChildren().addAll(Updat , back);
		 
		 // add all control to the pane 
		pane.setHgap(10);
		pane.setVgap(30);
		pane.setPadding(new Insets (5,5,5,5));
		pane.add(titl, 1, 0);
		pane.add(label, 0, 1);
		pane.add(model, 0, 2);
		pane.add(color, 0, 3);
		pane.add(year, 0, 4);
		pane.add(price, 0, 5);
		
		pane.add(comboBox, 1, 1);
		pane.add(modeltext, 1, 2);
		pane.add(colortext, 1, 3);
		pane.add(yeartext, 1, 4);
		pane.add(pricetext, 1, 5);
		pane.add(hBox, 1, 6);
		
		pane.add(newmodel, 2, 2);
		pane.add(newcolor, 2, 3);
		pane.add(newyear, 2, 4);
		pane.add(newprice, 2, 5);
		
		pane.add(newmodeltext, 3, 2);
		pane.add(newcolortext, 3, 3);
		pane.add(newyeartext, 3, 4);
		pane.add(newpricetext, 3, 5);

		


		


		Updat.setOnAction(e -> {  // action for update method 
		    if (comboBox.getSelectionModel().getSelectedItem() != null) {
		        String brandname = (String) comboBox.getSelectionModel().getSelectedItem();
		         brand = new Brand(brandname);
		        Cars oldData = new Cars(modeltext.getText(), colortext.getText(), brandname, Integer.parseInt(yeartext.getText()),Double.parseDouble(pricetext.getText()));
		        Cars newData = new Cars(newmodeltext.getText(), newcolortext.getText(), brandname, Integer.parseInt(newyeartext.getText()),Double.parseDouble(newpricetext.getText()));
		        
		        doubleLis.search(brand).singleList.update(oldData, newData);
		    }
		});

		back.setOnAction(e->{  // action to return to the before screen 
			adminStage ();
		});
		
		 
		 
		 return pane ;
   		
	 }
 
	 
	 
	 
	 public void printStack() {  // this method to print last ten order from stack
		 Stage stage = new Stage(); // create new Stage 
  	     GridPane pane = new GridPane (); // Create a grid pane 
		 Button back = new Button ("Back"); // this Button to return in first scene
         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));

	     StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save Martyr information 
  	     TextArea StackArea = new TextArea(); // create a text Area
  	   
  	   StackArea.setPrefWidth(600);
  	   StackArea.setPrefHeight(500);
  	  
     	 pane.setStyle("-fx-background-color: lightsteelblue ");
    	 pane.setAlignment(Pos.CENTER); // put the grid pane to the centre of scene
	     pane.add(StackArea, 0, 0);
	     pane.add(back, 3, 7);		
	     int count = 0;
		 while (count < 10) {
		 
		 if (!stack.isEmpty()) {
			 Order element = stack.pop();
			 element.setOrderStatus("Finshed");
			 stringBuilder.append(element.toString()+"\n");
			 stringBuilder.append("----------------------------------------------------------------------------------------\n");
			 tempstack.push(element);
			 }
		 
		
		 count++;
		 
		 back.setOnAction(e->{
			 adminStage ();
			 
		 });
	 }
		 StackArea.setText(stringBuilder.toString()); // print all information to Text Area
		 StackArea.setFont(newFont);
		 
		 while (!tempstack.isEmpty()) {
			 stack.push(tempstack.pop());
		 }
		 
		 Scene scene = new Scene (pane ,850,800);
		 stage.setScene(scene);
		 stage.setTitle("Stack Reprt");
		 stage.show();
	 }
	 
	 
	 
	 
	 
	 	 
	 public void readCustomerOrder() {  // this method to make the admit read the order again  
	 while (!queue.isEmpty()) {
			 order = queue.dequeue();
			 
			 String M = order.getModel();			 
			 String C = order.getColor();
			 String B = order.getCarBrand();
			 int Y = order.getYear();
			 double P = order.getPrice();
			 
			 cars = new Cars(M,C,B,Y,P);
			 brand = new Brand(B);

			 if(doubleLisForCostamer.search(brand) != null) { // if the order is found push order to the stack
				 if (doubleLisForCostamer.search(brand).singleList.search(M, C, B, Y, P)) {
					 stack.push(order);
					 doubleLisForCostamer.search(brand).singleList.delete(cars);
				 }
				 
				 else   // if the order not found return order to the queue
					 Tempqueue.enqueue(order);		
	 }		 
	 }
	 
	 
	 
	 while (!Tempqueue.isEmpty()) {  // reset the queue
		 queue.enqueue(Tempqueue.dequeue());
	 }
	 }
		
	 
	 
	 
	 
	 
	 public void printQueue () {  // this method to print the order from the queue in text aear
			 Stage stage = new Stage(); // create new Stage 
	  	     GridPane pane = new GridPane (); // Create a grid pane 
			 Button back = new Button ("Back"); // this Button to return in first scene
	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));

		     StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save Martyr information 
	  	     TextArea StackArea = new TextArea(); // create a text Area
	  	   
	  	   StackArea.setPrefWidth(600);
	  	   StackArea.setPrefHeight(500);
	  	  
	     	 pane.setStyle("-fx-background-color: lightsteelblue ");
	    	 pane.setAlignment(Pos.CENTER); // put the grid pane to the centre of scene
		     pane.add(StackArea, 0, 0);
		     pane.add(back, 3, 7);		
		
			 
			 while (!queue.isEmpty()) {
				 Order element = queue.dequeue();
				 element.setOrderStatus("InProcess");
				 stringBuilder.append(element.toString()+"\n");
				 stringBuilder.append("----------------------------------------------------------------------------------------\n");
				 Tempqueue.enqueue(element);
				 }
			 
			
			 
			 back.setOnAction(e->{  // action back to return to the before  screen  
				 adminStage ();
				 
			 });
			 
			 StackArea.setText(stringBuilder.toString()); // print all information to Text Area
			 StackArea.setFont(newFont);
			 
			 while (!Tempqueue.isEmpty()) {
				 queue.enqueue((Tempqueue.dequeue())); 
			 }
			 
			 Scene scene = new Scene (pane ,850,800);
			 stage.setScene(scene);
			 stage.setTitle("Stack Reprt");
			 stage.show();
		 }
	 
	 
	 
	 
	 
	 
	 public void tasbelview() { // This method to show the Cars In a Table View for costumers
		  Stage stage = new Stage ();  // Create a new stage 
	      TableView <Cars>table = new TableView<>();  // Create a TableView
	      Cars[] selectedCar = new Cars[1];	
	      Button Cancel = new Button ("Cancel");
	      Button Later = new Button ("Later");
	      Button Thanks = new Button ("Thanks");

	     
	     //create four Buttons 
	     Button Buys = new Button ("Buy");
	     Button Back = new Button ("Back");
	     Button Filter = new Button ("Filter");
	     Button Search = new Button ("Search");
	     
	     // Create four labels
	     Label ModelLabel = new Label("Model");
	     Label ColorLabel = new Label("Color");
	     Label YearLabel = new Label("Year");
	     Label PriceLabel = new Label("Price");
	     Label Errorlabel = new Label ("Your order is currently not available ");
	     Label Suclabel = new Label ("Your order is ready");
	     
	     
	     Errorlabel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	     Errorlabel.setStyle("-fx-text-fill:red");// set colour to the label font 
	     
	     Suclabel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	     Suclabel.setStyle("-fx-text-fill:red");// set colour to the label font 
	 		     
	     //Create four Text Fields
	     TextField ModelText = new TextField();
	     TextField ColorText = new TextField();
	     TextField YearText = new TextField();
	     TextField PriceText = new TextField();
	     
	     // Create four VBox
	     VBox Box1 = new VBox(10);
	     VBox Box2 = new VBox(10);
	     VBox Box3 = new VBox(10);
	     VBox Box4 = new VBox(10);
	     VBox Box5 = new VBox(10);

	     HBox ButtonBox = new HBox(30);
	     HBox ButtonBox0 = new HBox(30);

	     ButtonBox.getChildren().addAll(Cancel , Later);
	     ButtonBox0.getChildren().addAll(Suclabel , Thanks);

	     // Add All labels and text fields to the VBox
	     Box1.getChildren().addAll(ModelLabel,ModelText);
	     Box2.getChildren().addAll(ColorLabel,ColorText);
	     Box3.getChildren().addAll(YearLabel,YearText);
	     Box4.getChildren().addAll(PriceLabel,PriceText);
	     Box5.getChildren().addAll(Errorlabel,ButtonBox);
	     Search.setVisible(false);
	     Box5.setVisible(false);
	     

	     HBox hBox1 = new HBox(20);
	     hBox1.getChildren().addAll(Box1,Box2,Box3,Box4); // Add all VBox to the HBox 
	     hBox1.setVisible(false);
	     ButtonBox0.setVisible(false);


	     HBox hBox = new HBox (15);
	     hBox.getChildren().addAll(Back ,Search ,Buys ); // Add All button to the HBox
	     
	    
			
		    comboBox.setOnAction(e -> {  // put the ComboBox in the action 
		        String brandname =  comboBox.getSelectionModel().getSelectedItem();
		        Brand brand = new Brand(brandname);
		        ArrayList<Cars> arrayCars = new ArrayList<>();  // create a ArrayList to save the cars 
		        Double_Node searchResult = doubleLis.search(brand);

		        for (Single_Node curr = searchResult.getSingleList().getFirst(); curr != null; curr = curr.getNext()) {
		        	Cars car = curr.getData();
		            arrayCars.add(car);
		        }

		        ObservableList<Cars> DataCars = FXCollections.observableArrayList(arrayCars); // add the ArrayList to the  ObservableList
		        table.setItems(DataCars);

		    });
		    
		    // Create four Column in TableView
	        TableColumn<Cars, String> columnBrand = new TableColumn<>("Brand");  // first column with brand 
	        TableColumn<Cars, String> columnModel = new TableColumn<>("Model");  // second column with Model
	        TableColumn<Cars, String> columnColor = new TableColumn<>("Color");     // third column with Colour
	        TableColumn<Cars, Integer> columnYear = new TableColumn<>("Year");      // fourth column with year
	        TableColumn<Cars, String> columnPrice = new TableColumn<>("Price");     // five column with Price

	        // set width for all column
	        columnBrand.setPrefWidth(150);
	        columnModel.setPrefWidth(130);
	        columnColor.setPrefWidth(130);
	        columnYear.setPrefWidth(130);
	        columnPrice.setPrefWidth(130);

	        table.getColumns().addAll(columnBrand ,columnModel, columnColor, columnYear, columnPrice);//add column to the TableView
			

	        columnBrand.setCellValueFactory(new PropertyValueFactory<>("carBrand")); 
	        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
	        columnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
	        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
	        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

	       // table.setItems(DataCars);
	        
	        
	        
	        GridPane root = new GridPane(); 
	        root.add(comboBox, 1, 0);
	        root.setHgap(15);
	        root.setVgap(15);
	        root.add(table, 1, 1);
	        root.add(hBox1, 1, 3);
	        root.add(Filter, 1, 2);
	        root.add(hBox, 1, 4);
	        root.add(Box5, 1, 9);
	        root.add(ButtonBox0, 1, 9);		
	        root.setStyle("-fx-background-color: lightcyan ");

	        


	       
	        
	        Back.setOnAction(e->{  // return to the Client Screen
	        	ClientScreen ();
	        });
	        
	        Filter.setOnAction(e->{  // change data in table
	        	hBox1.setVisible(true);
	        	Search.setVisible(true);
	        });
	        
	        Search.setOnAction(e -> {
	            String enteredModel = ModelText.getText().trim().toLowerCase();   // model you write in text Field 
	            String enteredColor = ColorText.getText().trim().toLowerCase();   // Colour you write in text Field 
	            String enteredYear = YearText.getText().trim().toLowerCase();    // year you write in text Field 
	            String  enteredPrice = PriceText.getText().trim().toLowerCase();     // Price you write in text Field 

	            ArrayList<Cars> filteredCars = new ArrayList<>();  // create an array list

	            for (Double_Node curr1 = doubleLis.getFirst(); curr1 != null; curr1 = curr1.getNext()) {
	                for (Single_Node curr2 = curr1.getSingleList().getFirst(); curr2 != null; curr2 = curr2.getNext()) {
	                    Cars car = curr2.getData();

	                    String model = car.getModel().toLowerCase(); // get model from object car
	                    String color = car.getColor().toLowerCase();  // get colour from object car
	                    String year = String.valueOf(car.getYear()).toLowerCase();  // get year from object car
	                    int year1 = car.getYear();
	                    String price = String.valueOf(car.getPrice()).toLowerCase();  // get price from object car
	                    double price1 = car.getPrice();

	                    boolean modelMatch = enteredModel.isEmpty() || model.contains(enteredModel);
	                    boolean colorMatch = enteredColor.isEmpty() || color.contains(enteredColor);
	                    boolean yearMatch = enteredYear.isEmpty() || year.contains(enteredYear);
	                    //boolean priceMatch = enteredPrice.isEmpty() || price.contains(enteredPrice);
	                    boolean priceMatch1 = enteredPrice.isEmpty() || Double.parseDouble(price) <= Double.parseDouble(enteredPrice);

	                    if (modelMatch && colorMatch && yearMatch && priceMatch1) {
	                        filteredCars.add(car);
	                    }
	                }
	            }

	            ObservableList<Cars> filteredData = FXCollections.observableArrayList(filteredCars); // add array list to the ObservableList
	            table.setItems(filteredData);  // add ObservableList to the table
	        });
	        
	        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> { // this action to 
	            if (newSelection != null) {   // if you select row                                                                  //select row from table 
	                selectedCar[0] = newSelection;  // first index in car array 
	            }
	        });
	        
	        Buys.setOnAction(e -> {  // this action to buy a car 
	            if (selectedCar[0] != null) { // if you select action 
	                String selectedBrand = selectedCar[0].getCarBrand();   // get brand from row you selected
	                String selectedModel = selectedCar[0].getModel();       // get Model from row you selected
	                String selectedColor = selectedCar[0].getColor();       // get Colour from row you selected
	                int selectedYear = selectedCar[0].getYear();           // get Year from row you selected
	                double selectedPrice = selectedCar[0].getPrice();      // get Price from row you selected
	                Date date = new Date();

	                 brand = new Brand(selectedBrand);
	                 cars = new Cars(selectedModel, selectedColor, selectedBrand, selectedYear, selectedPrice);
	                 order = new Order(selectedModel, selectedColor, selectedBrand, selectedYear, selectedPrice, CostemerNAme, date, CostemerNumber,"InProcess");

	                Double_Node brandNode = doubleLisForCostamer.search(brand);
	                if ( brandNode != null) {
	                    if ( brandNode != null&& brandNode.getSingleList().searchCars(cars)) {
		                	System.out.println("Yes");
		                    order.setOrderStatus("Finshed");
		                    stack.push(order);
		                    brandNode.getSingleList().delete(cars);
		                    ButtonBox0.setVisible(true);

		                } 
		                
		                else {
		                    Box5.setVisible(true);
		                }                   
	                }
	            }
	        });

	        
	        Cancel.setOnAction(e->{
	        	ClientScreen ();

	        });
	        Later.setOnAction(e->{
                order.setOrderStatus("InProcess");

	        	queue.enqueue(order);
	        });
	        Thanks.setOnAction(e->{
	        	ClientScreen ();

	        });
	        
	        Scene scene = new Scene(root, 850, 800);

	        stage.setTitle("JavaFX TableView");
	        stage.setScene(scene);
	        stage.show();
	 }
	 
	 
	 
	 
	 
	 public void writeFile(Stage primaryStage) {  // this method to print all information from stack and queue in file 
		    // Choose the file to write the new sorted data inside it
		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Save File");
		    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		    File file = fileChooser.showSaveDialog(primaryStage);

		    if (file != null) { // Check if a file was selected
		        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
		            Order writeOrder;

		            // Write data from the stack
		            while (stack != null && !stack.isEmpty()) {
		                writeOrder = stack.pop();
		                if (writeOrder != null) {
		                    writer.println(writeOrder.getCostamerName() + "," + writeOrder.getCostamerMobil() + "," +
		                            writeOrder.getCarBrand() + "," + writeOrder.getModel() + "," +
		                            writeOrder.getYear() + "," + writeOrder.getPrice() + "," + writeOrder.getOrderStatus());
		                    tempstack.push(writeOrder);
		                }
		            }

		            // Restore the original stack
		            while (!tempstack.isEmpty()) {
		                stack.push(tempstack.pop());
		            }

		            // Write data from the queue
		            while (queue != null && !queue.isEmpty()) {
		                writeOrder = queue.dequeue();
		                if (writeOrder != null) {
		                    writer.println(writeOrder.getCostamerName() + "," + writeOrder.getCostamerMobil() + "," +
		                            writeOrder.getCarBrand() + "," + writeOrder.getModel() + "," +
		                            writeOrder.getYear() + "," + writeOrder.getPrice() + "," + writeOrder.getOrderStatus());
		                    Tempqueue.enqueue(writeOrder);
		                }
		            }

		            // Restore the original queue
		            while (!Tempqueue.isEmpty()) {
		                queue.enqueue(Tempqueue.dequeue());
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	 
	 
	 
	 public void writeCareFile(Stage primaryStage) {  // this method to print all information from stack and queue in file 
		    // Choose the file to write the new sorted data inside it
		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Save File");
		    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		    File file = fileChooser.showSaveDialog(primaryStage);

		    if (file != null) { // Check if a file was selected
		        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
		        	Double_Node curr1;
		        	Single_Node curr2;
		        	for (curr1 = doubleLis.getFirst() ; curr1!=null ; curr1=curr1.getNext()) {
		        		for (curr2 = curr1.singleList.getFirst();curr2!=null ; curr2 = curr2.getNext()) {
		        			writer.println(curr2.getData().toString());
		        		}
		        	}
		           
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	 
	 
	 
	 
	 
	 
}