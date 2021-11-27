package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MembersPage implements Page{

    private GroupLayout layout;
    private JPanel panel;

    private JLabel label;

    private MemberPanel memberPanel;
    private MemberSelector memberSelector;

    public MembersPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        label = new JLabel("<HTML><U>Members</U></HTML>");
        String[] memberNames = new String[ClimbSafeApplication.getClimbSafe().getMembers().size()];
        for(int i = 0; i < ClimbSafeApplication.getClimbSafe().getMembers().size(); i++){
            memberNames[i] = ClimbSafeApplication.getClimbSafe().getMember(i).getEmail();
        }
        memberPanel = new MemberPanel();
        memberSelector = new MemberSelector(memberNames,
                (selected) -> {
                    panel.remove(memberPanel);
                    memberPanel = new MemberPanel(ClimbSafeApplication.getClimbSafe().findMemberFromEmail(selected));
                    makeLayout();
                }
        );
        makeLayout();
    }

    private void makeLayout(){
        layout = new GroupLayout(panel);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(label)
                                .addComponent(memberSelector)
                        )
                        .addComponent(memberPanel)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label)
                                .addComponent(memberSelector)
                        )
                        .addComponent(memberPanel)
        );
        panel.setLayout(layout);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    class MemberSelector extends JPanel{

        String[] memberNames;

        GroupLayout barLayout;
        JList bar;

        public MemberSelector(String[] memberNames, Consumer<String> select){
            this.memberNames = memberNames;

            bar = new JList(memberNames);
            bar.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()) select.accept(String.valueOf(bar.getSelectedValue()));
                }
            });

            barLayout = new GroupLayout(this);
            barLayout.setHorizontalGroup(barLayout.createSequentialGroup().addComponent(bar));
            barLayout.setVerticalGroup(barLayout.createSequentialGroup().addComponent(bar));
            setLayout(barLayout);
        }

    }



    class MemberPanel extends JPanel{

        private Member member;

        private JLabel email;
        private JLabel password;
        private JLabel name;
        private JLabel emergencyContact;
        private JLabel nrOfWeeks;
        private JLabel guideRequired;
        private JLabel hotelRequired;

        private JLabel enterEmail;
        private JTextField enterPassword;
        private JTextField enterName;
        private JTextField enterEmergencyContact;
        private JTextField enterNrOfWeeks;
        private JToggleButton enterGuideRequired;
        private JToggleButton enterHotelRequired;

        private EquipmentSelector equipmentSelector;

        private JButton saveButton;

        private GroupLayout memberInfoLayout;

        public MemberPanel(){}

        public MemberPanel(Member member){
            this.member = member;

            email = new JLabel ("Email:");
            password = new JLabel ("Password:");
            name = new JLabel ("Name:");
            emergencyContact = new JLabel ("Emergency Contact:");
            nrOfWeeks = new JLabel ("Number of weeks:");
            guideRequired = new JLabel ("Guide Required:");
            hotelRequired = new JLabel ("Hotel Required:");

            enterEmail = new JLabel (member.getEmail());
            enterEmail.setFont(enterEmail.getFont().deriveFont(Font.PLAIN));
            enterPassword = new JTextField (member.getPassword());
            enterName = new JTextField (member.getName());
            enterEmergencyContact = new JTextField (member.getEmergencyContact());
            enterNrOfWeeks = new JTextField (String.valueOf(member.getNrWeeks()));
            enterGuideRequired = new JToggleButton("Required");
            enterGuideRequired.setSelected(member.getGuideRequired());
            enterHotelRequired = new JToggleButton("Required");
            enterHotelRequired.setSelected(member.getHotelRequired());

            ArrayList<String> equipmentNames = new ArrayList<>();
            ArrayList<Integer> equipmentQuantities = new ArrayList<>();
            for(BookedItem b: member.getBookedItems()){
                equipmentNames.add(b.getItem().getName());
                equipmentQuantities.add(b.getQuantity());
            }
            equipmentSelector = new EquipmentSelector(equipmentNames, equipmentQuantities);
            equipmentSelector.setBorder(new EmptyBorder(0, 100, 0, 0));

            saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SaveModification();
                }
            });

            memberInfoLayout = new GroupLayout(this);
            memberInfoLayout.setHorizontalGroup(
                    memberInfoLayout.createSequentialGroup()
                            .addGroup(
                                memberInfoLayout.createParallelGroup()
                                        .addComponent(email)
                                        .addComponent(password)
                                        .addComponent(name)
                                        .addComponent(emergencyContact)
                                        .addComponent(nrOfWeeks)
                                        .addComponent(guideRequired)
                                        .addComponent(hotelRequired)
                                        .addComponent(saveButton)
                            )
                            .addGroup(
                                    memberInfoLayout.createParallelGroup()
                                            .addComponent(enterEmail)
                                            .addComponent(enterPassword)
                                            .addComponent(enterName)
                                            .addComponent(enterEmergencyContact)
                                            .addComponent(enterNrOfWeeks)
                                            .addComponent(enterGuideRequired)
                                            .addComponent(enterHotelRequired)
                            )
                            .addComponent(equipmentSelector)
            );
            memberInfoLayout.setVerticalGroup(
                    memberInfoLayout.createParallelGroup()
                                    .addGroup(
                                        memberInfoLayout.createSequentialGroup()
                                                .addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(email)
                                                                .addComponent(enterEmail)
                                                )
                                                .addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(password)
                                                                .addComponent(enterPassword)
                                                )
                                                .addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(name)
                                                                .addComponent(enterName)
                                                )
                                                .addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(emergencyContact)
                                                                .addComponent(enterEmergencyContact)
                                                )
                                                .addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(nrOfWeeks)
                                                                .addComponent(enterNrOfWeeks)
                                                )
                                                .addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(guideRequired)
                                                                .addComponent(enterGuideRequired)
                                                ).addGroup(
                                                        memberInfoLayout.createParallelGroup()
                                                                .addComponent(hotelRequired)
                                                                .addComponent(enterHotelRequired)
                                                )
                                                .addComponent(saveButton)
                                    )
                            .addComponent(equipmentSelector)
            );
            setLayout(memberInfoLayout);

        }
        private void SaveModification(){
            equipmentSelector.getEquipmentQuantities().keySet(); //gets the items that have quantities
            equipmentSelector.getEquipmentQuantities().values(); //gets the quantities of the items
            ArrayList<Integer> quantities = new ArrayList<Integer>();
            quantities.addAll(equipmentSelector.getEquipmentQuantities().values());
            ArrayList<String> items = new ArrayList<String>();
            items.addAll(equipmentSelector.getEquipmentQuantities().keySet());
            try {
                ClimbSafeFeatureSet2Controller.updateMember(
                        enterEmail.getText(),
                        enterPassword.getText(),
                        enterName.getText(),
                        enterEmergencyContact.getText(),
                        Integer.parseInt(enterNrOfWeeks.getText()),
                        enterGuideRequired.isSelected(),
                        enterHotelRequired.isSelected(),
                        items,
                        quantities
                );
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}