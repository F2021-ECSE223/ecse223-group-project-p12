package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.Map;

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
        MemberPanel memberPanel = new MemberPanel(new Member("member@email.com", "password", "John Doe", "+1 (234) 567-8910", 6, true, false, ClimbSafeApplication.getClimbSafe()));
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label)
                        .addComponent(memberSelector)
                        .addComponent(memberPanel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(memberSelector)
                        .addComponent(memberPanel)
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

        private Member member;

        private JLabel email;
        private JLabel password;
        private JLabel name;
        private JLabel emergencyContact;
        private JLabel nrOfWeeks;
        private JLabel guideRequired;
        private JLabel hotelRequired;

        private JTextField enterEmail;
        private JTextField enterPassword;
        private JTextField enterName;
        private JTextField enterEmergencyContact;
        private JTextField enterNrOfWeeks;
        private JToggleButton enterGuideRequired;
        private JToggleButton enterHotelRequired;

        private EquipmentSelector equipmentSelector;

        private GroupLayout memberInfoLayout;

        public MemberPanel(Member member){
            this.member = member;

            email = new JLabel ("Email:");
            password = new JLabel ("Password:");
            name = new JLabel ("Name:");
            emergencyContact = new JLabel ("Emergency Contact:");
            nrOfWeeks = new JLabel ("Number of weeks:");
            guideRequired = new JLabel ("Guide Required:");
            hotelRequired = new JLabel ("Hotel Required:");

            enterEmail = new JTextField (member.getEmail());
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

    class EquipmentSelector extends JPanel{

        private java.util.List<String> equipmentNames;
        private java.util.List<Integer> quantities;

        private GroupLayout equipmentLayout;

        private JLabel titleLabel;
        private EquipmentSelectorItem[] items;

        public EquipmentSelector(java.util.List<String> equipmentNames, java.util.List<Integer> quantities){
            this.equipmentNames = new ArrayList<String>();
            this.quantities = new ArrayList<Integer>();
            for(Equipment e: MiscellaneousController.getEquipmentList()) this.equipmentNames.add(e.getName());
            for(String en: this.equipmentNames){
                int i = equipmentNames.indexOf(en);
                if(i < 0) this.quantities.add(0);
                else this.quantities.add(quantities.get(i));
            }

            titleLabel = new JLabel("Equipment Selector");
            items = new EquipmentSelectorItem[this.equipmentNames.size()];

            equipmentLayout = new GroupLayout(this);
            GroupLayout.ParallelGroup horizontalGroup = equipmentLayout.createParallelGroup();
            GroupLayout.SequentialGroup verticalGroup = equipmentLayout.createSequentialGroup();
            horizontalGroup.addComponent(titleLabel);
            verticalGroup.addComponent(titleLabel);
            for(int i = 0; i < this.equipmentNames.size(); i++){
                items[i] = new EquipmentSelectorItem(this.equipmentNames.get(i), this.quantities.get(i));
                horizontalGroup.addComponent(items[i]);
                verticalGroup.addComponent(items[i]);
            }
            equipmentLayout.setHorizontalGroup(horizontalGroup);
            equipmentLayout.setVerticalGroup(verticalGroup);

            setLayout(equipmentLayout);
        }

        private Map<String, Integer> getEquipmentQuantities(){
            Map<String, Integer> equipmentQuantities = new LinkedHashMap<>();
            for(EquipmentSelectorItem i: items){
                equipmentQuantities.put(i.getEquipmentName(), i.getQuantity());
            }
            return equipmentQuantities;
        }

        class EquipmentSelectorItem extends JPanel{

            private String equipmentName;
            private int quantity;

            private GroupLayout itemLayout;
            private JLabel nameLabel;
            private JButton decrementButton;
            private JLabel quantityLabel;
            private JButton incrementButton;

            public EquipmentSelectorItem(String equipmentName, int quantity){
                this.equipmentName = equipmentName;
                this.quantity = quantity;

                nameLabel = new JLabel(equipmentName);
                nameLabel.setMaximumSize(new Dimension(200, 30));
                nameLabel.setPreferredSize(new Dimension(200, 30));
                nameLabel.setMaximumSize(new Dimension(200, 30));
                decrementButton = new JButton("-");
                quantityLabel = new JLabel(String.valueOf(quantity));
                quantityLabel.setMaximumSize(new Dimension(50, 30));
                quantityLabel.setPreferredSize(new Dimension(50, 30));
                quantityLabel.setMaximumSize(new Dimension(50, 30));
                incrementButton = new JButton("+");

                itemLayout = new GroupLayout(this);
                itemLayout.setHorizontalGroup(
                        itemLayout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addComponent(decrementButton)
                                .addComponent(quantityLabel)
                                .addComponent(incrementButton)
                );
                itemLayout.setVerticalGroup(
                        itemLayout.createParallelGroup()
                                .addComponent(nameLabel)
                                .addComponent(decrementButton)
                                .addComponent(quantityLabel)
                                .addComponent(incrementButton)
                );

                incrementButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addToQuantity(1);
                    }
                });
                decrementButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addToQuantity(-1);
                    }
                });
                setLayout(itemLayout);
            }

            private void addToQuantity(int i){
                quantity += i;
                if(quantity < 0) quantity = 0;
                quantityLabel.setText(String.valueOf(quantity));
            }

            public String getEquipmentName(){
                return equipmentName;
            }

            public int getQuantity(){
                return quantity;
            }

        }

    }

}