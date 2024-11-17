import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;

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
            new AddEventModal(this);
        });
        controlPanel.add(addEventButton);

        //Dropbox for sorting the events
        sortDropDown = new JComboBox<>(SORT_OPTIONS);
        sortDropDown.setFont(new Font("Serif", Font.BOLD, 30));
        sortDropDown.addActionListener(e -> {
            switch (sortDropDown.getSelectedItem().toString()) {
                case "ALPHABETICAL":
                    events.sort(Comparator.comparing(Event::getName));
                    break;
                case "DATE":
                    events.sort(Comparator.comparing(Event::getDateTime));
                    break;
                case "REVERSE_ALPHABETICAL":
                    events.sort(Comparator.comparing(Event::getName).reversed());
                    break;
                case "REVERSE_DATE":
                    events.sort(Comparator.comparing(Event::getDateTime).reversed());
                    break;
            }
            updateDisplay();
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
    //Adds an event to the ArrayList and then calls updateDisplay()
    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }
    //Removes an event from the ArrayList and then calls updateDisplay()
    public void removeEvent(Event event) {
        events.remove(event);
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
                        if (event instanceof Deadline)
                            return true;
                        break;
                    case "Meetings":
                        if (event instanceof Meeting)
                            return true;
                        break;
                }
            }
        }
        return false;
    }
    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            if (!isFiltered(event)) {
                EventPanel eventPanel = new EventPanel(event);
                eventPanel.setParentPanel(this);
                displayPanel.add(eventPanel);
            }
        }
        revalidate();
        repaint();
    }
}

// Alphabetical sorting strategy: sorts events by names in ascending order
class AlphabeticalSort implements SortStrategy {
    public Comparator<Event> getComparator() {
        return Comparator.comparing(Event::getName);
    }
}

// Date sorting strategy: sorts events by their dates in ascending order
class DateSort implements SortStrategy {
    public Comparator<Event> getComparator() {
        return Comparator.comparing(Event::getDateTime);
    }
}

// Reverse alphabetical sorting strategy: sorts events by their names in descending order
class ReverseAlphabeticalSort implements SortStrategy {
    public Comparator<Event> getComparator() {
        return Comparator.comparing(Event::getName).reversed();
    }
}

// Reverse date sorting strategy: sorts event by their dates in descending order
class ReverseDateSort implements SortStrategy {
    public Comparator<Event> getComparator() {
        return Comparator.comparing(Event::getDateTime).reversed();
    }
}



