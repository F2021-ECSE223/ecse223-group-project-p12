package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.view.MembersPage.MemberPanel;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;

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

public class EquipmentBundlesPage implements Page{
	
//	EquipmentBundle equipmentbundle1 = new EquipmentBundle("bundle1", 25, ClimbSafeApplication.getClimbSafe());
//	EquipmentBundle equipmentbundle2 = new EquipmentBundle("bundle2", 35, ClimbSafeApplication.getClimbSafe());
//	EquipmentBundle equipmentbundle3 = new EquipmentBundle("bundle3", 15, ClimbSafeApplication.getClimbSafe());

    private GroupLayout layout;
    private JPanel panel;
    private JLabel label;
    private EquipmentBundlePanel equipmentBundlePanel;
    private EquipmentBundleSelector equipmentBundleSelector;

    public EquipmentBundlesPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        label = new JLabel("<HTML><U>Equipment Bundles</U></HTML>");
        String[] bundleNames = new String[ClimbSafeApplication.getClimbSafe().getBundles().size()];
        for(int i = 0; i < ClimbSafeApplication.getClimbSafe().getBundles().size(); i++){
            bundleNames[i] = ClimbSafeApplication.getClimbSafe().getBundle(i).getName();
        }
        equipmentBundlePanel = new EquipmentBundlePanel();
        equipmentBundleSelector = new EquipmentBundleSelector(bundleNames,
                (selected) -> {
                    panel.remove(equipmentBundlePanel);
                    equipmentBundlePanel = new EquipmentBundlePanel(ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(selected));
                    makeLayout();
                },
                (name) -> {
                    panel.remove(equipmentBundlePanel);
                    equipmentBundlePanel = new EquipmentBundlePanel(name);
                    makeLayout();
                }
        );
        makeLayout();
    }
    
    private void removeMemberPanel(){
        panel.remove(equipmentBundlePanel);
        equipmentBundlePanel = new EquipmentBundlePanel();
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
                                .addComponent(equipmentBundleSelector)
                        )
                        .addComponent(equipmentBundlePanel)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label)
                                .addComponent(equipmentBundleSelector)
                        )
                        .addComponent(equipmentBundlePanel)
        );
        panel.setLayout(layout);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    class EquipmentBundleSelector extends JPanel{

        String[] bundleNames;
        Consumer<String> select;
        GroupLayout barLayout;
        JList bar;
        JButton addButton;
        JButton deleteButton;
        JTextField addnameField;

        public EquipmentBundleSelector(String[] bundleNames, Consumer<String> select, Consumer<String> addEvent){
            this.bundleNames = bundleNames;
            this.select = select;

            bar = new JList(bundleNames);
            bar.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()) select.accept(String.valueOf(bar.getSelectedValue()));
                }
            });
            bar.setFont(bar.getFont().deriveFont(Font.PLAIN, 15.0f));
            bar.setFixedCellHeight(30);
            bar.setBorder(BorderFactory.createEmptyBorder());
            
            addButton = new JButton("Add");
            
            addnameField = new JTextField();
            addButton = new JButton("Add");
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(String.valueOf(bar.getSelectedValue()));
                    removeSelectedItem();
                    makeNewBar();
                    removeMemberPanel();
                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addEvent.accept(addnameField.getText());
                }
            });
            
                makeLayout();
            }
        
        private void makeNewBar(){
            remove(bar);
            bar = new JList(bundleNames);
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
        
        private void makeLayout(){
            barLayout = new GroupLayout(this);
            barLayout.setHorizontalGroup(
                    barLayout.createParallelGroup()
                            .addComponent(bar)
                            .addComponent(addnameField)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
            );
            barLayout.setVerticalGroup(
                    barLayout.createSequentialGroup()
                            .addComponent(bar)
                            .addComponent(addnameField)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
            );
            setLayout(barLayout);
            updateUI();
        }
        
        
        private void removeSelectedItem(){
            String[] newBundles = new String[bundleNames.length-1];
            int j = 0;
            for(String b: bundleNames){
                if(b == bar.getSelectedValue()) continue;
                newBundles[j] = b;
                j++;
            }
            bundleNames = newBundles;
        }
    }



    class EquipmentBundlePanel extends JPanel{

        private EquipmentBundle equipmentBundle;
        private boolean newEquipmentBundle;
        private String newName= "";
        private String oldname = "";

        private JLabel name;
        private JLabel discount;
        private JLabel oldName;

        private JTextField enterName;
        private JTextField enterDiscount;


        private EquipmentSelector equipmentSelector;

        private JButton saveButton;

        private GroupLayout bundleInfoLayout;
        
        public EquipmentBundlePanel() {}

        public EquipmentBundlePanel(String name){
        	newEquipmentBundle = true;
        	newName = name;
        	
        	makeLayout();
        	
        }

        public EquipmentBundlePanel(EquipmentBundle equipmentBundle){
            this.equipmentBundle = equipmentBundle;
            oldname = equipmentBundle.getName();
            
            newEquipmentBundle = false;
            makeLayout();
        }

            
            private void makeLayout() {
            name = new JLabel ("Name:");
            discount = new JLabel ("Discount");

            enterName = new JTextField (equipmentBundle.getName());
            enterDiscount = new JTextField (equipmentBundle.getDiscount());
            
            if(newEquipmentBundle){
                enterName = new JTextField();
                enterDiscount = new JTextField();
            } else {
            	oldName = new JLabel(equipmentBundle.getName());
            	oldName.setFont(oldName.getFont().deriveFont(Font.PLAIN)); 
            	enterName = new JTextField(equipmentBundle.getName());
                enterDiscount = new JTextField(equipmentBundle.getDiscount());
            }

            ArrayList<String> equipmentNames = new ArrayList<>();
            ArrayList<Integer> equipmentQuantities = new ArrayList<>();
            for(BundleItem b: equipmentBundle.getBundleItems()){
                equipmentNames.add(b.getEquipment().getName());
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

            bundleInfoLayout = new GroupLayout(this);
            bundleInfoLayout.setHorizontalGroup(
                    bundleInfoLayout.createSequentialGroup()
                            .addGroup(
                                bundleInfoLayout.createParallelGroup()
                                        .addComponent(name)
                                        .addComponent(discount)
                                        .addComponent(saveButton)
                            )
                            .addGroup(
                                    bundleInfoLayout.createParallelGroup()
                                            .addComponent(enterName)
                                            .addComponent(enterDiscount)
                            )
                            .addComponent(equipmentSelector)
            );
            bundleInfoLayout.setVerticalGroup(
                    bundleInfoLayout.createParallelGroup()
                                    .addGroup(
                                        bundleInfoLayout.createSequentialGroup()
                                                .addGroup(
                                                        bundleInfoLayout.createParallelGroup()
                                                                .addComponent(name)
                                                                .addComponent(enterName)
                                                )
                                                .addGroup(
                                                        bundleInfoLayout.createParallelGroup()
                                                                .addComponent(discount)
                                                                .addComponent(enterDiscount)
                                                )
                                                .addComponent(saveButton)
                                    )
                            .addComponent(equipmentSelector)
            );
            setLayout(bundleInfoLayout);

        }
        private void SaveModification(){
            equipmentSelector.getEquipmentQuantities().keySet(); //gets the items that have quantities
            equipmentSelector.getEquipmentQuantities().values(); //gets the quantities of the items
            ArrayList<Integer> quantities = new ArrayList<Integer>();
            quantities.addAll(equipmentSelector.getEquipmentQuantities().values());
            ArrayList<String> items = new ArrayList<String>();
            items.addAll(equipmentSelector.getEquipmentQuantities().keySet());
            try {
                
                if (newEquipmentBundle) {
                    ClimbSafeFeatureSet5Controller.addEquipmentBundle(
                            enterName.getText(),
                            Integer.parseInt(enterDiscount.getText()),
                            items,
                            quantities
                    );
                } else { ClimbSafeFeatureSet5Controller.updateEquipmentBundle(
                        oldname,
                		enterName.getText(),
                        Integer.parseInt(enterDiscount.getText()),
                        items,
                        quantities
                );
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}