import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class EventPanel extends JPanel {
    //Private variable declaration
    private Event event;
    private ArrayList<String> eventInformation;
    private JButton completeButton;
    //Constant ints for panel height/widths to make them easier to edit
    private final int PANEL_WIDTH = 250;
    private final int PANEL_HEIGHT = 200;
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 30;

    public EventPanel(Event event) {
        //Sets the event to be displayed to the event that is passed into the function
        this.event = event;

        //Size and color of event panel
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);

        displayPanel();
    }

    //Updates the panel with latest event information
    private void displayPanel() {
        //Completely resets the Event Panel
        this.removeAll();

        //Get the display strings from the event
        eventInformation = event.getDisplayString();

        //Create a panel for the event information
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Vertical layout

        //Create labels for each piece of event information and add them to the info panel
        for (String item : eventInformation) {
            JLabel eventInformationLabel = new JLabel(item);
            eventInformationLabel.setFont(new Font("Serif", Font.BOLD, 14));
            eventInformationLabel.setBackground(Color.WHITE);
            eventInformationLabel.setForeground(Color.BLACK);
            infoPanel.add(eventInformationLabel);
        }
        //Add the info panel to the main panel
        this.add(infoPanel);

        //Creates a button to set the completion status to true
        completeButton = new JButton("Complete Task");
        completeButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        completeButton.setFont(new Font("Serif", Font.BOLD, 10));
        completeButton.setBackground(Color.DARK_GRAY);
        completeButton.setForeground(Color.WHITE);
        //Adds an action listener using a lambda expression to change completion status and refresh the panel
        completeButton.addActionListener(e -> {
            event.complete();
            displayPanel();
        });
        this.add(completeButton);

        //Refresh the panel
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
