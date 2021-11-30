package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class ClimbSafeGUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private JButton quit;


	private JLabel windowTitle;
	private JLabel empty;
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
		list.put("Assignments", new AssignmentsPage());
		list.put("Payments", new PaymentsPage());
		list.put("Trips", new TripsPage());
		sideBar = new SideBar(list, this);
	}

	private void initComponents() {
		setBackground(Color.white);
		windowTitle = new JLabel("Welcome to ClimbSafe");
		windowTitle.setFont(new Font("Corbel Light", Font.PLAIN, 30));
		quit = new JButton("Exit Application");
		quit.addActionListener(e -> {
			this.dispose();
		});

		empty = new JLabel(" ");
		empty.setFont(new Font("Corbel Light", Font.PLAIN, 400));

		contentPanel.setBackground(Color.white);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		getContentPane().setBackground(Color.WHITE);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(sideBar.getSideBar())
								.addComponent(empty)
								.addComponent(quit))
						.addGroup(layout.createParallelGroup()
								.addComponent(windowTitle)
								.addComponent(titleSeparator)
								.addComponent(contentPanel)
						)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(sideBar.getSideBar())
								.addComponent(empty)
								.addComponent(quit)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(windowTitle)
								.addComponent(titleSeparator)
								.addComponent(contentPanel)

						)
		);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				ClimbSafePersistence.save();
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
		});
		setTitle("ClimbSafe Application");
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void setPagePanel(JPanel panel) {
		contentPanel.removeAll();
		contentPanel.add(panel);
		contentPanel.updateUI();
	}
}