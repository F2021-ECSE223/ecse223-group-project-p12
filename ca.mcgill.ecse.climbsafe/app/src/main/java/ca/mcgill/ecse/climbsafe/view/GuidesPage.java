 package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;
import ca.mcgill.ecse.climbsafe.model.Guide;
    
    /**
     * This class takes care of creating the page (accesible from the sidebar) where the admin can
     * add, update and delete guides.
     * The admin can enter all the required information for a member such as:
     * email, password, name, emergency contact.
     * From this page, the admin can see a list of the added guides and all their corresponding information.
     * 
     * @author Chris Hatoum
     *
     */

public class GuidesPage implements Page {

    private GroupLayout layout;
    private JPanel panel;
    private JLabel titleLabel;
    private Table customTable;

    private JLabel statusLabel;

    public GuidesPage(){
    	panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }
    
/**
 *  Method used to display the components of the Guide's page by placing
 *  them correctly in the Table
 */
        private void initComponents(){
            titleLabel = new JLabel("<HTML><B><U>Guides</U></B></HTML>");
            titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f).deriveFont(Font.PLAIN));
            statusLabel = new JLabel("");
            statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 15.0f));
            statusLabel.setForeground(Color.RED);
            customTable = new Table(new String[]{ "Email", "Password", "Name", "EmergencyContact" }, false,
                (object) -> {
                    try{
                        ClimbSafeFeatureSet3Controller.registerGuide(
                            object[0].toString(),
                            object[1].toString(),
                            object[2].toString(),
                            object[3].toString()
                        );
                    } catch(Exception e){
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                },
                (i, data) -> {
                    try{
                        ClimbSafeFeatureSet3Controller.updateGuide(
                                data[0][0].toString(),
                                data[1][1].toString(),
                                data[1][2].toString(),
                                data[1][3].toString()
                                
                        );
                    } catch(Exception e){
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                },
                (i, object) -> {
                    try {
                        ClimbSafeFeatureSet1Controller.deleteGuide(object[0].toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                },
                    statusLabel
            );
            for(Object[] guide: MiscellaneousController.getGuidesAsObjectArrays()){
                customTable.addRow(guide);
            }

            panel.setLayout(layout);
            panel.setBackground(Color.WHITE);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addComponent(titleLabel)
                    .addComponent(customTable)
                    .addComponent(statusLabel)
            );
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(titleLabel)
                    .addComponent(customTable)
                    .addComponent(statusLabel)
            );
        }


    @Override
    public JPanel getPanel() {
        return panel;
    }
}
