//SalesGUI.java
//created by: Daniel Myers

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class SalesGUI extends JFrame{

	private static final long serialVersionUID = 2915521560679875258L;
	
	static final String DATABASE_URL = "jdbc:mysql://localhost/mavtunes";
	private JDesktopPane theDesktop;
	private JLabel nameLabel, streetLabel, cityLabel, stateLabel, zipLabel, typeOfMusicLabel, 		
		enterTitleLabel, datePurchasedLabel, acctNumberLabel, submitWhenFinishedLabel, 	
		typeOfAppLabel;
	private JTextField nameField, streetField, cityField, stateField, zipField, enterTitleField, 		
		datePurchasedField, acctNumberField;
	private JComboBox typeOfMusicBox;
	private JButton appSubmitButton, musicSubmitButton;
 	private JRadioButton gameButton, productivityButton, educationButton;
	private JPanel typePanel;

	ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	Customer c1;
	
	Music.GenreType musicType;
	App.Type appType;
	
	Music music;
	App app;
	
	DBMethods dbmethods = new DBMethods();
	
	String type;
		
	public int zip, acctNumber, nOSP;
	double price;
	private String name, street, city, state, title, gameType, genre, developer, artist;
	
	Date datePurchased;

	public SalesGUI(){
		super("Mav Tunes");

		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenu purchaseMenu = new JMenu("Purchase");
		purchaseMenu.setMnemonic('P');

		JMenuItem writeFileItem = new JMenuItem("Write File");
		writeFileItem.setMnemonic('W');
		JMenuItem readFileItem = new JMenuItem("Read File");
		readFileItem.setMnemonic('R');
		JMenuItem writeDatabaseItem = new JMenuItem("Write Database");
		writeDatabaseItem.setMnemonic('D');
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('X');

		fileMenu.add(writeFileItem);
		fileMenu.add(readFileItem);
		fileMenu.add(writeDatabaseItem);
		fileMenu.add(exitItem);

		JMenuItem buyAppItem = new JMenuItem("Buy App");
		buyAppItem.setMnemonic('A');
		JMenuItem buyMusicItem = new JMenuItem("Buy Music");
		buyMusicItem.setMnemonic('M');
		
		purchaseMenu.add(buyAppItem);
		purchaseMenu.add(buyMusicItem);

		bar.add(fileMenu);
		bar.add(purchaseMenu);
		setJMenuBar(bar);

		theDesktop = new JDesktopPane();		
		add(theDesktop);

		readFileItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					readFile();
				}
			}
		);
		
		writeDatabaseItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					DBMethods dbmethods = new DBMethods();
					dbmethods.writeDB();
				}
			}
		);

		exitItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					DBMethods dbmethods = new DBMethods();
					dbmethods.readDB();
					System.exit(0);
				}
			}
		);
		
		writeFileItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					writeFile();
				}
			}
		);

		buyAppItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JInternalFrame frame = new JInternalFrame("Purchase Application",
								true, true, true, true);
					
					frame.add(AppJPanel(), BorderLayout.CENTER);
					frame.pack();
					frame.setSize(750,350);
				
					theDesktop.add(frame);
					frame.setVisible(true);
				}
			}
		);

		buyMusicItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JInternalFrame frame = new JInternalFrame("Purchase Music",
								true, true, true, true);
					frame.add(MusicJPanel(), BorderLayout.CENTER);
					frame.pack();
					frame.setSize(750,350);

					theDesktop.add(frame);
					frame.setVisible(true);
				}
			}
		);
	}
		
	public JPanel AppJPanel(){
		JPanel panel = new JPanel(new GridLayout(10,2,1,1));
		
		nameLabel = new JLabel("Name");
		streetLabel = new JLabel("Street");
		cityLabel = new JLabel("City");
		stateLabel = new JLabel("State");
		zipLabel = new JLabel("Zip");
		typeOfAppLabel = new JLabel("Type Of App");
		enterTitleLabel = new JLabel("Enter Title");
		datePurchasedLabel = new JLabel("Date Purchased");
		acctNumberLabel = new JLabel("Account Number");
		submitWhenFinishedLabel = new JLabel("Submit when Finished");
		
	
		nameField = new JTextField(30);
		streetField = new JTextField(30);
		cityField = new JTextField(30);
		stateField = new JTextField(30);
		zipField = new JTextField(5);
		enterTitleField = new JTextField(30);
		datePurchasedField = new JTextField(10);
		acctNumberField = new JTextField(10);
		
		datePurchasedField.setToolTipText("Format: mm/dd/yyyy");
		
		gameButton = new JRadioButton("GAME");
		productivityButton = new JRadioButton("PRODUCTIVITY");
		educationButton = new JRadioButton("EDUCATION");
		typePanel = new JPanel();
		typePanel.add(gameButton);
		typePanel.add(productivityButton);
		typePanel.add(educationButton);
		
		gameButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(gameButton.isSelected()){
						educationButton.setSelected(false);
						productivityButton.setSelected(false);
					}
				}
			}
		);
		
		productivityButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(productivityButton.isSelected()){
						gameButton.setSelected(false);
						educationButton.setSelected(false);
					}
				}
			}
		);
		
		educationButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(educationButton.isSelected()){
						gameButton.setSelected(false);
						productivityButton.setSelected(false);
					}
				}
			}
		);
		
		appSubmitButton = new JButton("Submit");

		setVisible(true);

		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(streetLabel);
		panel.add(streetField);
		panel.add(cityLabel);
		panel.add(cityField);
		panel.add(stateLabel);
		panel.add(stateField);
		panel.add(zipLabel);
		panel.add(zipField);
		panel.add(typeOfAppLabel);
		panel.add(typePanel);
		panel.add(enterTitleLabel);
		panel.add(enterTitleField);
		panel.add(datePurchasedLabel);
		panel.add(datePurchasedField);
		panel.add(acctNumberLabel);
		panel.add(acctNumberField);
		panel.add(submitWhenFinishedLabel);
		panel.add(appSubmitButton);
		
		SubmitHandler handler = new SubmitHandler();
		appSubmitButton.addActionListener(handler);

		return panel;
	}

	public JPanel MusicJPanel(){
		JPanel panel = new JPanel(new GridLayout(10,2,1,1));
		String[] genres = {"CHOOSE ONE", "CLASSICAL", "ROCK", "COUNTRY"};
		
		nameLabel = new JLabel("Name");
		streetLabel = new JLabel("Street");
		cityLabel = new JLabel("City");
		stateLabel = new JLabel("State");
		zipLabel = new JLabel("Zip");
		typeOfMusicLabel = new JLabel("Type Of Music");
		enterTitleLabel = new JLabel("Enter Title");
		datePurchasedLabel = new JLabel("Date Purchased");
		acctNumberLabel = new JLabel("Account Number");
		submitWhenFinishedLabel = new JLabel("Submit when Finished");
	
		nameField = new JTextField(30);
		streetField = new JTextField(30);
		cityField = new JTextField(30);
		stateField = new JTextField(30);
		zipField = new JTextField(5);
		enterTitleField = new JTextField(30);
		datePurchasedField = new JTextField(10);
		acctNumberField = new JTextField(10);
		
		datePurchasedField.setToolTipText("Format: mm/dd/yyyy");

		typeOfMusicBox = new JComboBox(genres);
		typeOfMusicBox.setMaximumRowCount(3);
		
		musicSubmitButton = new JButton("Submit");

		setVisible(true);

		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(streetLabel);
		panel.add(streetField);
		panel.add(cityLabel);
		panel.add(cityField);
		panel.add(stateLabel);
		panel.add(stateField);
		panel.add(zipLabel);
		panel.add(zipField);
		panel.add(typeOfMusicLabel);
		panel.add(typeOfMusicBox);
		panel.add(enterTitleLabel);
		panel.add(enterTitleField);
		panel.add(datePurchasedLabel);
		panel.add(datePurchasedField);
		panel.add(acctNumberLabel);
		panel.add(acctNumberField);
		panel.add(submitWhenFinishedLabel);
		panel.add(musicSubmitButton);

		SubmitHandler handler = new SubmitHandler();
		musicSubmitButton.addActionListener(handler);

		return panel;
	}
	
	public void writeFile(){
		ObjectOutputStream output;
				
		try{
			output = new ObjectOutputStream(new FileOutputStream("customers.ser"));
			
			for(int i = 0; i < customerList.size(); i++){
				output.writeObject(customerList.get(i));
			}
		} catch(IOException ioException){
			System.err.println("Error writing to file.");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		ObjectInputStream input;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showOpenDialog(null);

		if(result == JFileChooser.CANCEL_OPTION)
			System.exit(1);
	
		File fileName = fileChooser.getSelectedFile();

		if( (fileName == null) || (fileName.getName().equals(""))){
			JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid Name",
			JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		Customer customer;

		try{
			input = new ObjectInputStream(new FileInputStream(fileName));
			System.out.println("\nCUSTOMER INFORMATION\n--------------------");
			
			while(true){
				customer = (Customer) input.readObject();
				System.out.println(customer.getName() + "\n" + customer.getAddress() + "\n" + customer.getAccountNumber() + "\n\n");
			}
		} catch (EOFException endOfFileException){
			System.err.println("End of file.\n\n");
		} catch (ClassNotFoundException classNotFountException){
			System.err.println("Unable to create object.");
		}  catch (IOException ioException){
			System.err.println("Error reading file.");
		} 
	}

	public Date parseDate(String pDate){
		int index1;
		int index2;
		index1 = pDate.indexOf('/');

		String purchaseDate = pDate;
		
		int month;
		int day;
		int year;
		month = Integer.parseInt(purchaseDate.substring(0,index1));
		
		index2 = pDate.indexOf('/', ++index1);
		day = Integer.parseInt(purchaseDate.substring(index1,index2));
		

		year = Integer.parseInt(purchaseDate.substring(++index2, purchaseDate.length()));
	
		return new Date(month, day, year);
	}
	
	public void clearAll(String type){
		nameField.setText("");
		streetField.setText("");
		cityField.setText("");
		stateField.setText("");
		zipField.setText("");
		enterTitleField.setText("");
		datePurchasedField.setText("");
		acctNumberField.setText("");
		nameField.requestFocus();
		
		if(type.equals("App")){
			gameButton.setSelected(false);
			productivityButton.setSelected(false);
			educationButton.setSelected(false);
		}
		
		if(type.equals("Music"))
			typeOfMusicBox.setSelectedIndex(0);
	}

	public Music createMusic(String t, double p, Date pDate, Music.GenreType gT, String a, int n){
		//create new Music object for customer in customer array
		Music m = new Music(t, price, pDate, gT, artist, nOSP);
		
		clearAll(type);
		
		return m;
	}//end createMusic
	
	public App createApp(String t, double p, Date dP, App.Type gT, String d){
		//create new App object for customer in customer array		
		App a = new App(t, price, dP, gT, developer);
		
		clearAll(type);
		
		return a;
	}//end createApp

	private class SubmitHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == appSubmitButton)
				type = "App";
			else if(event.getSource() == musicSubmitButton)
				type = "Music";
			
			name = nameField.getText();
			
			if(name.equals("") || name.equals(null)){
				JOptionPane.showMessageDialog(null, "Name is blank", "Error",JOptionPane.ERROR_MESSAGE);
				nameField.setText("");
				nameField.requestFocus();
				return;
			}
			
			street = streetField.getText();
			
			if(street.equals("") || street.equals(null)){
				JOptionPane.showMessageDialog(null, "Street is blank", "Error", JOptionPane.ERROR_MESSAGE);
				streetField.setText("");
				streetField.requestFocus();
				return;
			}
			
			city = cityField.getText();
			
			if(city.equals("") || city.equals(null)){
				cityField.setText("");
				cityField.requestFocus();
				JOptionPane.showMessageDialog(null, "City is blank", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			state = stateField.getText();
			
			if(state.equals("") || state.equals(null)){
				stateField.setText("");
				stateField.requestFocus();
				JOptionPane.showMessageDialog(null, "State is blank.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			datePurchased = parseDate(datePurchasedField.getText());
			title = enterTitleField.getText();
			
			try{
				zip = Integer.parseInt(zipField.getText());
			} catch (Exception e){
				zipField.setText("");
				zipField.requestFocus();
				JOptionPane.showMessageDialog(null, "Please enter an integer for the zip code.", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			
			try{
				acctNumber = Integer.parseInt(acctNumberField.getText());
			} catch (Exception e){
				acctNumberField.setText("");
				acctNumberField.requestFocus();
				JOptionPane.showMessageDialog(null, "Please use an integer for the account number.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			c1 = new Customer(name, new Address(street, city, state, zip), acctNumber);
			
			boolean customerDoesntExist = true;
			
			for(Customer cust: customerList){
				if(cust.getAccountNumber() == c1.getAccountNumber()){
					//changed if to .getAccountNumber()
					try{
						if(dbmethods.checkInput(type)){
							if(type.equals("Music"))
								cust.addToProductList(createMusic(title, price, datePurchased, musicType, artist, nOSP));
							else if(type.equals("App"))
								cust.addToProductList(createApp(title, price, datePurchased, appType, developer));
						}
							
						customerDoesntExist = false;
					} catch(TitleNotFoundException tnfE){
						enterTitleField.setText("");
						enterTitleField.requestFocus();
					}	
				}
			}
			if(customerDoesntExist){
				try{
					if(dbmethods.checkInput(type)){
						customerList.add(new Customer(c1.getName(), c1.getAddress(), c1.getAccountNumber()));
						
						for(Customer c: customerList){
							if(c.getAccountNumber() == c1.getAccountNumber()){
								if(type.equals("Music"))
									c.addToProductList(createMusic(title, price, datePurchased, musicType, artist, nOSP));
								else if (type.equals("App"))
									c.addToProductList(createApp(title, price, datePurchased, appType, developer));
							}
						}
					}
				} catch(TitleNotFoundException tnfE){
					enterTitleField.setText("");
					enterTitleField.requestFocus();
				}
			}
		}
	}

	private class DBMethods{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		public void getConnection(){
			try{
				connection = DriverManager.getConnection(DATABASE_URL, "root", "root");
				statement = connection.createStatement();
			} catch (SQLException sqlE){
				sqlE.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
		}

		public boolean checkInput(String type){
			getConnection();
			
			try{
				boolean titleFound = false;
				if(type.equals("App")){
					resultSet = statement.executeQuery("SELECT * FROM Apps");

					while(resultSet.next()){
						if(resultSet.getString("Title").equals(title)){
							gameType = resultSet.getString("Type");
							developer = resultSet.getString("Developer");
							price = resultSet.getDouble("Price");
							
							
							if(gameType.equals("GAME"))
								appType = App.Type.GAME;
							else if(gameType.equals("EDUCATION"))
								appType = App.Type.EDUCATION;
							else if(gameType.equals("PRODUCTIVITY"))
								appType = App.Type.PRODUCTIVITY;
							
							clearAll(type);
							titleFound = true;
							return true;							
						}
					}
					if(titleFound == false){
						throw new TitleNotFoundException();
					}
				}
				else if(type.equals("Music")){
					resultSet = statement.executeQuery("SELECT * FROM Music");

					while(resultSet.next()){
						if(resultSet.getString("Title").equals(title)){
							genre = resultSet.getString("Type");
							artist = resultSet.getString("Artist");
							nOSP = resultSet.getInt("NumberofSongs");	
							price = resultSet.getDouble("Price");

							if(genre.equals("CLASSICAL"))
								musicType = Music.GenreType.CLASSICAL;
							else if(genre.equals("ROCK"))
								musicType = Music.GenreType.ROCK;
							else if(genre.equals("COUNTRY"))
								musicType = Music.GenreType.COUNTRY;
							
							clearAll(type);
							titleFound = true;
							return true;
						}
					}
					if(titleFound == false)
						throw new TitleNotFoundException();
				}
			} catch (TitleNotFoundException tnfE){
				throw new TitleNotFoundException(title);
			} catch (SQLException sqlE){
				sqlE.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
			return false;
		}

		public void readDB(){
			getConnection();
			
			try{
				int ix = 1;
				resultSet = statement.executeQuery("SELECT * FROM Customers");
				System.out.println("---------\nCUSTOMERS\n---------");
				System.out.printf("%-2s %-15s %-27s %-10s %-5s %-6s\n", "CustID", "Name", "Street", "City", "State", "Zip");
				
				ResultSetMetaData metaData = resultSet.getMetaData();
				int numberOfColumns = metaData.getColumnCount();

				while(resultSet.next()){
					System.out.printf("%-6s ", resultSet.getInt("CustID"));
					System.out.printf("%-15s ", resultSet.getString("Name"));
					System.out.printf("%-27s ", resultSet.getString("Street"));
					System.out.printf("%-10s ", resultSet.getString("City"));
					System.out.printf("%-5s ", resultSet.getString("State"));
					System.out.printf("%-6s\n", resultSet.getInt("Zip"));
				}

				System.out.println();			

				resultSet = statement.executeQuery("SELECT * FROM Products");
				System.out.println("-------------\nPRODUCTS SOLD\n-------------");
				System.out.printf("%-6s %-6s %-12s\n", "CustID", "Type", "Title");

				metaData = resultSet.getMetaData();
				numberOfColumns = metaData.getColumnCount();

				while(resultSet.next()){
					System.out.printf("%-6s ", resultSet.getInt("CustID"));
					System.out.printf("%-6s ", resultSet.getString("Type"));
					System.out.printf("%-12s\n", resultSet.getString("Title"));
				}
				System.out.println();

				resultSet = statement.executeQuery("SELECT * FROM Music");
				System.out.println("-----\nMUSIC\n-----");

				metaData = resultSet.getMetaData();
				numberOfColumns = metaData.getColumnCount();

				System.out.printf("%-16s %-10s %-13s %-11s %-5s\n", "Title", "Type", "Artist", 
					"NumOfSongs", "Price");
				
				while(resultSet.next()){
					System.out.printf("%-16s ", resultSet.getString("Title"));
					System.out.printf("%-10s ", resultSet.getString("Type"));
					System.out.printf("%-13s ", resultSet.getString("Artist"));
					System.out.printf("%-11s ", resultSet.getInt("NumberOfSongs"));
					System.out.printf("%-5s\n", resultSet.getDouble("Price"));	
					
				}
				System.out.println();

				resultSet = statement.executeQuery("SELECT * FROM Apps");
				System.out.println("------------\nAPPLICATIONS\n------------");

				metaData = resultSet.getMetaData();
				numberOfColumns = metaData.getColumnCount();
				
				System.out.printf("%-11s %-13s %-21s %-6s\n", "Title", "Type", "Developer",
					"Price");

				while(resultSet.next()){
					System.out.printf("%-11s ",resultSet.getString("Title"));
					System.out.printf("%-13s ",resultSet.getString("Type"));
					System.out.printf("%-21s ",resultSet.getString("Developer"));
					System.out.printf("%-6s\n",resultSet.getDouble("Price"));					}
				}
			 catch(SQLException sql){
				sql.printStackTrace();
			} finally {
				try{
					statement.close();
					connection.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
				

		public void writeDB(){
			getConnection();
			PreparedStatement insertNewCustomer = null;
			PreparedStatement insertNewProduct = null;
			
			boolean customerDoesntExist = true;
			int custID = 1;
			
			try{
				resultSet = statement.executeQuery("SELECT * FROM Customers");
				
				insertNewCustomer = connection.prepareStatement(
					"INSERT INTO Customers (CustID, Name, Street, City, State, Zip) VALUES (?,?,?,?,?,?)");
				insertNewProduct = connection.prepareStatement(
					"INSERT INTO Products (CustID, Type, Title) VALUES (?,?,?)");

				for(Customer cust: customerList){
					ArrayList<Product> productList = cust.getProductList();
							
					while(resultSet.next()){
						if(resultSet.getString("Name").equals(cust.getName())){
							custID = resultSet.getInt("CustID");
							
							for(Product p: productList){
								insertNewProduct.setInt(1, custID);
								insertNewProduct.setString(2, p.getClass().getName());
								insertNewProduct.setString(3, p.getTitle());
									
								insertNewProduct.executeUpdate();
									
								customerDoesntExist = false;
							}
						}
						
						custID++;
					}
						
						
					if(customerDoesntExist){
						insertNewCustomer.setInt(1, custID);
						insertNewCustomer.setString(2, cust.getName());
						insertNewCustomer.setString(3, cust.getAddress().getStreet());
						insertNewCustomer.setString(4, cust.getAddress().getCity());
						insertNewCustomer.setString(5, cust.getAddress().getState());
						insertNewCustomer.setInt(6, cust.getAddress().getZip());

						insertNewCustomer.executeUpdate();
							
						for(Product p: productList){
							insertNewProduct.setInt(1, custID);
							insertNewProduct.setString(2, p.getClass().getName());
							insertNewProduct.setString(3, p.getTitle());
							
							insertNewProduct.executeUpdate();
						}
							
						custID++;
						customerDoesntExist = true;
					}
				}
			} catch (SQLException sqlE){
				sqlE.printStackTrace();
			} finally {
				try{
					statement.close();
					connection.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}		
}

