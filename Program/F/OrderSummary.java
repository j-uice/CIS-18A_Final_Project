package F;

import F.Crt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Component;

// to compile and run in terminal, copy below
// javac F/*.java && java F.JuicesApocGoods

public class OrderSummary implements ActionListener {
	JFrame orderSumFrm;
	JPanel userNGoodsPnl;
	
	OrderSummary() {
		// creates the frame
		orderSumFrm = new JFrame("Juice's Apocalyptic Goods");
		orderSumFrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        	orderSumFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	orderSumFrm.setLayout(new BorderLayout());

	        // creates a background panel
	        JPanel orderSumBckgrnd = new JPanel(new BorderLayout());
	        orderSumBckgrnd.setBackground(Color.BLACK);
		orderSumBckgrnd.setForeground(Color.GREEN);
		orderSumBckgrnd.setFont(new Font("Monospaced", Font.BOLD, 40));
		orderSumFrm.setContentPane(orderSumBckgrnd);

        	// creates the title panel
	        JPanel titlePnl = new JPanel(new BorderLayout()); 
        	titlePnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        titlePnl.setBackground(Color.BLACK);
        	JLabel titleLbl = new JLabel("* ORDER SUMMARY *", SwingConstants.CENTER);
		titleLbl.setForeground(Color.GREEN); 
	        titleLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
        	titlePnl.add(titleLbl, BorderLayout.CENTER); 
		orderSumBckgrnd.add(titlePnl, BorderLayout.NORTH);

	        // panel to show the list of goods and users info
	        userNGoodsPnl = new JPanel();
	        userNGoodsPnl.setBackground(Color.BLACK);
	        userNGoodsPnl.setLayout(new GridLayout(10, 1, 1, 1)); 
        	orderSumBckgrnd.add(userNGoodsPnl, BorderLayout.CENTER);

		// using a method to style and put each content in a panel
		userNGoodsPnl.add(createStyledLabel("First Name: " + UserInfo.FrstN));
	        userNGoodsPnl.add(createStyledLabel("Last Name: " + UserInfo.LstN));
	        userNGoodsPnl.add(createStyledLabel("Email: " + UserInfo.Email));
	        userNGoodsPnl.add(createStyledLabel("Delivery Date: " + UserInfo.Date));
	        userNGoodsPnl.add(createStyledLabel("Delivery Time: " + UserInfo.Time));

		Crt.productLabels = new JLabel[Crt.product.length];

        	// Create buttons for each product
	        for (int i = 0; i < Crt.product.length; i++) {
   	         JPanel productPanel = new JPanel(new FlowLayout());
         	 productPanel.setBackground(Color.BLACK);

		 Crt.productLabels[i] = new JLabel(" " + Crt.quantities[i] + " " + Crt.product[i] + " ($" + Crt.prices[i] + ") ");
		 Crt.productLabels[i].setForeground(Color.GREEN);
		 Crt.productLabels[i].setFont(new Font("Monospaced", Font.BOLD, 40));

            	 productPanel.add(Crt.productLabels[i]);

            	 userNGoodsPnl.add(productPanel);
        	}


		// organize the menu buttons
		JPanel menuBttnPnl = new JPanel(new FlowLayout());
		menuBttnPnl.setBackground(Color.BLACK);
		orderSumBckgrnd.add(menuBttnPnl, BorderLayout.SOUTH);

		// button to go back to user info
		JButton backBttn = new JButton("BACK TO U-INFO");
		backBttn.setBackground(Color.GREEN);
		backBttn.setForeground(Color.BLACK);
		backBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
		backBttn.setActionCommand("u_info");
		backBttn.addActionListener(this);
		menuBttnPnl.add(backBttn);
		
		// button to go back to the list of goods
		JButton back2GoodsBttn = new JButton("BACK TO GOODS");
		back2GoodsBttn.setBackground(Color.GREEN);
		back2GoodsBttn.setForeground(Color.BLACK);
		back2GoodsBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
		back2GoodsBttn.setActionCommand("goods");
		back2GoodsBttn.addActionListener(this);
		menuBttnPnl.add(back2GoodsBttn);

		// Button for printing the receipt (getting a text file)
		JButton printBttn = new JButton("PRINT RECEIPT");
		printBttn.setBackground(Color.GREEN);
		printBttn.setForeground(Color.BLACK);
		printBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
		printBttn.setActionCommand("print");
		printBttn.addActionListener(this);
		menuBttnPnl.add(printBttn);
		
		orderSumFrm.setVisible(true);
	}
		
	JPanel createStyledLabel(String text) {
		JPanel userInfoPnl = new JPanel(new FlowLayout());
		JLabel userInfoLbl = new JLabel(text);
		userInfoPnl.setBackground(Color.BLACK);
		userInfoLbl.setForeground(Color.GREEN);
		userInfoLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
    
		userInfoPnl.add(userInfoLbl);  
		return userInfoPnl;            
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
        	String command = ae.getActionCommand();
	
		if (command.equals("u_info")) {
			orderSumFrm.dispose();
			new UserInfo();
		}
		
		if (command.equals("goods")) {
			orderSumFrm.dispose();
			new OrderFrame();
		}

		if (command.equals("print")) {
			saveOrderSummaryToFile();
		}

	}

	private void saveOrderSummaryToFile() {
		try {
			FileWriter writer = new FileWriter("ReceiptOrder.txt");
			
			// a loop that goes through all the components in userNGoodsPnl
			for (Component component : userNGoodsPnl.getComponents()) {
				if (component instanceof JPanel) {
					JPanel panel = (JPanel) component;
					for (Component innerComponent : panel.getComponents()) {
						if (innerComponent instanceof JLabel) {
							JLabel label = (JLabel) innerComponent;
							writer.write(label.getText() + "\n"); // writing label text to file
						}
					}
				}
			}

			writer.close();
			JOptionPane.showMessageDialog(orderSumFrm, "A text file has been saved!\nThank you for using Juice's Apocalyptic Goods!", "Saved", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(orderSumFrm, "Error saving File!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		  }
	}
} 
