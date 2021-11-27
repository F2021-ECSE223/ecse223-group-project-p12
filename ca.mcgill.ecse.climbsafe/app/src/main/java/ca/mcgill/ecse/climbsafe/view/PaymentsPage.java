package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;

import java.awt.*;
import java.util.ArrayList;

public class PaymentsPage implements Page{

    private GroupLayout 	layout;
    private JPanel			panel;
    private JTable			paymentTable;
    private JScrollPane		scrollPane;
    private JButton			payButton;
    private JTextField		codeField;
    private JLabel			payButtonText;
    private JLabel			codeFieldText;

    public PaymentsPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        
        payButton = new JButton("Pay");
        codeField = new JTextField();
        payButtonText = new JLabel("Enter the authorization code: ");
        codeFieldText = new JLabel("Press here to proceed to payment");
        
        String[] columns = {"Member",
        					"Total Price",
        					"Refund Percentage",
        					"Final Price",
        					"Payment Status"};
        
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
        scrollPane.setPreferredSize( new Dimension( 1200, 200 ) );
        paymentTable.setFillsViewportHeight(true);
        
        initComponents();
    }

    private void initComponents(){
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup( GroupLayout.Alignment.CENTER )
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                        		.addComponent(codeFieldText)
                        		.addComponent(codeField))
                        .addGroup(layout.createSequentialGroup()
                        		.addComponent(payButtonText)
                        		.addComponent(payButton))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup()
                        		.addComponent(codeFieldText)
                        		.addComponent(codeField))
                        .addGroup(layout.createParallelGroup()
                        		.addComponent(payButtonText)
                        		.addComponent(payButton))
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    private Object[][] getPaymentObjects() {
    	ArrayList<TOAssignment> assignmentList = (ArrayList<TOAssignment>) ClimbSafeFeatureSet6Controller.getAssignments();
        Object[][] objectList = new Object[assignmentList.size()][5];
        int idx = 0;
        for( TOAssignment a : assignmentList ) {
        	int totalPrice = a.getTotalCostForEquipment() + a.getTotalCostForGuide();
        	objectList[idx][0] = a.getMemberName();
        	objectList[idx][1] = totalPrice;
        	objectList[idx][2] = a.getRefundedPercentageAmount();
        	objectList[idx][3] = totalPrice*( 1 - a.getRefundedPercentageAmount() / 100 );
        	objectList[idx][4] = "Not Paid";
        	idx++;
        }
        return objectList;
    }
    
    private void payAction() {
    	
    }
}
