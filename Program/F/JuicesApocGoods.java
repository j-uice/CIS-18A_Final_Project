package F;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// to compile and run in terminal, copy below
// javac F/*.java && java F.JuicesApocGoods

public class JuicesApocGoods implements ActionListener {  // Implement ActionListener
	private JFrame mainMenuFrm;
	private JPanel backgroundPnl;

    JuicesApocGoods() {
        // Create the main menu Frame
        mainMenuFrm = new JFrame("Juice's Apocalyptic Goods");
        mainMenuFrm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrm.setLayout(new BorderLayout());
        mainMenuFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background panel for decor
        backgroundPnl = new JPanel();
        backgroundPnl.setBackground(Color.BLACK);
        backgroundPnl.setLayout(new BorderLayout());

        // menu panel to add title to the top
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setBackground(Color.BLACK);
        
	JLabel mainMenuLbl = new JLabel("WELCOME TO JUICE'S APOCALYPTIC GOODS ONLINE DELIVERY!");
        mainMenuLbl.setForeground(Color.GREEN);
        mainMenuLbl.setFont(new Font("Monospaced", Font.BOLD, 40));
        menuPanel.add(mainMenuLbl);

        // Add the title panel to the frame and set background as the frame's content pane
        backgroundPnl.add(menuPanel, BorderLayout.NORTH);
        mainMenuFrm.setContentPane(backgroundPnl);
	
        // this button takes the user to the list-of-goods window
        JButton orderBttn = new JButton("ORDER GOODS");
	orderBttn.setBackground(Color.GREEN);
	orderBttn.setForeground(Color.BLACK);
	orderBttn.setFont(new Font("Monospaced", Font.BOLD, 40));
        orderBttn.addActionListener(this); // Add action listener
	
	// this button takes the user to a services-to-hire window
	JButton serviceBttn = new JButton("HIRE SERVICES");
	serviceBttn.setBackground(Color.GREEN);
	serviceBttn.setForeground(Color.BLACK);
	serviceBttn.setFont(new Font("Monospaced", Font.BOLD, 35));
	serviceBttn.addActionListener(this);
	
	// a panel to organize the buttons
	JPanel buttonsPnl = new JPanel();
	buttonsPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
	buttonsPnl.setBackground(Color.BLACK);
	
	// adding buttons in button panel to display at the bottom
	buttonsPnl.add(orderBttn);
	buttonsPnl.add(serviceBttn);
	mainMenuFrm.add(buttonsPnl, BorderLayout.SOUTH);
	

        // Display the frame
        mainMenuFrm.setVisible(true);
    } 

    // when the Order Goods button gets pressed, 
    // the current window closes and the list-of-goods window opens
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("ORDER GOODS")) {
		mainMenuFrm.dispose(); // Close the current menu frame
		new OrderFrame();    // Open the new frame
        }

	if(ae.getActionCommand().equals("HIRE SERVICES")) {
		JOptionPane.showMessageDialog(mainMenuFrm, "Hiring Services will be coming soon.\n Try again some other time!", "Coming Soon", JOptionPane.INFORMATION_MESSAGE);
	}
    }

    public static void main(String[] args) {
        // Create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JuicesApocGoods();
            }
        });
    }
}
