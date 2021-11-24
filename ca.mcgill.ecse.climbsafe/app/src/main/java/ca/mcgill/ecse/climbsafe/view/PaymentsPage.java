package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import java.awt.*;

public class PaymentsPage implements Page{

    private GroupLayout layout;
    private JPanel panel;

    public PaymentsPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        JLabel label = new JLabel("Payments");
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
