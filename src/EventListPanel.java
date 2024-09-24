import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    //Private variable declaration
    private ArrayList<Event> events = new ArrayList<>();
    public JPanel controlPanel;
    public JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    final String[] SORT_OPTIONS = {"ALPHABETICAL", "DATE", "REVERSE_ALPHABETICAL", "REVERSE_DATE"};
    final String[] FILTERS = {"Remove Complete Tasks", "Deadlines", "Meetings"};
    private JCheckBox filterDisplay;
    private ArrayList<JCheckBox> filters;
    private JButton addEventButton;
    private final int PANEL_WIDTH = 900;
    private final int PANEL_HEIGHT = 800;
    private final int INNER_PANEL_WIDTH = 900;
    private final int CONTROL_PANEL_HEIGHT = 300;
    private final int DISPLAY_PANEL_HEIGHT = 600;

    public EventListPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.darkGray);

        //Control Panel
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(INNER_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
        controlPanel.setBackground(Color.lightGray);

        //Add Event Button
        addEventButton = new JButton("Add Event");
        addEventButton.setFont(new Font("Serif", Font.BOLD, 30));
        addEventButton.addActionListener(e -> {
            //insert code for AddEventModal here
        });
        controlPanel.add(addEventButton);

        //Dropbox for sorting the events
        sortDropDown = new JComboBox(SORT_OPTIONS);
        sortDropDown.setFont(new Font("Serif", Font.BOLD, 30));
        sortDropDown.addActionListener(e -> {
            //Insert code for drop down sorting here
        });
        controlPanel.add(sortDropDown);

        //Check box for filtering out events
        filters = new ArrayList<>();
        for (String filter : FILTERS) {
            JCheckBox checkBox = new JCheckBox(filter);
            checkBox.setFont(new Font("Serif", Font.BOLD, 30));
            checkBox.setBackground(Color.lightGray);
            checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    updateDisplay();
                }
            });
            filters.add(checkBox);
        }
        for (JCheckBox checkBox : filters) {
            controlPanel.add(checkBox);
        }
        this.add(controlPanel);

        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(INNER_PANEL_WIDTH, DISPLAY_PANEL_HEIGHT));
        displayPanel.setBackground(Color.lightGray);

        this.add(controlPanel);
        this.add(displayPanel);

    }
    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }
    public boolean isFiltered(Event event) {
        for (JCheckBox checkBox : filters) {
            if (checkBox.isSelected()) {
                switch(checkBox.getText()) {
                    case "Remove Complete Tasks":
                        if (event.isComplete())
                            return true;
                        break;
                    case "Deadlines":
                        //Code for deadlines here
                        break;
                    case "Meetings":
                        //Code for meetings here
                        break;
                }
            }
        }
        return false;
    }
    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            if (!isFiltered(event))
                displayPanel.add(new EventPanel(event));
        }
        revalidate();
        repaint();
    }
}