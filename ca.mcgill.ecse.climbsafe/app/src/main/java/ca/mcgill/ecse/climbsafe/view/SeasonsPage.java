package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class SeasonsPage implements Page {

    private GroupLayout layout;
    private JPanel panel;
    private JLabel startDateLabel = new JLabel("Start Date:");;
    private JLabel weekModLab = new JLabel("Number of Weeks :");;
    private JLabel priceOfGuideLabel = new JLabel("Price of Guide Per Week:");;
    private JLabel startDate;
    private JLabel weeks;
    private JLabel priceOfGuide;
    private JButton editStart = new JButton("edit");
    private JButton editPriceGuide = new JButton("edit");
    private JTextField dayChange = new JTextField("");
    private JTextField monthChange = new JTextField("");
    private JTextField yearChange = new JTextField("");
    private JSpinner weeksChange = new JSpinner();
    private JLabel dayLab = new JLabel("Day");
    private JLabel monthLab = new JLabel("Month");
    private JLabel yearLab = new JLabel("Year");
    private JLabel slash = new JLabel("/");
    private JLabel label = new JLabel("Seasons");
    private JButton saveStart = new JButton("save");
    private JButton savePriceGuide = new JButton("save");
    private JLabel emptyLab = new JLabel("                    ");
    private JLabel invalidSomething = new JLabel("");
    private JTextField newPriceGuide = new JTextField("");
    private JLabel dollarsPerWeek = new JLabel("$/week");
    int testNum = 1;
    int changedValue = (int) weeksChange.getValue();
    ChangeListener changeListner = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            changedValue = (int) weeksChange.getValue();
            int nextInOrder;
            System.out.println(changedValue-testNum);
            if((changedValue-testNum) < 0){
                nextInOrder = (int) weeksChange.getPreviousValue();
                if(nextInOrder <= 0){
                    weeksChange.setValue(1);
                }
            }
            testNum = (int) weeksChange.getValue();
        }
    };

    public SeasonsPage(){

        panel = new JPanel();
        layout = new GroupLayout(panel);
        invalidSomething.setForeground(Color.red);

        initComponents();
        editStart.addActionListener( e -> startButtonPressed() );
        saveStart.addActionListener(e -> saveStartButtonPressed());
        editPriceGuide.addActionListener(e -> editPriceGuidePressed());
        savePriceGuide.addActionListener(e -> savePriceGuidePressed());
    }

    private void initComponents(){
        weeksChange.setValue(1);
        invalidSomething.setText("");
        panel.removeAll();
        Date start = MiscellaneousController.getSeasonStartDate();
        Integer numWeeks = MiscellaneousController.getSeasonNumberOfWeeks();
        Integer priceGuide =MiscellaneousController.getPriceOfGuide();
        startDate = new JLabel(start.getDate() + "-" + start.getMonth() + "-" + start.getYear());
        priceOfGuide = new JLabel(priceGuide.toString());
        weeks = new JLabel(numWeeks.toString() + " weeks long");
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label)
                        .addGap(20)
                        .addGroup( layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(startDateLabel)
                                        .addGap(50)
                                        .addComponent(priceOfGuideLabel)
                                )

                                .addGroup( layout.createParallelGroup()
                                        .addComponent(startDate)
                                        .addComponent(weeks)
                                        .addGap(28)
                                        .addGroup( layout.createSequentialGroup()
                                                .addComponent(priceOfGuide)
                                                .addComponent(dollarsPerWeek)
                                        )

                                )

                                .addGroup( layout.createParallelGroup()
                                        .addComponent(editStart)
                                        .addGap(50)
                                        .addComponent(editPriceGuide)
                                )
                        )
                        .addComponent(invalidSomething)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(20)
                        .addGroup( layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(startDateLabel)
                                        .addGap(50)
                                        .addComponent(priceOfGuideLabel)
                                )

                                .addGroup( layout.createSequentialGroup()
                                        .addComponent(startDate)
                                        .addComponent(weeks)
                                        .addGap(28)
                                        .addGroup( layout.createParallelGroup()
                                                .addComponent(priceOfGuide)
                                                .addComponent(dollarsPerWeek)
                                        )
                                )

                                .addGroup( layout.createSequentialGroup()
                                        .addComponent(editStart)
                                        .addGap(50)
                                        .addComponent(editPriceGuide)
                                )
                        )
                        .addComponent(invalidSomething)
        );
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }


    private void startButtonPressed() {
        weeksChange.addChangeListener(changeListner);
        ((JSpinner.DefaultEditor) weeksChange.getEditor()).getTextField().setEditable(false);
        panel.removeAll();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label)
                        .addGroup( layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(emptyLab)
                                        .addComponent(startDateLabel)
                                        .addComponent(weekModLab)
                                )

                                .addGroup( layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dayLab)
                                                .addComponent(monthLab)
                                                .addComponent(yearLab)
                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(dayChange)
                                                        .addComponent(monthChange)
                                                        .addComponent(yearChange)
                                                )

                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(weeksChange)
                                                )

                                        )
                                )
                        )
                        .addComponent(invalidSomething)
                        .addComponent(saveStart)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup( layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(emptyLab)
                                        .addComponent(startDateLabel)
                                        .addComponent(weekModLab)
                                )

                                .addGroup( layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(dayLab)
                                                .addComponent(monthLab)
                                                .addComponent(yearLab)
                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(dayChange)
                                                        .addComponent(monthChange)
                                                        .addComponent(yearChange)
                                                )
                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(weeksChange)
                                                )

                                        )
                                )
                        )
                        .addComponent(invalidSomething)
                        .addComponent(saveStart)
        );

    }

    private void saveStartButtonPressed() {
        int newDay;
        int newMonth;
        int newYear;
        int numWeeks;
        try {
            newDay = Integer.parseInt(dayChange.getText());
            newMonth = Integer.parseInt(monthChange.getText());
            newYear = Integer.parseInt(yearChange.getText());
            numWeeks = (int) weeksChange.getValue();
            Date startUpDate = new Date(newYear,newMonth,newDay);

            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startUpDate);

//            if(startCal.get(Calendar.DAY_OF_WEEK) != endCal.get(Calendar.DAY_OF_WEEK)){
//                invalidSomething.setText("Season Length Must Be a Multiple of 7 Days");
//            }
//
//            long timeDif = Math.abs(endUpDate.getTime() - startUpDate.getTime());
//            long diff = TimeUnit.DAYS.convert(timeDif, TimeUnit.MILLISECONDS);


            ClimbSafeFeatureSet1Controller.setup(startUpDate, numWeeks, MiscellaneousController.getPriceOfGuide());
            initComponents();

        }catch (Exception e){
            invalidSomething.setText("Invalid Date");
        }
    }

    private void editPriceGuidePressed() {
        panel.removeAll();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label)
                        .addGap(100)
                        .addGroup( layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(startDateLabel)
                                        .addGap(50)
                                        .addComponent(priceOfGuideLabel)
                                )

                                .addGroup( layout.createParallelGroup()
                                        .addComponent(startDate)
                                        .addComponent(weeks)
                                        .addGap(28)
                                        .addGroup( layout.createSequentialGroup()
                                                .addComponent(newPriceGuide)
                                                .addComponent(dollarsPerWeek)
                                        )
                                )

                                .addGroup( layout.createParallelGroup()
                                        .addComponent(editStart)
                                        .addGap(50)
                                        .addComponent(savePriceGuide)
                                )
                        )
                        .addComponent(invalidSomething)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(100)
                        .addGroup( layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(startDateLabel)
                                        .addGap(50)
                                        .addComponent(priceOfGuideLabel)
                                )

                                .addGroup( layout.createSequentialGroup()
                                        .addComponent(startDate)
                                        .addComponent(weeks)
                                        .addGap(28)
                                        .addGroup( layout.createParallelGroup()
                                                .addComponent(newPriceGuide)
                                                .addComponent(dollarsPerWeek)
                                        )
                                )

                                .addGroup( layout.createSequentialGroup()
                                        .addComponent(editStart)
                                        .addGap(50)
                                        .addComponent(savePriceGuide)
                                )
                        )
                        .addComponent(invalidSomething)
        );
    }

    private void savePriceGuidePressed() {
        String newPrice = newPriceGuide.getText();
        try{
            int upPrice = Integer.parseInt(newPrice);
            ClimbSafeFeatureSet1Controller.setup(MiscellaneousController.getSeasonStartDate(),MiscellaneousController.getSeasonNumberOfWeeks() , upPrice);
            initComponents();
        }catch (Exception e){
            invalidSomething.setText("Please Enter a Number (Integer)");
        }
    }

}

