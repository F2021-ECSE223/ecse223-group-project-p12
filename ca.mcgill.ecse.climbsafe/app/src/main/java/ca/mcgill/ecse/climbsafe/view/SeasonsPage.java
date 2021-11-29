package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class SeasonsPage implements Page {

    private GroupLayout layout;
    private JPanel panel;
    private JLabel startDateLabel = new JLabel("Start Date:");;
    private JLabel endDateLabel = new JLabel("End Date :");;
    private JLabel priceOfGuideLabel = new JLabel("Price of Guide Per Week:");;
    private JLabel startDate;
    private JLabel weeks;
    private JLabel priceOfGuide;
    private JButton editStart = new JButton("edit");
    private JButton editPriceGuide = new JButton("edit");
    private JTextField dayChange = new JTextField("");
    private JTextField monthChange = new JTextField("");
    private JTextField yearChange = new JTextField("");
    private JTextField endDayChange = new JTextField("");
    private JTextField endMonthChange = new JTextField("");
    private JTextField endYearChange = new JTextField("");
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
        invalidSomething.setText("");
        panel.removeAll();
        Date start = ClimbSafeApplication.getClimbSafe().getStartDate();
        Integer numWeeks = ClimbSafeApplication.getClimbSafe().getNrWeeks();
        Integer priceGuide =ClimbSafeApplication.getClimbSafe().getPriceOfGuidePerWeek();
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
                                        .addComponent(priceOfGuide)
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
                                        .addComponent(priceOfGuide)
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
        panel.removeAll();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label)
                        .addGroup( layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(emptyLab)
                                        .addComponent(startDateLabel)
                                        .addComponent(endDateLabel)
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
                                                        .addComponent(endDayChange)
                                                        .addComponent(endMonthChange)
                                                        .addComponent(endYearChange)
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
                                        .addComponent(endDateLabel)
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
                                                        .addComponent(endDayChange)
                                                        .addComponent(endMonthChange)
                                                        .addComponent(endYearChange)
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
        try {
            newDay = Integer.parseInt(dayChange.getText());
            newMonth = Integer.parseInt(monthChange.getText());
            newYear = Integer.parseInt(yearChange.getText());
            Date startUpDate = new Date(newYear,newMonth,newDay);
            newDay = Integer.parseInt(endDayChange.getText());
            newMonth = Integer.parseInt(endMonthChange.getText());
            newYear = Integer.parseInt(endYearChange.getText());
            Date endUpDate = new Date(newYear,newMonth,newDay);

            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startUpDate);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endUpDate);

            if(startCal.get(Calendar.DAY_OF_WEEK) != endCal.get(Calendar.DAY_OF_WEEK)){
                invalidSomething.setText("Season Length Must Be a Multiple of 7 Days");
            }

            long timeDif = Math.abs(endUpDate.getTime() - startUpDate.getTime());
            long diff = TimeUnit.DAYS.convert(timeDif, TimeUnit.MILLISECONDS);

            ClimbSafeApplication.getClimbSafe().setStartDate(startUpDate);
            ClimbSafeApplication.getClimbSafe().setNrWeeks((int) diff/7);
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
                                        .addComponent(newPriceGuide)
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
                                        .addComponent(newPriceGuide)
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
            ClimbSafeApplication.getClimbSafe().setPriceOfGuidePerWeek(upPrice);
            initComponents();
        }catch (Exception e){
            invalidSomething.setText("Please Enter a Number (Integer)");
        }
    }

}

