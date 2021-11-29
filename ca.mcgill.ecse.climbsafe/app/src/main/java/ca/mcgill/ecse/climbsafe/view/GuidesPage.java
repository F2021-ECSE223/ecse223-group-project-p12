 package ca.mcgill.ecse.climbsafe.view;

    import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.model.Guide;

import javax.swing.*;
    import java.awt.*;

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
    

        private void initComponents(){
            titleLabel = new JLabel("Guides");
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
            for(Guide e: ClimbSafeApplication.getClimbSafe().getGuides()){
                customTable.addRow(new Object[]{ e.getEmail(),e.getPassword(), e.getName(), e.getEmergencyContact() });
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
