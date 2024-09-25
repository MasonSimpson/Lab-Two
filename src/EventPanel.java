import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class EventPanel extends JPanel {
    //Private variable declaration
    private Event event;
    private ArrayList<String> eventInformation;
    private JButton completeButton;
    private JButton removeTaskButton;
    private EventListPanel parentPanel; //Reference to EventListPanel
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

        displayPanel();
    }

    //Setter method to set the parentPanel
    public void setParentPanel(EventListPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

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

        completeButton = new JButton("Complete Task");
        completeButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        completeButton.setFont(new Font("Serif", Font.BOLD, 10));
        completeButton.setBackground(Color.DARK_GRAY);
        completeButton.setForeground(Color.WHITE);
        completeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        completeButton.addActionListener(e -> {
            event.complete();
            displayPanel();
        });
        this.add(completeButton);

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
