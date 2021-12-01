package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class outlines the front end interactions and components
 * used in the Payments page of the application
 * 
 * @author Cedric Barre
 *
 */
public class PaymentsPage implements Page{

    private GroupLayout 	layout;
    private JPanel			panel;
    private JTable			paymentTable;
    private JScrollPane		scrollPane;
    private JButton			payButton;
    private JTextField		codeField;
    private JLabel			payButtonText;
    private JLabel			codeFieldText;
    private JLabel			description;
    private JLabel			error;

    public PaymentsPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        
        payButton = new JButton("Pay");
        payButton.addActionListener( e -> payAction() );
        codeField = new JTextField();
		payButtonText = new JLabel("Enter the authorization code: ");
        codeFieldText = new JLabel("Press here to proceed to payment");
        description = new JLabel("Select a row of the table and enter the authorization code to pay for the trip");
        error = new JLabel("");
        error.setForeground(Color.red);
        
        String[] columns = {"Member",
        					"Member Email",
        					"Guide Cost",
        					"Equipment Cost",
        					"Total Price",
        					"Authorization Code",
        					"Payment Status",
        					"Refund Percentage"};
        
        paymentTable = new JTable( new DefaultTableModel( getPaymentObjects(), columns ){

            /**
			 * 
			 */
			private static final long serialVersionUID = -1L;

			@Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        } );
        
        scrollPane = new JScrollPane(paymentTable);
        scrollPane.setPreferredSize( new Dimension( (int)Toolkit.getDefaultToolkit()
        											.getScreenSize().getWidth() - 400, 200 ) );
        paymentTable.setFillsViewportHeight(true);
        
        initComponents();
    }

    /**
     * Method used to display the components of the Payment page by placing
     * them correctly in the layout of the Panel object
     */
    private void initComponents(){
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.CENTER )
                		.addComponent(description)
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                        		.addComponent(payButtonText)
                        		.addComponent(codeField))
                        .addGroup(layout.createSequentialGroup()
                        		.addComponent(codeFieldText)
                        		.addComponent(payButton))
                        .addComponent(error)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                		.addComponent(description)
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup()
                        		.addComponent(payButtonText)
                        		.addComponent(codeField))
                        .addGroup(layout.createParallelGroup()
                        		.addComponent(codeFieldText)
                        		.addComponent(payButton))
                        .addComponent(error)
        );
    }

    /**
     * Method used to retrieve the panel used to display the Payment page
     * content
     */
    @Override
    public JPanel getPanel() {
    	updateTable();
        return panel;
    }

    /**
     * Method used to get all the data for the payment table. It looks at all the
     * initiated assignments and displays all the sattes related to expenses and
     * payments
     * 
     * @return		2D array of objects containing the attributes relating to
     * 				expenses and payment for each assignment
     */
    private Object[][] getPaymentObjects() {
    	ArrayList<TOAssignment> assignmentList = (ArrayList<TOAssignment>) ClimbSafeFeatureSet6Controller.getAssignments();
        Object[][] objectList = new Object[assignmentList.size()][8];
        int idx = 0;
        for( TOAssignment a : assignmentList ) {
        	int totalPrice = a.getTotalCostForEquipment() + a.getTotalCostForGuide();
        	String paymentStatus = "";
        	for(String s : a.getStatus().split("\\.")){
        		if(s.equals("NotPaid")){
        	        paymentStatus = "Not Paid";
        	    } else if( s.equals("Paid") ) {
        	    	paymentStatus = "Paid";
        	    }
        	}
        	objectList[idx][0] = a.getMemberName();
        	objectList[idx][1] = a.getMemberEmail();
        	objectList[idx][2] = a.getTotalCostForGuide();
        	objectList[idx][3] = a.getTotalCostForEquipment();
        	objectList[idx][4] = totalPrice;
        	objectList[idx][5] = a.getAuthorizationCode();
        	objectList[idx][6] = paymentStatus;
        	objectList[idx][7] = a.getRefundedPercentageAmount();
        	idx++;
        }
        return objectList;
    }
    
    /**
     * Method use to update the payment information in the display table
     */
    private void updateTable() {
    	DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
    	Object[][] list = getPaymentObjects();
    	while( model.getRowCount() != 0 ) {
    		model.removeRow(0);
    	}
    	for( int i = 0; i < list.length; i++ ) {
    		model.addRow(list[i]);
    	}
    	paymentTable.updateUI();
    }
    
    /**
     * Callback method for the pay button which pays for a specific member's trip
     */
    private void payAction() {
    	String code = codeField.getText();
    	int row = paymentTable.getSelectedRow();
    	error.setText("");
    	
    	if( code.equals("") ) {
    		error.setText("The authorization code field is empty! Make sure to supply a code before paying");
    		error.setForeground(Color.red);
    		return;
    	}
    	if( row == -1 ) {
    		error.setText("No trips selected from the table! Make sure to select a trip in order to pay");
    		error.setForeground(Color.red);
    		return;
    	}
    	
    	String email = (String) paymentTable.getModel().getValueAt( row, 1 );
    	try {
			AssignmentController.payTrip(email, code);
			error.setText("Payment Successful");
			error.setForeground(Color.green);
			updateTable();
		} catch (InvalidInputException e) {
			error.setText("Error occured: " + e.getMessage());
			error.setForeground(Color.red);
		}
    	
    }
}
