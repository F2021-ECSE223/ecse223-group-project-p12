package ca.mcgill.ecse.climbsafe.view;


import javax.swing.*;
import java.awt.*;

public class GuidesPage implements Page {

    public GroupLayout layout;

    public GuidesPage(Container container){
        layout = new GroupLayout(container);
        initComponents();
    }

    private void initComponents(){
        JButton button = new JButton("Button");
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
    public GroupLayout getLayout() {
        return layout;
    }
}
