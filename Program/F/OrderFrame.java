package F;

import F.Crt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// to compile and run in terminal, copy below
// javac F/*.java && java F.JuicesApocGoods

public class OrderFrame implements ActionListener {

	public JFrame orderFrm;
	
	public OrderFrame() {
        	orderFrm = new JFrame("Juice's Apocalyptic Goods");
        	orderFrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        	orderFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	orderFrm.setLayout(new BorderLayout());

	        // background panel
	        JPanel orderBackground = new JPanel(new BorderLayout());
	        orderBackground.setBackground(Color.BLACK);
	        orderFrm.setContentPane(orderBackground);

        	// title panel to hold title label
	        JPanel orderPanel = new JPanel(new BorderLayout()); 
        	orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        orderPanel.setBackground(Color.BLACK);


        	JLabel orderTitle = new JLabel("SELECT YOUR ITEMS!", SwingConstants.CENTER);
	        orderTitle.setForeground(Color.GREEN);
	        orderTitle.setFont(new Font("Monospaced", Font.BOLD, 40));
        	orderPanel.add(orderTitle, BorderLayout.CENTER); 
	        orderBackground.add(orderPanel, BorderLayout.NORTH);	// display it on top
		

	        // panel to show the list of goods and +/- buttons
	        JPanel goodsPanel = new JPanel();
	        goodsPanel.setBackground(Color.BLACK);
	        goodsPanel.setLayout(new GridLayout(Crt.product.length, 1, 10, 10)); // Arranges products in rows
        	orderBackground.add(goodsPanel, BorderLayout.CENTER);

		Crt.productLabels = new JLabel[Crt.product.length];

        	// Create buttons for each product
	        for (int i = 0; i < Crt.product.length; i++) {
   	         JPanel productPanel = new JPanel(new FlowLayout());
         	 productPanel.setBackground(Color.BLACK);

		 Crt.productLabels[i] = new JLabel(" " + Crt.quantities[i] + " " + Crt.product[i] + " ($" + Crt.prices[i] + ") ");
		 Crt.productLabels[i].setForeground(Color.GREEN);
		 Crt.productLabels[i].setFont(new Font("Monospaced", Font.BOLD, 40));

           	 JButton addButton = new JButton("+");
		 addButton.setBackground(Color.GREEN);
            	 addButton.setForeground(Color.BLACK);
            	 addButton.setFont(new Font("Monospaced", Font.BOLD, 40));
            	 addButton.setActionCommand("add_" + i); // Unique command per product
            	 addButton.addActionListener(this);

            	 JButton removeButton = new JButton("-");
            	 removeButton.setBackground(Color.GREEN);
            	 removeButton.setForeground(Color.BLACK);
            	 removeButton.setFont(new Font("Monospaced", Font.BOLD, 40));
            	 removeButton.setActionCommand("remove_" + i);
            	 removeButton.addActionListener(this);

            	 productPanel.add(Crt.productLabels[i]);
            	 productPanel.add(removeButton);
            	 productPanel.add(addButton);

            	 goodsPanel.add(productPanel);
        	}

		// organize the menu buttons
		JPanel menuBttnPnl = new JPanel(new FlowLayout());
		menuBttnPnl.setBackground(Color.BLACK);
		orderBackground.add(menuBttnPnl, BorderLayout.SOUTH);

		// button to go back to main menu
		JButton backBttn = new JButton("GO BACK");
		backBttn.setBackground(Color.GREEN);
		backBttn.setForeground(Color.BLACK);
		backBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
		backBttn.setActionCommand("back");
		backBttn.addActionListener(this);
		menuBttnPnl.add(backBttn);

		// Button for placing order to cart and will take user to take their info
		JButton plcOrderBttn = new JButton("PLACE IN CART");
		plcOrderBttn.setBackground(Color.GREEN);
		plcOrderBttn.setForeground(Color.BLACK);
		plcOrderBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
		plcOrderBttn.setActionCommand("placeOrder");
		plcOrderBttn.addActionListener(this);
		menuBttnPnl.add(plcOrderBttn);

		orderFrm.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
        	String command = ae.getActionCommand();
	
		if (command.equals("placeOrder")) {
		 orderFrm.dispose();
		 new UserInfo();
		}
		
		if (command.equals("back")) {
		 orderFrm.dispose();
		 new JuicesApocGoods();
		}

		if (command.startsWith("add_")) {
            	 int indexProduct = Integer.parseInt(command.substring(4));
            	 if (Crt.quantities[indexProduct] < 999) {
		  Crt.quantities[indexProduct]++;
		 }
            	 updateProductLabel(indexProduct);
        	} 
		 else if (command.startsWith("remove_")) {
            	  int indexProduct = Integer.parseInt(command.substring(7));
            	  if (Crt.quantities[indexProduct] > 0) {
                   Crt.quantities[indexProduct]--;
            	 }
            updateProductLabel(indexProduct);
        	}
    	}

	public void updateProductLabel(int indexProduct) {
        	Crt.productLabels[indexProduct].setText(" " + Crt.quantities[indexProduct] + " " + Crt.product[indexProduct] + " ($" + Crt.prices[indexProduct] + ") ");
    	}
}
