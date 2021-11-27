package ca.mcgill.ecse.climbsafe.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This class is a custom Table UI element.
 * Its purpose is to be double-binded to the application data (read and write data).
 *
 * @author Philippe Sarouphim Hochar.
 *
 */
public class Table extends JPanel {

    private String[] columns;

    private Consumer<Object[]> createEvent;
    private BiConsumer<Integer, Object[][]> editEvent;
    private BiConsumer<Integer, Object[]> deleteEvent;

    private ArrayList<TableRow> rows;
    private GroupLayout layout;
    
    private JPanel titlesPanel;
    private GroupLayout titlesLayout;
    private JLabel[] titles;
    
    private JPanel addPanel;
    private GroupLayout addLayout;
    private JTextField[] addFields;
    private JButton addButton;

    /**
     * Constructor for the table.
     * It takes all of the necessary inputs to create the table columns and get the necessary event functions.
     * It also calls all of the necessary methods to create the UI.
     *
     * @author Philippe Sarouphim Hochar
     *
     * @param columns Column names
     * @param createEvent Creation event
     * @param editEvent Edit event
     * @param deleteEvent Deletion event
     */
    public Table(String[] columns, Consumer<Object[]> createEvent, BiConsumer<Integer, Object[][]> editEvent, BiConsumer<Integer, Object[]> deleteEvent){
        this.columns = columns;

        this.createEvent = createEvent;
        this.editEvent = editEvent;
        this.deleteEvent = deleteEvent;

        this.layout = new GroupLayout(this);
        this.rows = new ArrayList<TableRow>();

        makeTableTitles();
        makeAddSection();

        makeLayout();

        setLayout(layout);
    }

    /**
     * This method creates the column titles
     *
     * @author Philippe Sarouphim Hochar.
     */
    private void makeTableTitles(){
        titlesPanel = new JPanel();
        titlesLayout = new GroupLayout(titlesPanel);
        titles = new JLabel[columns.length];
        for(int i = 0; i < columns.length; i++){
            titles[i] = new JLabel(columns[i]);
            titles[i].setFont(titles[i].getFont().deriveFont(15.0f));
            titles[i].setMinimumSize(new Dimension(100 ,20));
            titles[i].setPreferredSize(new Dimension(100 ,20));
            titles[i].setMaximumSize(new Dimension(100 ,20));
        }
        GroupLayout.SequentialGroup horizontalGroup = titlesLayout.createSequentialGroup();
        GroupLayout.ParallelGroup verticalGroup = titlesLayout.createParallelGroup();
        for(JLabel t: titles){
            horizontalGroup.addComponent(t);
            verticalGroup.addComponent(t);
        }
        titlesLayout.setHorizontalGroup(horizontalGroup);
        titlesLayout.setVerticalGroup(verticalGroup);
        titlesPanel.setLayout(titlesLayout);
        titlesPanel.setVisible(true);
    }

