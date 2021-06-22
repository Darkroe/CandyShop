package candyShop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class clientGUI extends JFrame implements ActionListener, ChangeListener {
	
	// Create The Client's Shopping Bag Object
	shoppingBag<Object> clientBag = new shoppingBag<Object>();
	
	// Create The Store's Client Checkout Line Object
	checkOutLine<Object> clientLine = new checkOutLine<Object>();
	
	// Create the 
	storePriceCalculator profitAmount = new storePriceCalculator();
	
	private boolean isEditable = true;
	private int fieldWidth = 20;
	
	private int topPadding = 10;
	private int leftPadding = 10;
	private int bottomPadding = 10;
	private int rightPadding = 10;
	
	// Create JLabel variables
	private JLabel nameLabel, priceLabel, candyNumLabel, candyBagLabel, candyListLabel, addCandyLabel;
	private JLabel removeCandyLabel;
	private JLabel removeSpecificCandyLabel;
	
	private JLabel clientNameLabel;
	private JLabel currClientLabel;
	private JLabel checkOutLabel;
	private JLabel addClientLabel;
	private JLabel clientLineLabel;
	private JLabel totalPriceLabel;
	private JLabel totalProfitsLabel;
	
	// Create JTextField variables
	private JTextField nameField;
	private JTextField priceField;
	
	private JTextField candyButtonField;
	private JTextField removeCandyButtonField;
	private JTextField removeSpecificCandyButtonField;
	private JTextField rscbMessageField;
	private JTextField clientButtonField;
	
	private JTextField clientNameField;
	private JTextField currClientField;
	private JTextField removeClientButtonField;
	private JTextField checkoutField;
	private JTextField profitField;
	
	// Create JTextArea variable
	private JTextArea candyList;
	private JTextArea candyBag;
	private JTextArea checkoutLine;
	
	// Create JSpinner variable
	private JSpinner numSpinner;
	
	// Create JScrollPane variable
	private JScrollPane scrollBar;
	private JScrollPane scrollBarBag;
	private JScrollPane scrollBarLine;
	
	// Create JButton variables
	private JButton addCandyButton;
	private JButton addClientButton;
	private JButton removeCandyButton;
	private JButton removeSpecificCandyButton;
	private JButton removeClientButton;
	
	clientGUI() throws IOException{
		
		// Create variables for JSpinner
		int initNum = 0;
		int minNum = 0;
		int maxNum = 7;
		int stepVal = 1;
		
		// Setup File Reading
		FileInputStream storeName = new FileInputStream("storeName.txt");
		Scanner fileScan = new Scanner(storeName);
		
		// Setup File Writing
		FileOutputStream clientReceipts = new FileOutputStream("receipts.txt");
		PrintWriter writeReceipts = new PrintWriter(clientReceipts);
		
		// Read Store Name From File
		String nameOfStore = fileScan.nextLine();
		fileScan.close();
		
		GridBagConstraints guiConstruct = null;
		
		// Set GUI Title
		setTitle(nameOfStore + "  Client Line & Client Shopping Bag");
		
		// Construct name and price labels
		nameLabel = new JLabel("Candy Selected:");
		priceLabel = new JLabel("Candy Price:");
		candyNumLabel = new JLabel("Candy Number:");
		candyBagLabel = new JLabel("Candy Bag");
		candyListLabel = new JLabel("Candy List");
		addCandyLabel = new JLabel("Add to Bag");
		removeCandyLabel = new JLabel("Remove First Item in Bag");
		removeSpecificCandyLabel = new JLabel("Remove Specific Candy");
		
		clientNameLabel = new JLabel("New Client Name");
		currClientLabel = new JLabel("Current Client");
		checkOutLabel = new JLabel("Check Out Current Client");
		addClientLabel = new JLabel("Add Customer");
		clientLineLabel = new JLabel("Checkout Line");
		totalPriceLabel = new JLabel("Checkout Price Total");
		totalProfitsLabel = new JLabel("Total Store Profits:");
		
		// Construct spinner model
		SpinnerNumberModel spinnerModel = null;
		
		// Construct JSpinner GUI
		spinnerModel = new SpinnerNumberModel(initNum, minNum, maxNum, stepVal);
		numSpinner = new JSpinner(spinnerModel);
		numSpinner.addChangeListener(this);
		
		// Construct "Candy Selected" text field
		nameField = new JTextField(fieldWidth);
		nameField.setText("Sweet Tarts");
		nameField.setEditable(!isEditable);
		
		// Construct "Candy Price" text field
		priceField = new JTextField(fieldWidth);
		priceField.setText("Price: $0.87");
		priceField.setEditable(!isEditable);
		
		// Construct Candy Button Message Field
		candyButtonField = new JTextField(fieldWidth);
		candyButtonField.setText("");
		candyButtonField.setEditable(!isEditable);
		
		// Construct Remove Candy Button Message Field
		removeCandyButtonField = new JTextField(fieldWidth);
		removeCandyButtonField.setText("");
		removeCandyButtonField.setEditable(!isEditable);
		
		// Construct Remove Specific Candy Button Edit Field
		removeSpecificCandyButtonField = new JTextField(fieldWidth);
		removeSpecificCandyButtonField.setText("Enter A Candy To Remove");
		removeSpecificCandyButtonField.setEditable(isEditable);
		removeSpecificCandyButtonField.addActionListener(this);
		
		// Construct Remove Specific Candy Message Field
		rscbMessageField = new JTextField(fieldWidth);
		rscbMessageField.setText("");
		rscbMessageField.setEditable(!isEditable);
		
		// Construct Client Button Message Field
		clientButtonField = new JTextField(fieldWidth);
		clientButtonField.setText("");
		clientButtonField.setEditable(!isEditable);
		
		// Construct Remove Client Button Message Field
		removeClientButtonField = new JTextField(fieldWidth);
		removeClientButtonField.setText("");
		removeClientButtonField.setEditable(!isEditable);
		
		// Construct Current Client Field
		currClientField = new JTextField(fieldWidth);
		currClientField.setText("No Current Client");
		currClientField.setEditable(!isEditable);
		
		// Construct "Client Name" text field
		clientNameField = new JTextField(fieldWidth);
		clientNameField.setText("Enter A Name");
		clientNameField.setEditable(isEditable);
		clientNameField.addActionListener(this);
		
		// Construct Client Checkout Price Field
		checkoutField = new JTextField(fieldWidth);
		checkoutField.setText("");
		checkoutField.setEditable(!isEditable);
		
		// Construct Store Profit Field
		profitField = new JTextField(fieldWidth);
		profitField.setText("");
		profitField.setEditable(!isEditable);
		
		// Construct "Add to Bag" button
		addCandyButton = new JButton("Add Candy");
		addCandyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Operation For If Adding The Candy Was Successful
				if (clientBag.add(nameField.getText())) {
					
					// Message To Tell User Adding Candy Was Successful
					candyButtonField.setText("Candy Added To Bag");
					
					// Adds Added Candy Name To Candy Bag Text Area
					candyBag.append(nameField.getText() + "\n");
				}
				
				// Displays Error Message To User If Candy Bag Is Full
				else {
					candyButtonField.setText("Bag Full");
				}
			}
		});
		
		// Construct "Remove Candy" Button
		// Removes the candy at the top of the candy bag list
		removeCandyButton = new JButton("Remove Candy");
		removeCandyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Operation For If The Candy Removal Was Successful
				if (clientBag.remove() != null) {
					
					// Message To Tell User Removing Candy Was Successful
					removeCandyButtonField.setText("Candy Removed From Bag");
					
					// Resets Candy Bag Text Area, Then Refills It With Remaining Candies
					candyBag.setText("");
					for (int i = 0; i < clientBag.getSize(); i++) {
						if (clientBag.getElement(i) != null) {
							candyBag.append(clientBag.getElement(i).toString() + "\n");
						}
					}
				}
				
				// Displays Error Message To User If Candy Bag Is Empty
				else {
					removeCandyButtonField.setText("Bag Empty");
				}
			}
		});
		
		// Construct "Remove Specific Candy" Button
		// Removes all candy of a single name
		removeSpecificCandyButton = new JButton("Remove Specific Candy");
		removeSpecificCandyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Operation For If The Candy Removal Was Successful
				if (clientBag.remove(removeSpecificCandyButtonField.getText())) {
					
					// Message To Tell User Removing Candies Was Successful
					rscbMessageField.setText("Specified Candies Removed");
					
					// Resets Candy Bag Text Area, Then Refills It With Remaining Candies
					candyBag.setText("");
					for (int i = 0; i < clientBag.getSize(); i++) {
						if (clientBag.getElement(i) != null) {
							candyBag.append(clientBag.getElement(i).toString() + "\n");
						}
					}
				}
				
				// Displays Error Message To User If Candy Name Is Misspelled, If The Candy Is Not In Their Bag, Or If The Bag In Empty
				else {
					rscbMessageField.setText("Bag Empty | Misspelling | Not In Bag");
				}
			}
		});
		
		// Construct "Add to Line" button
		addClientButton = new JButton("Add To Line");
		addClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ensures A Blank String Cannot Be Inputed As A Client
				if (clientNameField.getText().equals("")) {
					clientButtonField.setText("Please Enter A Name");
				}
				
				// Tells User If Client Was Successfully Added To The Line Or Not
				else {
					if (clientLine.add(clientNameField.getText())) {
						clientButtonField.setText("Client Added To Line");
						checkoutLine.append(clientNameField.getText() + "\n");
					}
					else {
						clientButtonField.setText("Line Is Full");
					}
					
					if (clientLine.getLineSize() == 1) {
						currClientField.setText(clientNameField.getText());
					}
				}
			}
		});
		
		// Construct "Remove Client" Button
		removeClientButton = new JButton("Check Out Client");
		removeClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (clientLine.remove()) {
					
					// Initialize Variables For Later Use
					int numCandies;
					double totalCost = 0.0;
					
					// Create The Client's Total Price Paid Object
					    // Done in actionPerformed so that every client has a unique price object
					clientPriceCalculator totalAmount = new clientPriceCalculator();
					
					// Calculate The Client's Total Price Paid On Checkout
					numCandies = clientBag.getNumOf("Sweet Tarts");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Sweet Tarts");
					
					numCandies = clientBag.getNumOf("Fizzi Pops");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Fizzi Pops");
					
					numCandies = clientBag.getNumOf("Karma Tarts");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Karma Tarts");
					
					numCandies = clientBag.getNumOf("Pixie Rods");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Pixie Rods");
					
					numCandies = clientBag.getNumOf("Mike & Ikes");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Mike & Ikes");
					
					numCandies = clientBag.getNumOf("Butterfingers");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Butterfingers");
					
					numCandies = clientBag.getNumOf("Missionaries");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Missionaries");
					
					numCandies = clientBag.getNumOf("Musketeers");
					totalCost = totalCost + totalAmount.totalPrice(numCandies, "Musketeers");
					
					// State How Much Was Spent By The Client Who Just Checked Out
					checkoutField.setText(currClientField.getText() + " spent $" + String.format("%.2f",totalCost));
					
					// Edit The Stores Profit Object And Display Updated Numbers
					profitField.setText("Total Profits: $" + String.format("%.2f", profitAmount.totalProfit(totalCost)));
					
					// Write To A Recipts File How Much Current Client Just Spent
					writeReceipts.println(currClientField.getText() + ": $" + String.format("%.2f", totalCost));
					writeReceipts.flush();
					
					// Sets The Current Client In Line To The Next Client Or Back To A Default If The Line Is Now Empty
					if (clientLine.getLineSize() > 0) {
						currClientField.setText(clientLine.peek().toString());
					}
					else {
						currClientField.setText("No Current Client");
					}
					
					// Tells User That Checkout Was Successful 
					removeClientButtonField.setText("Client Has Been Checked Out");
					
					// Clears The Client's Shopping Bag Object So The Next Client's Shopping Bag Can Start From Scratch
					clientBag.clear();
					
					// Clears The Client's Shopping Bag Text Area
					candyBag.setText("");
					
					// Resets The Checkout Line Text Area, Then Fills It In With The Remaining Clients Still In Line
					checkoutLine.setText("");
					for (int i = 0; i < clientLine.getLineSize(); i++) {
						if (clientLine.getLineElement(i) != null) {
							checkoutLine.append(clientLine.getLineElement(i).toString() + "\n");
						}
					}
				}
				
				// Tells The User That A Client Was Not Checked Out Because There Was No Client
				else {
					removeClientButtonField.setText("The Line Is Empty");
				}
			}
		});
		
		// Construct "Candy List" text area
		candyList = new JTextArea(8, 10);
		candyList.append("Candy Names   :  Candy Number\n");
		candyList.append("=============================\n");
		candyList.append("Sweet Tarts    :  0\n");
		candyList.append("Fizzi Pops       :  1\n");
		candyList.append("Karma Tarts   :  2\n");
		candyList.append("Pixie Rods      :  3\n");
		candyList.append("Mike & Ikes     :  4\n");
		candyList.append("Butterfingers  :  5\n");
		candyList.append("Missionaries  :  6\n");
		candyList.append("Musketeers    :  7\n");
		candyList.setEditable(!isEditable);
		
		// Construct "Candy Bag" text area
		candyBag = new JTextArea(8, 10);
		candyBag.setEditable(!isEditable);
		
		// Construct "Checkout Line" text area
		checkoutLine = new JTextArea(8, 10);
		checkoutLine.setEditable(!isEditable);
		
		// Construct scrollBars for text areas
		scrollBar = new JScrollPane(candyList);
		scrollBarBag = new JScrollPane(candyBag);
		scrollBarLine = new JScrollPane(checkoutLine);
		
		// Set The GUI Layout
		setLayout(new GridBagLayout());
		
	// Set Positions
		
		// Labels -----------------------------------------------------------------------------
		
		// Candy Name Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 0;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(nameLabel, guiConstruct);
		
		// Candy Price Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 1;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(priceLabel, guiConstruct);
		
		// Candy Number Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 2;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(candyNumLabel, guiConstruct);
		
		// Candy Bag List Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 2;
		guiConstruct.gridy = 3;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(candyBagLabel, guiConstruct);
		
		// Candy List Area Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 3;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(candyListLabel, guiConstruct);
		
		// Add Candy Button Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 2;
		guiConstruct.gridy = 0;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(addCandyLabel, guiConstruct);
		
		// Remove Candy Button Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 2;
		guiConstruct.gridy = 1;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeCandyLabel, guiConstruct);
		
		// Remove Specific Candy Button Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 2;
		guiConstruct.gridy = 2;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeSpecificCandyLabel, guiConstruct);
		
		// Client Name Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 5;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(clientNameLabel, guiConstruct);
		
		// Current Client Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 6;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(currClientLabel, guiConstruct);
		
		// Check Out Current Client Label
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 2;
		guiConstruct.gridy = 6;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(checkOutLabel, guiConstruct);
		
		// Add Client Button Label Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 2;
		guiConstruct.gridy = 5;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(addClientLabel, guiConstruct);
		
		// Client Checkout Line Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 7;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(clientLineLabel, guiConstruct);
		
		// Total Checkout Price Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 9;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(totalPriceLabel, guiConstruct);
		
		// Total Store Profits Price Position
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 0;
		guiConstruct.gridy = 10;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(totalProfitsLabel, guiConstruct);
		
		// Text Fields -------------------------------------------------------------------------
		
		// Selected Candy Name Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 1;
		guiConstruct.gridy = 0;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(nameField, guiConstruct);
		
		// Selected Candy Price Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 1;
		guiConstruct.gridy = 1;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(priceField, guiConstruct);
		
		// Add Candy To Bag Button Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 4;
		guiConstruct.gridy = 0;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(candyButtonField, guiConstruct);
		
		// Remove Candy From Bag Button Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 4;
		guiConstruct.gridy = 1;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeCandyButtonField, guiConstruct);
		
		// Remove Specific Candy From Bag Button Input Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 4;
		guiConstruct.gridy = 2;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeSpecificCandyButtonField, guiConstruct);
		
		// Remove Specific Candy From Bag Button Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 4;
		guiConstruct.gridy = 3;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(rscbMessageField, guiConstruct);
		
		// Add Client Button Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 4;
		guiConstruct.gridy = 5;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(clientButtonField, guiConstruct);
		
		// Current Client Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 1;
		guiConstruct.gridy = 6;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(currClientField, guiConstruct);
		
		// Client Name Input Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 1;
		guiConstruct.gridy = 5;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(clientNameField, guiConstruct);
		
		// Remove Client Button Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 4;
		guiConstruct.gridy = 6;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeClientButtonField, guiConstruct);
		
		// Client Checkout Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 1;
		guiConstruct.gridy = 9;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(checkoutField, guiConstruct);
		
		// Total Store Profits Message Field
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 1;
		guiConstruct.gridy = 10;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(profitField, guiConstruct);
		
		// Buttons -----------------------------------------------------------------------------
		
		// Add Candy To Bag Button
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 3;
		guiConstruct.gridy = 0;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(addCandyButton, guiConstruct);
		
		// Add Client To Line Button
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 3;
		guiConstruct.gridy = 5;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(addClientButton, guiConstruct);
		
		// Remove Candy From The Top Of The Bag Button
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 3;
		guiConstruct.gridy = 1;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeCandyButton, guiConstruct);
		
		// Remove All Of A Specific Candy From The Bag Button
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 3;
		guiConstruct.gridy = 2;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeSpecificCandyButton, guiConstruct);
		
		// Remove Client Front The Front Of The Line Button
		guiConstruct = new GridBagConstraints();
		guiConstruct.gridx = 3;
		guiConstruct.gridy = 6;
		guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
		add(removeClientButton, guiConstruct);
		
		// Spinner -----------------------------------------------------------------------------
		
		guiConstruct = new GridBagConstraints();
	    guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
	    guiConstruct.fill = GridBagConstraints.HORIZONTAL;
	    guiConstruct.gridx = 1;
	    guiConstruct.gridy = 2;
	    add(numSpinner, guiConstruct);
	    
	    // Scroll Bar ---------------------------------------------------------------------------
	    
	    // Candy Names / Prices Scroll Bar
	    guiConstruct = new GridBagConstraints();
	    guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
	    guiConstruct.fill = GridBagConstraints.HORIZONTAL;
	    guiConstruct.gridx = 0;
	    guiConstruct.gridy = 4;
	    guiConstruct.gridwidth = 2;
	    add(scrollBar, guiConstruct);
	    
	    // Candy Bag Scroll Bar
	    guiConstruct = new GridBagConstraints();
	    guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
	    guiConstruct.fill = GridBagConstraints.HORIZONTAL;
	    guiConstruct.gridx = 2;
	    guiConstruct.gridy = 4;
	    guiConstruct.gridwidth = 2;
	    add(scrollBarBag, guiConstruct);
	    
	    // Client Line Scroll Bar
	    guiConstruct = new GridBagConstraints();
	    guiConstruct.insets = new Insets(topPadding, leftPadding, bottomPadding, rightPadding);
	    guiConstruct.fill = GridBagConstraints.HORIZONTAL;
	    guiConstruct.gridx = 0;
	    guiConstruct.gridy = 8;
	    guiConstruct.gridwidth = 2;
	    add(scrollBarLine, guiConstruct);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		int candyPrice = (Integer) numSpinner.getValue();
		
		// Candy Prices
		double sweetTartPrice = 0.87;
		double fizziPopPrice = 1.23;
		double karmaTartPrice = 0.64;
		double pixieRodPrice = 1.59;
		double mikeIkePrice = 1.41;
		double butterfingerPrice = 0.72;
		double missionariPrice = 0.69;
		double musketeerPrice = 1.86;
		
		// Sets Selected Candy Name And Price Visible To The User
		switch (candyPrice) {
		case 0:
			nameField.setText("Sweet Tarts");
			priceField.setText("Price: $" + sweetTartPrice);
			break;
		case 1:
			nameField.setText("Fizzi Pops");
			priceField.setText("Price: $" + fizziPopPrice);
			break;
		case 2:
			nameField.setText("Karma Tarts");
			priceField.setText("Price: $" + karmaTartPrice);
			break;
		case 3:
			nameField.setText("Pixie Rods");
			priceField.setText("Price: $" + pixieRodPrice);
			break;
		case 4:
			nameField.setText("Mike & Ikes");
			priceField.setText("Price: $" + mikeIkePrice);
			break;
		case 5:
			nameField.setText("Butterfingers");
			priceField.setText("Price: $" + butterfingerPrice);
			break;
		case 6:
			nameField.setText("Missionaries");
			priceField.setText("Price: $" + missionariPrice);
			break;
		case 7:
			nameField.setText("Musketeers");
			priceField.setText("Price: $" + musketeerPrice);
			break;
		}
	}
}
