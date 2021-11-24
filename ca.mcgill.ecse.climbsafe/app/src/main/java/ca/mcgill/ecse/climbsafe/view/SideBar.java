package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Dictionary;
import java.util.LinkedHashMap;

public class SideBar {

    ClimbSafeGUI mainGUI;
    LinkedHashMap<String, Page> pages;
    JList<String> bar;

    public SideBar(LinkedHashMap<String, Page> pages, ClimbSafeGUI mainGUI){
        this.pages = pages;
        String[] items = new String[pages.size()];
        int i = 0;
        for(String s : pages.keySet()){
            items[i] = s;
            i++;
        }
        this.bar = new JList<String>(items);

        this.bar.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) selectionChanged(bar.getSelectedValue());
            }
        });

        this.mainGUI = mainGUI;
    }

    private void selectionChanged(String selectedPage){
        mainGUI.setPagePanel(pages.get(selectedPage).getPanel());
    }

    public JList<String> getSideBar(){
        return bar;
    }



}