    /**
     * This method creates the "add" section at the bottom of the table.
     *
     * @author Philippe Sarouphim Hochar.
     */
    private void makeAddSection(){
        addPanel = new JPanel();
        addLayout = new GroupLayout(addPanel);
        addFields = new JTextField[columns.length];
        for(int i = 0; i < columns.length; i++){
            addFields[i] = new JTextField("");
            addFields[i].setFont(addFields[i].getFont().deriveFont(15.0f));
            addFields[i].setMinimumSize(new Dimension(100 ,30));
            addFields[i].setPreferredSize(new Dimension(100 ,30));
            addFields[i].setMaximumSize(new Dimension(100 ,30));
        }
        addButton = new JButton("Add");
        addButton.setMinimumSize(new Dimension(80, 30));
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.setMaximumSize(new Dimension(80, 30));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] object = new Object[columns.length];
                for(int i = 0; i < columns.length; i++){
                    object[i] = addFields[i].getText();
                    addFields[i].setText("");
                }
                createRow(object);
            }
        });
        GroupLayout.SequentialGroup horizontalGroup = addLayout.createSequentialGroup();
        GroupLayout.ParallelGroup verticalGroup = addLayout.createParallelGroup();
        for(JTextField t: addFields){
            horizontalGroup.addComponent(t);
            verticalGroup.addComponent(t);
        }
        horizontalGroup.addComponent(addButton);
        verticalGroup.addComponent(addButton);
        addLayout.setHorizontalGroup(horizontalGroup);
        addLayout.setVerticalGroup(verticalGroup);
        addPanel.setLayout(addLayout);
        addPanel.setVisible(true);
    }

    /**
     * This method adds a row to the table with the given data.
     *
     * @author Philippe Sarouphim Hochar.
     * @param rowData Row data
     */
    public void addRow(Object[] rowData){
        rows.add(new TableRow(0, rowData));
        makeLayout();
    }

    /**
     * This method makes the table layout and adds the titles and adding sections at the top and
     * bottom of the table.
     *
     * @author Philippe Sarouphim Hochar
     */
    private void makeLayout(){
        removeAll();
        layout = new GroupLayout(this);

        GroupLayout.ParallelGroup horizontalGroup = layout.createParallelGroup();
        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
        horizontalGroup.addComponent(titlesPanel);
        verticalGroup.addComponent(titlesPanel);
        for(TableRow tr: rows) {
            horizontalGroup.addComponent(tr);
            verticalGroup.addComponent(tr);
        }
        horizontalGroup.addComponent(addPanel);
        verticalGroup.addComponent(addPanel);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);

        setBorder(new EmptyBorder(10, 10, 10, 10));

        setLayout(layout);
        updateUI();
    }

    /**
     * This method triggers the creation event with the given data.
     * If the data is successfully added to the application, then the row will be added to the table.
     *
     * @author Philippe Sarouphim Hochar.
     * @param data Data of the row to be added.
     */
    private void createRow(Object[] data){
        try{
            createEvent.accept(data);
        } catch(Exception e){
            e.printStackTrace();
            return;
        }
        addRow(data);
    }

    /**
     * This method triggers the editing event with the given data.
     * If the data is successfully modified in the application, then the row will be modified accordingly.
     *
     * @author Philippe Sarouphim Hochar.
     * @param row Row in question
     * @param newData New, modified data
     * @param oldData Old row data
     */
    private void editRow(TableRow row, Object[] newData, Object[] oldData){
        Object[][] data = new Object[2][];
        data[0] = oldData;
        data[1] = newData;
        try {
            editEvent.accept(rows.indexOf(row), data);
        } catch(Exception e){
            e.printStackTrace();
            return;
        }
        row.rowData = newData;
    }

    /**
     * This method triggers the deletion event for a given row.
     * If the data is successfully deleted in the application, then the row will be deleted.
     *
     * @author Philippe Sarouphim Hochar.
     * @param row Row to be deleted
     */
    private void removeRow(TableRow row){
        try {
            deleteEvent.accept(rows.indexOf(row), row.rowData);
        } catch(Exception e){
            e.printStackTrace();
            return;
        }
        rows.remove(row);
        makeLayout();
    }

    /**
     * This subclass is a table row which is the principal element of the table.
     * Its purpose is to display the row data in a row-like fashion, and to handle modification and
     * deletion mechanism.
     *
     * @author Philippe Sarouphim Hochar.
     */
    class TableRow extends JPanel{

        private int index;

        private boolean hover = false;
        private boolean editMode = false;

        private Object[] rowData;

        private JLabel[] labels;
        private JTextField[] fields;

        private JButton editButton;
        private JButton deleteButton;

        private GroupLayout layout;

        /**
         * This is the constructor for the table row object.
         * It gets the row data and create the layout accordingly.
         *
         * @author Philippe Sarouphim Hochar.
         * @param index Index of the row in the table
         * @param rowData Row data
         */
        public TableRow(int index, Object[] rowData){
            this.index = index;
            this.rowData = rowData;

            updateLabels();

            setMinimumSize(new Dimension(columns.length * 100 + 80, 40));
            setPreferredSize(new Dimension(columns.length * 100 + 80, 40));
            setMaximumSize(new Dimension(columns.length * 100 + 80, 40));

            MouseListener hoverListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    checkEditDisplay();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    checkEditDisplay();
                }
            };

            editButton = new JButton("E");
            editButton.setFont(editButton.getFont().deriveFont(8.5f));
            editButton.setMinimumSize(new Dimension(40, 30));
            editButton.setPreferredSize(new Dimension(40, 30));
            editButton.setMaximumSize(new Dimension(40, 30));
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editMode = !editMode;
                    checkEditDisplay();
                    if(editMode){
                        editButton.setText("S");
                        makeEditLayout();
                    } else {
                        modifyRow();
                        editButton.setText("E");
                        updateLabels();
                        makeNonEditLayout();
                    }
                    updateUI();
                }
            });
            editButton.addMouseListener(hoverListener);

            deleteButton = new JButton("X");
            deleteButton.setFont(deleteButton.getFont().deriveFont(8.5f));
            deleteButton.setMinimumSize(new Dimension(40, 30));
            deleteButton.setPreferredSize(new Dimension(40, 30));
            deleteButton.setMaximumSize(new Dimension(40, 30));
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteRow();
                }
            });
            deleteButton.addMouseListener(hoverListener);

            addMouseListener(hoverListener);

            makeNonEditLayout();

            checkEditDisplay();
        }

        /**
         * This method makes the display layout of the row.
         *
         * @author Philippe Sarouphim Hochar.
         */
        private void makeNonEditLayout(){
            removeAll();
            layout = new GroupLayout(this);

            GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();
            for(JLabel l: labels) horizontalGroup.addComponent(l);
            horizontalGroup.addComponent(editButton);
            horizontalGroup.addComponent(deleteButton);
            GroupLayout.ParallelGroup verticalGroup = layout.createParallelGroup();
            for(JLabel l: labels) verticalGroup.addComponent(l);
            verticalGroup.addComponent(editButton);
            verticalGroup.addComponent(deleteButton);

            layout.setHorizontalGroup(horizontalGroup);
            layout.setVerticalGroup(verticalGroup);

            setLayout(layout);
            updateUI();
        }

        /**
         * This method makes the editing layout of the row.
         *
         * @author Philippe Sarouphim Hochar.
         */
        private void makeEditLayout(){
            removeAll();
            layout = new GroupLayout(this);

            GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();
            for(JTextField tf: fields) horizontalGroup.addComponent(tf);
            horizontalGroup.addComponent(editButton);
            horizontalGroup.addComponent(deleteButton);
            GroupLayout.ParallelGroup verticalGroup = layout.createParallelGroup();
            for(JTextField tf: fields) verticalGroup.addComponent(tf);
            verticalGroup.addComponent(editButton);
            verticalGroup.addComponent(deleteButton);

            layout.setHorizontalGroup(horizontalGroup);
            layout.setVerticalGroup(verticalGroup);

            setLayout(layout);
            updateUI();
        }

        /**
         * This method makes an attempt to delete the row.
         *
         * @author Philippe Sarouphim Hochar.
         */
        private void deleteRow(){
            removeRow(this);
        }

        /**
         * This method makes an attempt to modify the row.
         *
         * @author Philippe Sarouphim Hochar.
         */
        private void modifyRow(){
            Object[] newData = new Object[rowData.length];
            for(int i = 0; i < newData.length; i++) newData[i] = fields[i].getText();
            editRow(this, newData, rowData);
        }

        /**
         * This method updates all of the values in labels and text fields.
         * It is meant to be called after an editing event.
         *
         * @auhthor Philippe Sarouphim Hochar.
         */
        private void updateLabels(){
            labels = new JLabel[rowData.length];
            for(int i = 0; i < rowData.length; i++){
                labels[i] = new JLabel(rowData[i].toString());
                labels[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN, 15.0f));
                labels[i].setMinimumSize(new Dimension(100 ,30));
                labels[i].setPreferredSize(new Dimension(100 ,30));
                labels[i].setMaximumSize(new Dimension(100 ,30));
            }
            fields = new JTextField[rowData.length];
            for(int i = 0; i < rowData.length; i++){
                fields[i] = new JTextField(rowData[i].toString());
                fields[i].setFont(labels[i].getFont().deriveFont(Font.PLAIN, 15.0f));
                fields[i].setMinimumSize(new Dimension(100 ,30));
                fields[i].setPreferredSize(new Dimension(100 ,30));
                fields[i].setMaximumSize(new Dimension(100 ,30));
            }
        }

        /**
         * This method checks whether the edit and delete buttons should be displayed.
         *
         * @author Philippe Sarouphim Hochar.
         */
        private void checkEditDisplay(){
            editButton.setVisible(hover || editMode);
            deleteButton.setVisible(hover || editMode);
            updateUI();
        }

    }

}
