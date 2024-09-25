import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class EventPanel extends JPanel {
    //Private variable declaration
    private Event event;
    private ArrayList<String> eventInformation;
    private JButton completeButton;
    private JButton removeTaskButton;
    private EventListPanel parentPanel; //Reference to EventListPanel, needed for the remove task button
    //Final ints to avoid use of magic numbers
    private final int PANEL_WIDTH = 250;
    private final int PANEL_HEIGHT = 200;
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 30;

    //Constructor
    public EventPanel(Event event) {
        this.event = event;

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);

        displayPanel(); //Calling a method here to keep my code DRY
    }

    //Setter method to set the parentPanel to an instance of EventListPanel
    //This makes it to where EventListPanel methods can be accessed by EventPanel
    //So I can use the removeEvent method in EventListPanel
    //Whenever the remove task button is pressed, which will remove the
    //Event from the ArrayList completely
    public void setParentPanel(EventListPanel parentPanel) {
        this.parentPanel = parentPanel;
    }
    //This essentially replaces the paintComponent method
    //This is a lot easier to deal with, as paintComponent is a pain in the ass
    private void displayPanel() {
        this.removeAll();
        eventInformation = event.getDisplayString();

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        for (String item : eventInformation) {
            JLabel eventInformationLabel = new JLabel(item);
            eventInformationLabel.setFont(new Font("Serif", Font.BOLD, 14));
            eventInformationLabel.setBackground(Color.WHITE);
            eventInformationLabel.setForeground(Color.BLACK);
            infoPanel.add(eventInformationLabel);
        }
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(infoPanel);

        //Button that is used to set the status of a task to complete
        //Only events that are Meetings or Deadlines need the complete task button
        //Otherwise the button is not displayed
        //Not really needed in this version of the code, but in future iterations
        //If I ever wanted to add more events, this would be needed
        if (event instanceof Meeting || event instanceof Deadline) { //Checks if the event needs the complete button
            completeButton = new JButton("Complete Task");
            completeButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            completeButton.setFont(new Font("Serif", Font.BOLD, 10));
            completeButton.setBackground(Color.DARK_GRAY);
            completeButton.setForeground(Color.WHITE);
            completeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            completeButton.addActionListener(e -> {
                event.complete();
                displayPanel(); //Updates the panel with the new information
            });
            this.add(completeButton);
        }

        //Button that completely removes a task from the panel
        //Requires an instance of EventListPanel to be set to parentPanel
        //So that the removeEvent function can be used and the event
        //Can be completely removed from the ArrayList
        //Otherwise, if I were to just delete the panel, the event
        //Would be re-added whenever the repaint() function is called
        removeTaskButton = new JButton("Remove Task");
        removeTaskButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        removeTaskButton.setFont(new Font("Serif", Font.BOLD, 10));
        removeTaskButton.setBackground(Color.RED);
        removeTaskButton.setForeground(Color.WHITE);
        removeTaskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeTaskButton.addActionListener(e -> {
            if (parentPanel != null) { //Check if parentPanel is set
                parentPanel.removeEvent(event); //Call removeEvent on parentPanel
            }
        });
        this.add(removeTaskButton);

        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
