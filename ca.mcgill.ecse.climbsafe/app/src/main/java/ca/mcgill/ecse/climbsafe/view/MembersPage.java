package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/**
 * This class takes care of creating the page (accesible from the sidebar) where the admin can
 * add, update and delete members.
 * The admin can enter all the required information for a member such as:
 * email, password, name, emergency contact, the number of weeks the member desires to climb,
 * if the member requires a guide, if the member requires a hotel.
 *
 * From this page, the admin can see a list of the added members and all their corresponding information.
 *
 * @author Philippe Sarouphim Hochar.
 * @author Theo Ghanem
 */
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
        label = new JLabel("<HTML><B><U>Members</U></B></HTML>");
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
                },
                (email) -> {
                    panel.remove(memberPanel);
                    memberPanel = new MemberPanel(email);

                    makeLayout();
                }
        );
        makeLayout();
    }

    /**
     * This method removes the member panel and creates a new empty one to replace it.
     *
     * @author Philippe Sarouphim Hochar.
     * @author Theo Ghanem
     */
    private void removeMemberPanel(){
        panel.remove(memberPanel);
        memberPanel = new MemberPanel();
        makeLayout();
    }
    /**
     * Makes the layout for the member page:
     * Adds the sidebar with the list of the added members and the panel with the information
     * @author Philippe Sarouphim Hochar
     * @author Theo Ghanem
     */
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
        Consumer<String> select;

        GroupLayout barLayout;
        JList bar;
        JTextField addEmailField;
        JButton addButton;
        JButton deleteButton;
        private JLabel statusLabel;

        public MemberSelector(String[] memberNames, Consumer<String> select, Consumer<String> addEvent){
            this.memberNames = memberNames;
            this.select = select;
            this.statusLabel = new JLabel("");

            bar = new JList(memberNames);
            bar.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()) select.accept(String.valueOf(bar.getSelectedValue()));
                }
            });
            bar.setFont(bar.getFont().deriveFont(Font.PLAIN, 15.0f));
            bar.setFixedCellHeight(30);
            bar.setBorder(BorderFactory.createEmptyBorder());

            addEmailField = new JTextField();
            addButton = new JButton("Add");
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClimbSafeFeatureSet1Controller.deleteMember(String.valueOf(bar.getSelectedValue()));
                    removeSelectedItem();
                    makeNewBar();
                    removeMemberPanel();
                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ClimbSafeFeatureSet2Controller.emailIsValid(addEmailField.getText()); //the email must be valid to be added
                    } catch (Exception ex) {
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                statusLabel.setText("");
                            }
                        }, 5000);
                        statusLabel.setText("Error occurred: " + ex.getMessage());
                        statusLabel.setForeground(Color.red);
                        return;
                    }
                    addEvent.accept(addEmailField.getText());
                }
            });
            makeLayout();
        }

        /**
         * Creates a new sidebar with the list of the added members
         * @author Philippe Sarouphim Hochar
         * @author Theo Ghanem
         */
        public void makeNewBar(){
            remove(bar);
            bar = new JList(memberNames);
            bar.setFont(bar.getFont().deriveFont(Font.PLAIN, 15.0f));
            bar.setFixedCellHeight(30);
            bar.setBorder(BorderFactory.createEmptyBorder());
            bar.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()) select.accept(String.valueOf(bar.getSelectedValue()));
                }
            });
            makeLayout();
        }

        /**
         * Makes the layout for the sidebar for the list of members
         * Adds the sidebar, adds the fields for the emails, adds the add button and delete button
         * @author Philippe Sarouphim Hochar
         * @author Theo Ghanem
         */
        private void makeLayout(){
            barLayout = new GroupLayout(this);
            barLayout.setHorizontalGroup(
                    barLayout.createParallelGroup()
                            .addComponent(bar)
                            .addComponent(addEmailField)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
                            .addComponent(statusLabel)
            );
            barLayout.setVerticalGroup(
                    barLayout.createSequentialGroup()
                            .addComponent(bar)
                            .addComponent(addEmailField)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
                            .addComponent(statusLabel)
            );
            setLayout(barLayout);
            updateUI();
        }

        /**
         * Removes the selected member from the list of added members
         * @author Philippe Sarouphim Hochar
         * @author Theo Ghanem
         */
        private void removeSelectedItem(){
            String[] newMembers = new String[memberNames.length-1];
            int j = 0;
            for(String m: memberNames){
                if(m == bar.getSelectedValue()) continue;
                newMembers[j] = m;
                j++;
            }
            memberNames = newMembers;
        }

        public void addMember(String newMember){
            String[] newMembers = new String[memberNames.length + 1];
            for(int i = 0; i < memberNames.length; i++){
                newMembers[i] = memberNames[i];
            }
            newMembers[newMembers.length - 1] = newMember;
            memberNames = newMembers;
        }

    }

    /**
     * This class calls a method that takes care of creating a member panel where we
     * will be able to see and modify a member's information.
     * It also initializes the necessary java swing components.
     * @author Philippe Sarouphim Hochar
     * @author Theo Ghanem
     */
    class MemberPanel extends JPanel{

        private boolean newMember;
        private String newEmail = "";
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

        private JLabel statusLabel;

        private GroupLayout memberInfoLayout;

        public MemberPanel(){}

        public MemberPanel(String email){
            newMember = true;
            newEmail = email;
            makeLayout();
        }

        public MemberPanel(Member member){
            this.member = member;
            newMember = false;
            makeLayout();
        }

        /**
         * Makes the layout for the information of the member we are adding.
         * Adds the entries for the member's: email, password, name, emergency contact,
         * number of weeks, requires a guide, requires a hotel.
         * Adds JLabels and JTextFields for the corresponding information.
         * @author Philippe Sarouphim Hochar
         * @author Theo Ghanem
         */
        private void makeLayout(){
            email = new JLabel ("Email:");
            password = new JLabel ("Password:");
            name = new JLabel ("Name:");
            emergencyContact = new JLabel ("Emergency Contact:  ");
            nrOfWeeks = new JLabel ("Number of weeks:");
            guideRequired = new JLabel ("Guide Required:  ");
            hotelRequired = new JLabel ("Hotel Required:  ");

            if(newMember){
                enterEmail = new JLabel(newEmail);
                enterEmail.setFont(enterEmail.getFont().deriveFont(Font.PLAIN));
                enterPassword = new JTextField();
                enterName = new JTextField();
                enterEmergencyContact = new JTextField();
                enterNrOfWeeks = new JTextField();
                enterGuideRequired = new JToggleButton("Required");
                enterGuideRequired.setSelected(false);
                enterHotelRequired = new JToggleButton("Required");
                enterHotelRequired.setSelected(false);
            } else {
                enterEmail = new JLabel(member.getEmail());
                enterEmail.setFont(enterEmail.getFont().deriveFont(Font.PLAIN));
                enterPassword = new JTextField(member.getPassword());
                enterName = new JTextField(member.getName());
                enterEmergencyContact = new JTextField(member.getEmergencyContact());
                enterNrOfWeeks = new JTextField(String.valueOf(member.getNrWeeks()));
                enterGuideRequired = new JToggleButton("Required");
                enterGuideRequired.setSelected(member.getGuideRequired());
                enterHotelRequired = new JToggleButton("Required");
                enterHotelRequired.setSelected(member.getHotelRequired());
            }

            statusLabel = new JLabel("");
            statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 15.0f));
            statusLabel.setForeground(Color.RED);

            ArrayList<String> equipmentNames = new ArrayList<>();
            ArrayList<Integer> equipmentQuantities = new ArrayList<>();
            if(!newMember)
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
                    memberInfoLayout.createParallelGroup()
                            .addGroup(memberInfoLayout.createSequentialGroup()
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
                )
                            .addComponent(statusLabel)
            );
            memberInfoLayout.setVerticalGroup(
                    memberInfoLayout.createSequentialGroup()
                                    .addGroup(
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
                                    )
                            .addComponent(statusLabel)
            );
            setLayout(memberInfoLayout);
        }


        /**
         * Method used to add and update the information for a new or existing member.
         * It gets the information provided in the text fields and passes them to the constructor of the member.
         * It also gets the selected items and their respective quantities and passes them to the creation or
         * update of the member.
         * @author Philippe Sarouphim Hochar
         * @author Theo Ghanem
         */
        private void SaveModification(){
            equipmentSelector.getEquipmentQuantities().keySet(); //gets the items that have quantities
            equipmentSelector.getEquipmentQuantities().values(); //gets the quantities of the items
            ArrayList<Integer> quantities = new ArrayList<Integer>();
            quantities.addAll(equipmentSelector.getNonZeroEquipmentQuantities().values());
            ArrayList<String> items = new ArrayList<String>();
            items.addAll(equipmentSelector.getNonZeroEquipmentQuantities().keySet());
            try {
                if (newMember) {
                    ClimbSafeFeatureSet2Controller.registerMember(
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
                    memberSelector.addMember(enterEmail.getText());
                    memberSelector.makeNewBar();
                } else {
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
                }
            } catch(Exception e){
                statusLabel.setText(e.getMessage());
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        statusLabel.setText("");
                    }
                }, 5000);
            }
        }

    }

}