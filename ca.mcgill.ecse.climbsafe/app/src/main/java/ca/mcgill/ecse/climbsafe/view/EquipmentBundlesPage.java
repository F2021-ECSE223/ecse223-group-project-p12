package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import java.awt.*;

public class EquipmentBundlesPage implements Page{

    private GroupLayout layout;
    private JPanel panel;

    public EquipmentBundlesPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        JLabel label = new JLabel("Equipment Bundles");
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

}
