package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;
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
        label = new JLabel("<HTML><B><U>Equipment Bundles</U></B></HTML>");
        String[] equipmentBundleNames = MiscellaneousController.getEquipmentBundleNamesArray();
        equipmentBundlePanel = new EquipmentBundlePanel();
        equipmentBundleSelector = new EquipmentBundleSelector(equipmentBundleNames,
                (selected) -> {
                    panel.remove(equipmentBundlePanel);
                    equipmentBundlePanel = new EquipmentBundlePanel(ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(selected));
                    makeLayout();
                },
                (email) -> {
                    panel.remove(equipmentBundlePanel);
                    equipmentBundlePanel = new EquipmentBundlePanel(email);
                    makeLayout();
                }
        );
        makeLayout();
    }

    private void removeEquipmentBundlePanel(){
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

        String[]equipmentBundleNames;
        Consumer<String> select;

        GroupLayout barLayout;
        JList bar;
        JTextField addEmailField;
        JButton addButton;
        JButton deleteButton;

        public EquipmentBundleSelector(String[] equipmentBundleNames, Consumer<String> select, Consumer<String> addEvent){
            this.equipmentBundleNames = equipmentBundleNames;
            this.select = select;

            bar = new JList(equipmentBundleNames);
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
                    ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(String.valueOf(bar.getSelectedValue()));
                    removeSelectedItem();
                    makeNewBar();
                    removeEquipmentBundlePanel();
                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addEvent.accept(addEmailField.getText());
                }
            });
            makeLayout();
        }

        private void makeNewBar(){
            remove(bar);
            bar = new JList(equipmentBundleNames);
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
                            .addComponent(addEmailField)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
            );
            barLayout.setVerticalGroup(
                    barLayout.createSequentialGroup()
                            .addComponent(bar)
                            .addComponent(addEmailField)
                            .addComponent(addButton)
                            .addComponent(deleteButton)
            );
            setLayout(barLayout);
            updateUI();
        }

        private void removeSelectedItem(){
            String[] newEquipmentBundles = new String[equipmentBundleNames.length-1];
            int j = 0;
            for(String m: equipmentBundleNames){
                if(m == bar.getSelectedValue()) continue;
                newEquipmentBundles[j] = m;
                j++;
            }
            equipmentBundleNames = newEquipmentBundles;
        }

    }



    class EquipmentBundlePanel extends JPanel{

        private boolean newEquipmentBundle;
        private String newName = "";
        private EquipmentBundle equipmentBundle;

        private JLabel name;
        private JLabel discount;

        private JTextField enterName;
        private JTextField enterDiscount;

        private EquipmentSelector equipmentSelector;

        private JButton saveButton;

        private GroupLayout equipmentBundleInfoLayout;

        public EquipmentBundlePanel(){}

        public EquipmentBundlePanel(String name){
            newEquipmentBundle = true;
            newName = name;
            makeLayout();
        }

        public EquipmentBundlePanel(EquipmentBundle equipmentBundle){
            this.equipmentBundle = equipmentBundle;
            newEquipmentBundle = false;
            makeLayout();
        }

        private void makeLayout(){
            name = new JLabel ("Name:");
            discount = new JLabel ("Discount:  ");

            if(newEquipmentBundle){
                enterName = new JTextField(newName);
                enterDiscount = new JTextField();
            } else {
                enterName = new JTextField(equipmentBundle.getName());
                enterDiscount = new JTextField(String.valueOf(equipmentBundle.getDiscount()));
            }

            ArrayList<String> equipmentNames = new ArrayList<>();
            ArrayList<Integer> equipmentQuantities = new ArrayList<>();
            if(!newEquipmentBundle)
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

            equipmentBundleInfoLayout = new GroupLayout(this);
            equipmentBundleInfoLayout.setHorizontalGroup(
            		equipmentBundleInfoLayout.createSequentialGroup()
                            .addGroup(
                            		equipmentBundleInfoLayout.createParallelGroup()
                                            .addComponent(name)
                                            .addComponent(discount)
                                            .addComponent(saveButton)
                            )
                            .addGroup(
                            		equipmentBundleInfoLayout.createParallelGroup()
                                            .addComponent(enterName)
                                            .addComponent(enterDiscount)
                            )
                            .addComponent(equipmentSelector)
            );
            equipmentBundleInfoLayout.setVerticalGroup(
            		equipmentBundleInfoLayout.createParallelGroup()
                            .addGroup(
                            		equipmentBundleInfoLayout.createSequentialGroup()
                                            .addGroup(
                                            		equipmentBundleInfoLayout.createParallelGroup()
                                                            .addComponent(name)
                                                            .addComponent(enterName)
                                            )
                                            .addGroup(
                                            		equipmentBundleInfoLayout.createParallelGroup()
                                                            .addComponent(discount)
                                                            .addComponent(enterDiscount)
                                            )
                                            .addComponent(saveButton)
                            )
                            .addComponent(equipmentSelector)
            );
            setLayout(equipmentBundleInfoLayout);
        }

        private void SaveModification(){
            ArrayList<Integer> quantities = new ArrayList<Integer>();
            quantities.addAll(equipmentSelector.getNonZeroEquipmentQuantities().values());
            ArrayList<String> items = new ArrayList<String>();
            items.addAll(equipmentSelector.getNonZeroEquipmentQuantities().keySet());
            try {
                if (newEquipmentBundle) {
                    ClimbSafeFeatureSet5Controller.addEquipmentBundle(
                            enterName.getText(),
                            Integer.parseInt(enterDiscount.getText()),
                            items,
                            quantities
                    );
                } else {
                    ClimbSafeFeatureSet5Controller.updateEquipmentBundle(
                    		equipmentBundle.getName(),
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