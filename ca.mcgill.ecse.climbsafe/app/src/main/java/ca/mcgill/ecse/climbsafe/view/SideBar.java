package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.LinkedHashMap;

public class SideBar {

	private Color dark_grey = new Color(48,48,48);
	private Color green = new Color(42,78,54);
    ClimbSafeGUI mainGUI;
    LinkedHashMap<String, Page> pages;
    JList<String> bar;

    public SideBar(LinkedHashMap<String, Page> pages, ClimbSafeGUI mainGUI){
        this.pages = pages;
        String[] items = new String[pages.size()+17];
        int i = 0;
        for(String s : pages.keySet()){
            items[i] = s;
            i++;
        }
        
        for (int j = i+1; j< pages.size()+17; j++) {
        	items[j] = " ";
        }
        
        this.bar = new JList<String>(items);
        this.bar.setFont(this.bar.getFont().deriveFont(Font.PLAIN, 15.0f));
        this.bar.setForeground(Color.white);
        this.bar.setBackground(dark_grey);
        this.bar.setFixedCellHeight(30);
        this.bar.setBorder(BorderFactory.createEmptyBorder());

        this.bar.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) selectionChanged(bar.getSelectedValue());
            }
        });

        this.mainGUI = mainGUI;
    }

    public void select(int i){
        bar.setSelectedIndex(i);
    }

    private void selectionChanged(String selectedPage){
        mainGUI.setPagePanel(pages.get(selectedPage).getPanel());
    }

    public JList<String> getSideBar(){
        return bar;
    }



}
