package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class ClimbSafeGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel windowTitle;
	private JSeparator titleSeparator = new JSeparator();
	private JPanel contentPanel = new JPanel();
	
	private LinkedHashMap<String, Page> list = new LinkedHashMap<String, Page>();
	private SideBar sideBar;
	
	public ClimbSafeGUI() {
		initData();
		initComponents();
	}
	
	private void initData() {
		list.put("Seasons", new SeasonsPage());
		list.put("Members", new MembersPage());
		list.put("Guides", new GuidesPage());
		list.put("Equipments", new EquipmentsPage());
		list.put("Equipment Bundles", new EquipmentBundlesPage());
		list.put("Payments", new PaymentsPage());
		list.put("Trips", new TripsPage());
		sideBar = new SideBar(list, this);
	}
	
	private void initComponents() {
		setBackground(Color.white);
		windowTitle = new JLabel("Welcome to ClimbSafe");
		windowTitle.setFont(new Font("Corbel Light", Font.PLAIN, 30));
		
		contentPanel.setBackground(Color.white);
		
		GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    getContentPane().setBackground(Color.WHITE);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		   	  .addGroup(layout.createParallelGroup()
		   			  .addComponent(sideBar.getSideBar()))
		      .addGroup(layout.createParallelGroup()
		    		  .addComponent(windowTitle)
		    		  .addComponent(titleSeparator)
					  .addComponent(contentPanel))
		);
		layout.setVerticalGroup(
		   layout.createParallelGroup()
		      .addGroup(layout.createSequentialGroup()
		    		  .addComponent(sideBar.getSideBar()))
		      .addGroup(layout.createSequentialGroup()
		    		  .addComponent(windowTitle)
		    		  .addComponent(titleSeparator)
					  .addComponent(contentPanel)
			  )
		);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ClimbSafe Application");
		pack();
		setVisible(true);
		
	}
	
	public void setPagePanel(JPanel panel) {
		contentPanel.removeAll();
		contentPanel.add(panel);
		pack();
	}
}