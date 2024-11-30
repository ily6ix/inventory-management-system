Inventory Management GUI Application
This project implements a basic inventory management system using Java Swing for the graphical user interface (GUI). It allows users to check the stock availability of products and determine restocking needs based on an inventory file.

Features
User-friendly GUI for entering product details and checking inventory.
Reads product information from a file (Inventory.txt).
Displays stock availability and calculates the number of units required for restocking.
Options to clear input fields or exit the application.
How It Works
Main Components
GUI Layout:

The GUI is implemented using the JFrame class with multiple JPanel components:
ID Panel: For entering the product ID.
Quantity Panel: For entering the desired quantity.
Button Panel: Contains buttons for checking stock, clearing inputs, and exiting.
Uses a BorderLayout to organize the panels.
Fields for user input (JTextField) and labels (JLabel) guide the user.
User Actions:

Check Stock Button:
Takes user input for Product ID and Quantity.
Creates a Product object and checks its stock status using the StockCheck class.
Displays stock information and restocking needs in the console.
Clear Button:
Clears all input fields.
Exit Button:
Closes the application.

Limitations
The program assumes the Inventory.txt file is formatted correctly.
Displays results in the console rather than the GUI.
Future Enhancements
Add GUI components to display stock results directly within the application.
Improve error handling for invalid inputs and file operations.
Support dynamic updating of inventory through the application.
Credits
Author: HP
Logger Utility: java.util.logging
GUI Framework: javax.swing
