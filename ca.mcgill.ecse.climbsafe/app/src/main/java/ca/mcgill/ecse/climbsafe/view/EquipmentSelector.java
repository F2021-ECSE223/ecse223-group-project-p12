package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;
import ca.mcgill.ecse.climbsafe.model.Equipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class EquipmentSelector extends JPanel {

    private java.util.List<String> equipmentNames;
    private java.util.List<Integer> quantities;

    private GroupLayout equipmentLayout;

    private JLabel titleLabel;
    private EquipmentSelectorItem[] items;

    public EquipmentSelector(java.util.List<String> fullList, java.util.List<String> equipmentNames, java.util.List<Integer> quantities, boolean bundles){
        this.equipmentNames = fullList;
        this.quantities = new ArrayList<Integer>();
        for(String en: this.equipmentNames){
            int i = equipmentNames.indexOf(en);
            if(i < 0) this.quantities.add(0);
            else this.quantities.add(quantities.get(i));
        }

        titleLabel = new JLabel(bundles ? "<HTML><U>Bundle Selector</U></HTML>" : "<HTML><U>Equipment Selector</U></HTML>");
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

    public Map<String, Integer> getEquipmentQuantities(){
        Map<String, Integer> equipmentQuantities = new LinkedHashMap<>();
        for(EquipmentSelectorItem i: items){
            equipmentQuantities.put(i.getEquipmentName(), i.getQuantity());
        }
        return equipmentQuantities;
    }

    public Map<String, Integer> getNonZeroEquipmentQuantities(){
        Map<String, Integer> equipmentQuantities = new LinkedHashMap<String, Integer>();
        for(EquipmentSelectorItem i: items)
            if(i.getQuantity() > 0)
                equipmentQuantities.put(i.getEquipmentName(), i.getQuantity());
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
            quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
