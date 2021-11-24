package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Dictionary;

public class SideBar {

    ClimbSafeGUI mainGUI;
    Dictionary<String, Page> pages;
    JList<String> bar;

    public SideBar(Dictionary<String, Page> pages, ClimbSafeGUI mainGUI){
        this.pages = pages;
        String[] items = new String[pages.size()];
        for(int i = 0; i < pages.size(); i++){
            items[i] = pages.keys().nextElement();
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
        mainGUI.setPageLayout(pages.get(selectedPage).getLayout());
    }

    public JList<String> getSideBar(){
        return bar;
    }



}
