package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class SeasonsPage implements Page {

    private GroupLayout layout;
    private JPanel panel;
    private JLabel startDateLabel;
    private JLabel numWeeksLabel;
    private JLabel priceOfGuideLabel;
    private JLabel startDate;
    private JLabel weeks;
    private JLabel priceOfGuide;
    private JButton editStart = new JButton("edit");
    private JButton editWeeks = new JButton("edit");
    private JButton editPriceGuide = new JButton("edit");

    Properties j = new Properties();
    OutputStream os;
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);


    public SeasonsPage(){
        Date start = ClimbSafeApplication.getClimbSafe().getStartDate();
        Integer numWeeks = ClimbSafeApplication.getClimbSafe().getNrWeeks();
        Integer priceGuide =ClimbSafeApplication.getClimbSafe().getPriceOfGuidePerWeek();
        panel = new JPanel();
        layout = new GroupLayout(panel);
        startDateLabel = new JLabel("Start Date:");
        startDate = new JLabel(start.toString());
        weeks = new JLabel(numWeeks.toString());
        priceOfGuide = new JLabel(priceGuide.toString());

        numWeeksLabel = new JLabel("Number of Weeks:");
        priceOfGuideLabel = new JLabel("Price of Guide Per Week:");
        initComponents();
        editStart.addActionListener( e -> startButtonPressed() );
    }

    private void initComponents(){
        JLabel label = new JLabel("Seasons");
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label)
                        .addGroup( layout.createSequentialGroup()
                                .addComponent(startDateLabel)
                                .addComponent(startDate)
                                .addComponent(editStart)
                        )

                                .addGroup( layout.createSequentialGroup()
                                        .addComponent(numWeeksLabel)
                                        .addComponent(weeks)
                                        .addComponent(editWeeks)
                                )
                        .addGroup( layout.createSequentialGroup()
                                .addComponent(priceOfGuideLabel)
                                .addComponent(priceOfGuide)
                                .addComponent(editPriceGuide)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup( layout.createParallelGroup()
                                .addComponent(startDateLabel)
                                .addComponent(startDate)
                                .addComponent(editStart)
                        )

                        .addGroup( layout.createParallelGroup()
                                .addComponent(numWeeksLabel)
                                .addComponent(weeks)
                                .addComponent(editWeeks)
                        )
                        .addGroup( layout.createParallelGroup()
                                .addComponent(priceOfGuideLabel)
                                .addComponent(priceOfGuide)
                                .addComponent(editPriceGuide)
                        )
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }


    private void startButtonPressed() {



//        layout.setHorizontalGroup(
//                layout.createParallelGroup()
//                        .addComponent(label)
//                        .addGroup( layout.createSequentialGroup()
//                                .addComponent(startDateLabel)
//                                .addComponent(startDate)
//                                .addComponent(editStart)
//                        )
//
//                        .addGroup( layout.createSequentialGroup()
//                                .addComponent(numWeeksLabel)
//                                .addComponent(weeks)
//                                .addComponent(editWeeks)
//                        )
//                        .addGroup( layout.createSequentialGroup()
//                                .addComponent(priceOfGuideLabel)
//                                .addComponent(priceOfGuide)
//                                .addComponent(editPriceGuide)
//                        )
//        );
//        layout.setVerticalGroup(
//                layout.createSequentialGroup()
//                        .addComponent(label)
//                        .addGroup( layout.createParallelGroup()
//                                .addComponent(startDateLabel)
//                                .addComponent(startDate)
//                                .addComponent(editStart)
//                        )
//
//                        .addGroup( layout.createParallelGroup()
//                                .addComponent(numWeeksLabel)
//                                .addComponent(weeks)
//                                .addComponent(editWeeks)
//                        )
//                        .addGroup( layout.createParallelGroup()
//                                .addComponent(priceOfGuideLabel)
//                                .addComponent(priceOfGuide)
//                                .addComponent(editPriceGuide)
//                        )
//        );
    }
}

