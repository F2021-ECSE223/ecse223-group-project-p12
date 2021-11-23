package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;

public class ClimbSafeGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// Title
	private JLabel windowTitle;
	private JSeparator titleSeparator = new JSeparator();
	
	JList<String> sideBar = new JList<String>(new String[]{"Season", "Guides", "Members", "Equipment", 
			"Equipment Bundle", "Assignment", "Tool"});
	
	public ClimbSafeGUI() {
		initComponents();
	}
	
	private void initComponents() {
		setBackground(Color.white);
		windowTitle = new JLabel("Welcome to ClimbSafe");
		windowTitle.setFont(new Font("Corbel Light", Font.PLAIN, 30));
		
		GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    getContentPane().setBackground(Color.WHITE);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		   	  .addGroup(layout.createParallelGroup()
		   			  .addComponent(sideBar))
		      .addGroup(layout.createParallelGroup()
		    		  .addComponent(windowTitle)
		    		  .addComponent(titleSeparator))
		);
		layout.setVerticalGroup(
		   layout.createParallelGroup()
		      .addGroup(layout.createSequentialGroup()
		    		  .addComponent(sideBar))
		      .addGroup(layout.createSequentialGroup()
		    		  .addComponent(windowTitle)
		    		  .addComponent(titleSeparator))
		);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ClimbSafe Application");
		pack();
		setVisible(true);
	}

}