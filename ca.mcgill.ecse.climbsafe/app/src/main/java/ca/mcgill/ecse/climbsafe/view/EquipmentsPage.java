package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Equipment;

import javax.swing.*;
import java.awt.*;

public class EquipmentsPage implements Page {

    private GroupLayout layout;
    private JPanel panel;

    private JLabel titleLabel;

    private Table customTable;

    private JLabel statusLabel;

    public EquipmentsPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        titleLabel = new JLabel("Equipments");
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f).deriveFont(Font.PLAIN));
        statusLabel = new JLabel("");
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 15.0f));
        statusLabel.setForeground(Color.RED);
        customTable = new Table(new String[]{ "Name", "Weight", "Price" }, true,
            (object) -> {
                try{
                    ClimbSafeFeatureSet4Controller.addEquipment(
                        object[0].toString(),
                        Integer.parseInt(object[1].toString()),
                        Integer.parseInt(object[2].toString())
                    );
                } catch(Exception e){
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            },
            (i, data) -> {
                try{
                    ClimbSafeFeatureSet4Controller.updateEquipment(
                            data[0][0].toString(),
                            data[1][0].toString(),
                            Integer.parseInt(data[1][1].toString()),
                            Integer.parseInt(data[1][2].toString())
                    );
                } catch(Exception e){
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            },
            (i, object) -> {
                try {
                    ClimbSafeFeatureSet6Controller.deleteEquipment(object[0].toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            },
                statusLabel
        );
        for(Equipment e: ClimbSafeApplication.getClimbSafe().getEquipment()){
            customTable.addRow(new Object[]{ e.getName(), e.getWeight(), e.getPricePerWeek() });
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
