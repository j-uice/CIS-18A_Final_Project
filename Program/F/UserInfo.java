package F;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;


// to compile and run in terminal, copy below
// javac F/*.java && java F.JuicesApocGoods


public class UserInfo implements ActionListener {
	public JFrame userInfoFrm;
	protected static JTextField frstName;
	protected static JTextField lstName;
	protected static JTextField userEmail;
	protected static JFormattedTextField dlvryDate;
	protected static JTextField dlvryTime;
	protected static String Date;
	protected static String Time;
	protected static String FrstN;
	protected static String LstN;
	protected static String Email;

	public UserInfo() {
		// main frame for this class
		userInfoFrm = new JFrame("Juice's Apocalyptic Goods");
		userInfoFrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        	userInfoFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	userInfoFrm.setLayout(new BorderLayout());

	        // background panel for decor
	        JPanel userInfoBckgrnd = new JPanel();
		userInfoBckgrnd.setLayout(new BoxLayout(userInfoBckgrnd, BoxLayout.Y_AXIS));
	        userInfoBckgrnd.setBackground(Color.BLACK);

        	// title panel to hold title label
	        JPanel titlePnl = new JPanel(new BorderLayout()); 
        	titlePnl.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
	        titlePnl.setBackground(Color.BLACK);
        	JLabel titleLbl= new JLabel("ENTER YOUR INFORMATION", SwingConstants.CENTER);
	        titleLbl.setForeground(Color.GREEN);
	        titleLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
        	titlePnl.add(titleLbl, BorderLayout.NORTH);
		
		// displays the title on top and makes background panel content pane
		userInfoBckgrnd.add(titlePnl);
		userInfoFrm.setContentPane(userInfoBckgrnd);

		// panel to display where users will input their information
		JPanel userInputPnl = new JPanel();
		userInputPnl.setLayout(new BoxLayout(userInputPnl, BoxLayout.Y_AXIS));
		userInputPnl.setBackground(Color.BLACK);
		userInfoBckgrnd.add(userInputPnl);


		// label to direct users to writing their last name in the text field
		JLabel frstNameLbl = new JLabel(" FIRST NAME: ");
		frstNameLbl.setForeground(Color.GREEN);
		frstNameLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
		userInputPnl.add(frstNameLbl); // adds the first name label to user input panel


		// text field for users to input their first name
		frstName = new JTextField(10);
		frstName.setBackground(Color.DARK_GRAY);
		frstName.setForeground(Color.GREEN);
		frstName.setFont(new Font("Monospaced", Font.BOLD, 35));
		userInputPnl.add(frstName);

		// label to direct users to writing their last name in the text field
		JLabel lstNameLbl = new JLabel(" LAST NAME: ");
		lstNameLbl.setForeground(Color.GREEN);
		lstNameLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
		userInputPnl.add(lstNameLbl); // adds the last name label to user input panel

		// text field for users to input their last name
		lstName = new JTextField(10);
		lstName.setBackground(Color.DARK_GRAY);
		lstName.setForeground(Color.GREEN);
		lstName.setFont(new Font("Monospaced", Font.BOLD, 35));
		userInputPnl.add(lstName);

		// label to direct users to writing their email in the text field
		JLabel emailLbl = new JLabel(" EMAIL: ");
		emailLbl.setForeground(Color.GREEN);
		emailLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
		userInputPnl.add(emailLbl); // adds the email label to user input panel

		// text field for users to input their email
		userEmail = new JTextField(10);
		userEmail.setBackground(Color.DARK_GRAY);
		userEmail.setForeground(Color.GREEN);
		userEmail.setFont(new Font("Monospaced", Font.BOLD, 35));
		userInputPnl.add(userEmail);
		
		// label to direct users to writing a date for their delivery in the text field
		JLabel dateLbl = new JLabel(" ENTER A DATE FOR DELIVERY (MM/DD/YYYY) INCLUDE THE SLASHES: ");
		dateLbl.setForeground(Color.GREEN);
		dateLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
		userInputPnl.add(dateLbl);
		
		// delivery date
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		dlvryDate = new JFormattedTextField(df);
		dlvryDate.setBackground(Color.DARK_GRAY);
		dlvryDate.setForeground(Color.GREEN);
		dlvryDate.setFont(new Font("Monospaced", Font.BOLD, 35));
		dlvryDate.setColumns(10);
		userInputPnl.add(dlvryDate);
	
		// label to direct users to writing a time for their delivery in the text field
		JLabel timeLbl = new JLabel(" ENTER A TIME, IN 24 HOUR FORMAT AND INCLUDING THE COLON, FOR DELIVERY (HH:MM): ");
		timeLbl.setForeground(Color.GREEN);
		timeLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
		userInputPnl.add(timeLbl);
	
		// text field for time
		dlvryTime = new JTextField(10);
		dlvryTime.setBackground(Color.DARK_GRAY);
		dlvryTime.setForeground(Color.GREEN);
		dlvryTime.setFont(new Font("Monospaced", Font.BOLD, 35));
		userInputPnl.add(dlvryTime);
		

		// button takes user back
		JButton backBttn = new JButton("GO BACK");
		backBttn.setBackground(Color.GREEN);
		backBttn.setForeground(Color.BLACK);
		backBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
		backBttn.setActionCommand("back");
		backBttn.addActionListener(this);

		// button takes user to an order summary window
		JButton orderSumBttn = new JButton("PLACE ORDER");
		orderSumBttn.setBackground(Color.GREEN);
		orderSumBttn.setForeground(Color.BLACK);
		orderSumBttn.setFont(new Font("MonoSpaced", Font.BOLD, 40));
		orderSumBttn.setActionCommand("cart");
		orderSumBttn.addActionListener(this);
	
		// panel to keep buttons organized
		JPanel infoBttnPnl = new JPanel(new FlowLayout());
		infoBttnPnl.setBackground(Color.BLACK);
		infoBttnPnl.add(backBttn);
		infoBttnPnl.add(orderSumBttn);
		userInfoBckgrnd.add(infoBttnPnl); // adds the panel to the frame to display
		
		// shows current window
		userInfoFrm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
        	String command = ae.getActionCommand();
	
		if (command.equals("back")) {
			userInfoFrm.dispose();
			new OrderFrame();
		}

		if (command.equals("cart")) {
			FrstN = frstName.getText();
			LstN = lstName.getText();
			Email = userEmail.getText();
			
			if (FrstN.isEmpty()) {
				JOptionPane.showMessageDialog(userInfoFrm, "First name cannot be empty. Please enter your first name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;

			}
			
			if (LstN.isEmpty()) {
				JOptionPane.showMessageDialog(userInfoFrm, "Last name cannot be empty. Please enter your last name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;

			}

			if (Email.isEmpty()) {
				JOptionPane.showMessageDialog(userInfoFrm, "Email cannot be empty. Please enter your email.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

		 	// gets text from text field
			String dateInput = dlvryDate.getText().trim();
			
			// validates text field if its empty
			if (dlvryDate.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(userInfoFrm, "Delivery date cannot be empty. Please enter a date in MM/dd/yyyy format. (INCLUDE THE SLASHES)", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// validates the date input	
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				sdf.setLenient(false); // Disables lenient parsing (e.g., 02/30/2025 won't work)
				sdf.parse(dlvryDate.getText()); // Will throw an exception if invalid

            		} catch (ParseException e) { // message pops out when user enters invalid date
				JOptionPane.showMessageDialog(userInfoFrm, "Invalid date format. Please enter the date in MM/dd/yyyy format (INCLUDE THE SLASHES).", "Error", JOptionPane.ERROR_MESSAGE);
				return;
            	   	  }
		
			// gets text from text field and defines a 24 hr format for validation
			String timeInput = dlvryTime.getText().trim();
			String timeRegex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; // Ensures HH:mm format
			
			if (timeInput.isEmpty()) {
				JOptionPane.showMessageDialog(userInfoFrm, "Delivery time cannot be empty. Please enter a time in HH:mm (24-HOUR FORMAT AND INCLUDE THE COLON).", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			
			if(!Pattern.matches(timeRegex, timeInput)) {
				JOptionPane.showMessageDialog(userInfoFrm, "Delivery time cannot be empty. Please enter a time in HH:mm (24-HOUR FORMAT AND INCLUDE THE COLON).", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime.parse(timeInput, formatter); // Parses and validates time
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(userInfoFrm, "Delivery time cannot be empty. Please enter a time in HH:mm (24-HOUR FORMAT AND INCLUDE THE COLON).", "Error", JOptionPane.ERROR_MESSAGE);
				return;

		 	  }
			
			Date = dateInput;
			Time = timeInput;
			userInfoFrm.dispose();
			new OrderSummary();
		}
	}
} 
