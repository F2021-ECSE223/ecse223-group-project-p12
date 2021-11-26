package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class MembersPage implements Page{

    private GroupLayout layout;
    private JPanel panel;

    public MembersPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        JLabel label = new JLabel("Members");
        /*String[] memberNames = new String[ClimbSafeApplication.getClimbSafe().getMembers().size()];
        for(int i = 0; i < ClimbSafeApplication.getClimbSafe().getMembers().size(); i++){
            memberNames[i] = ClimbSafeApplication.getClimbSafe().getMember(i).getEmail();
        }*/
        String[] memberNames = new String[]{ "Member 1", "Member 2", "Member 3" };
        MemberSelector memberSelector = new MemberSelector(memberNames);
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label)
                        .addComponent(memberSelector)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(memberSelector)
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    class MemberSelector extends JPanel{

        String[] memberNames;

        GroupLayout barLayout;
        JList bar;

        public MemberSelector(String[] memberNames){
            this.memberNames = memberNames;

            bar = new JList(memberNames);
            bar.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                }
            });

            barLayout = new GroupLayout(this);
            barLayout.setHorizontalGroup(barLayout.createSequentialGroup().addComponent(bar));
            barLayout.setVerticalGroup(barLayout.createSequentialGroup().addComponent(bar));
            setLayout(barLayout);
        }

    }

    class MemberPanel extends JPanel{

    }

    class EquipmentSelector extends JPanel{

        public EquipmentSelector(String[] equipments){

        }

    }

}