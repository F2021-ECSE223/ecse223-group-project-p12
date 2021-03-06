package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import ca.mcgill.ecse.climbsafe.controller.*;

/**
 * This class outlines the front end interactions and components
 * used in the Assignments page of the application
 * 
 * @author Cedric Barre
 *
 */
public class AssignmentsPage implements Page{

    private GroupLayout 	layout;
    private JPanel 			panel;
    private JLabel 			initiateText;
    private JButton 		initiateButton;
    private JLabel 			errorText;
    private JLabel			viewText;
    private JComboBox		weeksComboBox;
    private JTable			assignmentTable;
    private JScrollPane		scrollPane;
    private JLabel			deleteText;
    private JButton			deleteButton;

    public AssignmentsPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initiateText = new JLabel( "Press here to initate assignments:" );
        deleteText = new JLabel( "In case of an error in the assignments during initiation, "
        		+ "press here to delete all assignments before launching another initiation process" );
        errorText = new JLabel( "" );
        errorText.setForeground(Color.red);
        
        viewText = new JLabel( "View assignments for selected weeks:" );
        ArrayList<String> weekList = new ArrayList<String>();
        for(int i = 1; i <= MiscellaneousController.getSeasonNumberOfWeeks(); i++ ) {
        	weekList.add(String.valueOf(i));
        }
        weeksComboBox = new JComboBox<>(weekList.toArray());
        weeksComboBox.addActionListener(e -> displayNewAssignmentTable());
        
        String[] tableColumns = { "Member",
				  "Member Email",
				  "Guide",
				  "Guide Email",
				  "Start Week",
				  "End Week",
				  "Status"};
        assignmentTable = new JTable( new DefaultTableModel( getAssignmentObjects( 1 ), tableColumns ){

            /**
			 * 
			 */
			private static final long serialVersionUID = -1L;

			@Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        } );
        scrollPane = new JScrollPane(assignmentTable);
        scrollPane.setPreferredSize( new Dimension( (int)Toolkit.getDefaultToolkit()
										.getScreenSize().getWidth() - 400, 200 ) );
        assignmentTable.setFillsViewportHeight(true);
        
        initiateButton = new JButton( "Initiate" );
        initiateButton.addActionListener( e -> buttonPressed() );
        deleteButton = new JButton( "Delete Assignments" );
        deleteButton.addActionListener( e -> deleteAssignments() );
        initComponents();
    }

    /**
     * Method used to display the components of the Assignment page by placing
     * them correctly in the layout of the Panel object
     */
    private void initComponents(){
        panel.setLayout( layout );
        panel.setBackground( Color.WHITE );
        layout.setAutoCreateGaps( true );
        layout.setAutoCreateContainerGaps( true );
        
        layout.setHorizontalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.CENTER )
                        .addComponent( initiateText )
                        .addComponent( initiateButton )
                        .addComponent( errorText )
                        .addGroup( layout.createSequentialGroup()
                        		.addComponent( viewText )
                        		.addComponent( weeksComboBox ) )
                        .addComponent( scrollPane )
                        .addGroup( layout.createSequentialGroup()
                        		.addComponent( deleteText )
                        		.addComponent( deleteButton ) )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent( initiateText )
                        .addComponent( initiateButton )
                        .addComponent( errorText )
                        .addGroup(layout.createParallelGroup()
                        		.addComponent( viewText )
                        		.addComponent( weeksComboBox ))
                        .addComponent( scrollPane )
                        .addGroup( layout.createParallelGroup()
                        		.addComponent( deleteText )
                        		.addComponent( deleteButton ) )
        );
    }

    /**
     * Method used to retrieve the panel used to display the Assignment page
     * content
     */
    @Override
    public JPanel getPanel() {
    	displayNewAssignmentTable();
        return panel;
    }
    
    /**
     * Callback method used when the Initiate Assignment button is pressed.
     * It calls the controller method to initiate the assignment creation process
     */
    private void buttonPressed() {
    	try {
    		AssignmentController.initiateAssignments();
    		errorText.setText("Successfully created assignments for all members");
    		errorText.setForeground(Color.green);
    		displayNewAssignmentTable();
    	} catch ( Exception e ) {
    		errorText.setText("Error occured: " + e.getMessage());
    		errorText.setForeground(Color.red);
    		displayNewAssignmentTable();
    	}
    }
    
    private void deleteAssignments() {
    	MiscellaneousController.deleteAllAssignments();
    	displayNewAssignmentTable();
    	errorText.setText("");
    }
    
    /**
     * Method use to update the assignments in the display table
     */
    private void displayNewAssignmentTable() {
    	int week = weeksComboBox.getSelectedIndex() + 1;
    	DefaultTableModel model = (DefaultTableModel) assignmentTable.getModel();
    	Object[][] list = getAssignmentObjects( week );
    	while( model.getRowCount() != 0 ) {
    		model.removeRow(0);
    	}
    	for( int i = 0; i < list.length; i++ ) {
    		model.addRow(list[i]);
    	}
    	assignmentTable.updateUI();
    }
    
    /**
     * Method used to get all the assignments active during a specified week.
     * 
     * @param week	Integer representing the week number from which we want to get
     * 				all the assignments
     * @return		2D array of objects containing the attributes of each assignment
     * 				from the specified week
     */
    private Object[][] getAssignmentObjects( int week ) {
    	ArrayList<TOAssignment> assignmentList = getAllAssignmentsByWeek( week );
        Object[][] objectList = new Object[assignmentList.size()][11];
        int idx = 0;
        for( TOAssignment a : assignmentList ) {
        	String status = ( MiscellaneousController.getMemberStatus( a.getMemberEmail() ).equals("Banned") )
        						? "Banned" : a.getStatus().split("\\.")[2];
        	objectList[idx][0] = a.getMemberName();
        	objectList[idx][1] = a.getMemberEmail();
        	objectList[idx][2] = a.getGuideName();
        	objectList[idx][3] = a.getGuideEmail();
        	objectList[idx][4] = a.getStartWeek();
        	objectList[idx][5] = a.getEndWeek();
        	objectList[idx][6] = status;
        	idx++;
        }
        return objectList;
    }
    
    /**
     * Method used to sift through all the assignments to get only the assignments
     * active during the specified week
     * 
     * @param week	Integer representing the week number from which we want to get
     * 				all the assignments
     * @return		ArrayList containing all the assignments from the specified week
     * 				in a transfer object format
     */
    private ArrayList<TOAssignment> getAllAssignmentsByWeek( int week ) {
		ArrayList<TOAssignment> list = new ArrayList<TOAssignment>();
		
		for( TOAssignment a : ClimbSafeFeatureSet6Controller.getAssignments() ) {
			if( week >= a.getStartWeek() && week <= a.getEndWeek() ) {
				list.add(a);
			}
		}
		return list;
	}
}
