package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

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

		JPanel panel = new JPanel();
		panel.add(new JLabel("hi"));

		JFrame frame = new JFrame();
		frame.add(panel);
	    
	    layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		   	  .addGroup(layout.createParallelGroup()
		   			  .addComponent(sideBar))
		      .addGroup(layout.createParallelGroup()
		    		  .addComponent(windowTitle)
		    		  .addComponent(titleSeparator)
					  .addComponent(frame)
			  )
		);
		layout.setVerticalGroup(
		   layout.createParallelGroup()
		      .addGroup(layout.createSequentialGroup()
		    		  .addComponent(sideBar))
		      .addGroup(layout.createSequentialGroup()
		    		  .addComponent(windowTitle)
		    		  .addComponent(titleSeparator)
					  .addComponent(frame)
			  )
		);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ClimbSafe Application");
		pack();
		setVisible(true);
	}

}