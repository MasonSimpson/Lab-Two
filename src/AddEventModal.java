import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog {
    //Private variable declaration
    private EventFactory eventFactory = new EventFactory();
    private EventListPanel eventListPanel;
    private AddEventModal modal;
    private record Attribute(String name, JComponent value){}
    private ArrayList<Attribute> attributes;
    private JPanel infoCollectorPanel;
    private JComboBox<String> eventTypeComboBox;
    //No magic numbers used here :)
    private final int INFO_PANEL_WIDTH = 600;
    private final int INFO_PANEL_HEIGHT = 400;
    private final int PANEL_WIDTH = 600;
    private final int PANEL_HEIGHT = 500;

    public static final String[] eventTypes = {"Meeting", "Deadline"};

    //Constructor for modal
    public AddEventModal(EventListPanel eventListPanel) {
        modal = this;
        this.eventListPanel = eventListPanel;

        this.setTitle("Add Event");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(addEventPanel());
        this.pack();
        this.setVisible(true);
    }

    //Creates panel that will hold all components within modal
    private JPanel addEventPanel() {

        JPanel panel = new JPanel();
        attributes = new ArrayList<>();

        infoCollectorPanel = new JPanel();
        infoCollectorPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT));
        infoCollectorPanel.setBackground(Color.WHITE);

        eventTypeComboBox = new JComboBox<>(eventTypes);
        eventTypeComboBox.addActionListener(getEventChooser());

        panel.add(eventTypeComboBox);
        panel.add(infoCollectorPanel);

        JButton addEventButton = new JButton("Submit");
        addEventButton.addActionListener(getEventCreator());

        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.add(addEventButton);

        return panel;
    }

    //Returns an ActionListener that adds the correct fields to the infoCollectorPanel
    //Depending on which Event is selected from the EventTypeComboBox
    private ActionListener getEventChooser() {
        return e -> {
            attributes.clear();
            infoCollectorPanel.removeAll();
            switch (eventTypeComboBox.getSelectedIndex()) {
                case 0: { // Meeting
                    attributes.add(new Attribute("Name", new JTextField(10)));
                    attributes.add(new Attribute("Start Date and Time", new DateTimeInputPanel())); // Custom panel for start date-time
                    attributes.add(new Attribute("End Date and Time", new DateTimeInputPanel())); // Custom panel for end date-time
                    attributes.add(new Attribute("Location", new JTextField(10)));
                    break;
                }
                case 1: { // Deadline
                    attributes.add(new Attribute("Name", new JTextField(10)));
                    attributes.add(new Attribute("Date and Time", new DateTimeInputPanel())); // Custom panel for deadline date-time
                    break;
                }
            }
            attributes.forEach(attr -> {
                infoCollectorPanel.add(new JLabel(attr.name));
                infoCollectorPanel.add(attr.value);
            });
            infoCollectorPanel.revalidate();
            infoCollectorPanel.repaint();
        };
    }

    //Returns an ActionListener that creates an Event based on the fields in infoCollectionPanel
    //Calls appropriate constructor based on EventTypeComboBox
    //Disposes the creator modal after finished
    private ActionListener getEventCreator() {
        return e -> {
            String name = "";
            LocalDateTime startDateTime = null;
            LocalDateTime endDateTime = null;
            LocalDateTime deadlineDateTime = null;
            String location = "";

            //Get inputs from fields
            for (Attribute attribute : attributes) {
                JComponent component = attribute.value();
                switch (attribute.name()) {
                    case "Name":
                        name = ((JTextField) component).getText();
                        break;
                    case "Start Date and Time":
                        startDateTime = ((DateTimeInputPanel) component).getLocalDateTime();
                        break;
                    case "End Date and Time":
                        endDateTime = ((DateTimeInputPanel) component).getLocalDateTime();
                        break;
                    case "Date and Time":
                        deadlineDateTime = ((DateTimeInputPanel) component).getLocalDateTime();
                        break;
                    case "Location":
                        location = ((JTextField) component).getText();
                        break;
                }
            }

            //Create event based on selected type
            Event newEvent;
            if (eventTypeComboBox.getSelectedIndex() == 0) { // Meeting
                newEvent = eventFactory.createEvent(eventTypes[0], name, location, startDateTime, endDateTime);
            } else { //Deadline
                newEvent = eventFactory.createEvent(eventTypes[1], name, null, deadlineDateTime);
            }

            //Add the new event to the event list panel
            eventListPanel.addEvent(newEvent);

            //Close the modal
            modal.dispose();
        };
    }

    //Custom JPanel class for separate date-time input fields
    //Makes working with LocalDateTime much easier
    //And improves readability of code
    private static class DateTimeInputPanel extends JPanel {
        //Private variable declaration
        private JTextField yearField;
        private JTextField monthField;
        private JTextField dayField;
        private JTextField hourField;
        private JTextField minuteField;

        //Constructor
        public DateTimeInputPanel() {
            //Declare and initialize input fields
            //Column based on amount of digits in each field
            yearField = new JTextField(4);
            monthField = new JTextField(2);
            dayField = new JTextField(2);
            hourField = new JTextField(2);
            minuteField = new JTextField(2);

            //Adds the input fields and labels to the panel
            add(new JLabel("Year:"));
            add(yearField);
            add(new JLabel("Month:"));
            add(monthField);
            add(new JLabel("Day:"));
            add(dayField);
            add(new JLabel("Hour:"));
            add(hourField);
            add(new JLabel("Minute:"));
            add(minuteField);
        }

        //Method to get LocalDateTime from the input fields
        //Parses the strings in the text fields to integers
        public LocalDateTime getLocalDateTime() {
            int year = Integer.parseInt(yearField.getText());
            int month = Integer.parseInt(monthField.getText());
            int day = Integer.parseInt(dayField.getText());
            int hour = Integer.parseInt(hourField.getText());
            int minute = Integer.parseInt(minuteField.getText());

            return LocalDateTime.of(year, month, day, hour, minute);
        }
    }
}
