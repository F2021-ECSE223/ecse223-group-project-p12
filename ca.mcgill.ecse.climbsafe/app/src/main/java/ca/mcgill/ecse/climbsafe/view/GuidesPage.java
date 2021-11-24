package ca.mcgill.ecse.climbsafe.view;


import javax.swing.*;
import java.awt.*;

public class GuidesPage implements Page {

    private GroupLayout layout;
    private JPanel panel;

    public GuidesPage(){
    	panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        JButton button = new JButton("Button");
        panel.setLayout(layout);
	    panel.setBackground(Color.WHITE);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(button)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(button)
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
