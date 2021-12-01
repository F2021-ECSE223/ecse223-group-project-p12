package ca.mcgill.ecse.climbsafe.view;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.MiscellaneousController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TripsPage implements Page{

    private GroupLayout layout;
    private JPanel panel;
    JLabel startTripsForWeekLab = new JLabel("Start All Trips For Week: ");
    JSpinner weekToStart = new JSpinner();
    JButton start = new JButton("start");
    JLabel editMemberTripLab = new JLabel("Finish or Cancel Member's Trip: ");
    JTextField whichMember =  new JTextField("");
    JButton finishTrip = new JButton("Finish");
    JButton cancelTrip = new JButton("Cancel");
    JLabel invalidSomething = new JLabel("");
    int numOfWeeks = MiscellaneousController.getSeasonNumberOfWeeks();
    int testNum = 1;
    int changedValue = (int) weekToStart.getValue();
    ChangeListener changeListner = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            numOfWeeks = MiscellaneousController.getSeasonNumberOfWeeks();
            changedValue = (int) weekToStart.getValue();
            int nextInOrder;
            System.out.println(changedValue-testNum);
            if((changedValue-testNum) < 0){
                nextInOrder = (int) weekToStart.getPreviousValue();
                if(nextInOrder <= 0){
                    weekToStart.setValue(1);
                }
            }else if ((changedValue-testNum) > 0){
                nextInOrder = (int) weekToStart.getNextValue();
                if(nextInOrder > numOfWeeks){
                    weekToStart.setValue(numOfWeeks);
                }
            }
            testNum = (int) weekToStart.getValue();
        }
    };



    public TripsPage(){
        panel = new JPanel();
        layout = new GroupLayout(panel);
        initComponents();
    }

    private void initComponents(){
        invalidSomething.setForeground(Color.red);
        JLabel label = new JLabel("Trips");
        ((JSpinner.DefaultEditor) weekToStart.getEditor()).getTextField().setEditable(false);
        weekToStart.setValue(1);
        weekToStart.addChangeListener(changeListner);
        start.addActionListener(e -> startButtonPressed());
        finishTrip.addActionListener(e -> finishButtonPressed());
        cancelTrip.addActionListener(e -> cancelButtonPressed());
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(label)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(startTripsForWeekLab)
                                                .addComponent(editMemberTripLab)
                                        )
                                        .addGroup(layout.createParallelGroup()
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(weekToStart)
                                                        .addComponent(start)
                                                )

                                                .addComponent(whichMember)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(finishTrip)
                                                        .addComponent(cancelTrip)
                                                )
                                        )
                                )
                                .addComponent(invalidSomething)

                        )

        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(startTripsForWeekLab)
                                                .addComponent(editMemberTripLab)
                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(weekToStart)
                                                        .addComponent(start)
                                                )
                                                .addComponent(whichMember)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(finishTrip)
                                                        .addComponent(cancelTrip)

                                                )
                                     )
                                )
                                .addComponent(invalidSomething)
                        )

        );
    }

    private void cancelButtonPressed() {
        String member = whichMember.getText();
        try{
            AssignmentController.cancelTrip(member);
            invalidSomething.setText("");
        }catch(Exception e){
            invalidSomething.setText("User Not Found");
        }
    }

    private void finishButtonPressed() {
        String member = whichMember.getText();
        try {
            AssignmentController.finishTrip(member);
            invalidSomething.setText("");
        }catch (Exception e){
            invalidSomething.setText("User Not Found");
        }
    }

    private void startButtonPressed() {
        int week = (int) weekToStart.getValue();
        try{
            AssignmentController.startTrips(week);
        }catch(Exception e){

        }

    }


    @Override
    public JPanel getPanel() {
        return panel;
    }

}
