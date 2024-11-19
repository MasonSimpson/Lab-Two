import javax.swing.*;
import java.time.LocalDateTime;

public class EventPlanner {

    public static void main(String[] args) {
        EventListPanel eventListPanel = new EventListPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(eventListPanel);
        addDefaultEvents(eventListPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel events) {
        EventFactory factory = new EventFactory();

        events.addEvent(factory.createEvent(
                "Deadline",
                "Default Deadline",
                null,
                LocalDateTime.of(2024,12,7,17,0)
        ));

        events.addEvent(factory.createEvent(
                "Meeting",
                "Default Meeting",
                "Default Location",
                LocalDateTime.of(2024,12,7,17,0),
                LocalDateTime.of(2024,12,7,18,0)
        ));
    }
}
