import java.awt.*;
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
        //Declaring variables to be used to the Meeting and Deadline objects
        LocalDateTime deadlineDateTime = LocalDateTime.of(2024, 12, 7, 17, 0);
        LocalDateTime meetingStartDateTime = LocalDateTime.of(2024, 12, 8, 17, 0);
        LocalDateTime meetingEndDateTime = LocalDateTime.of(2024, 12, 8, 18, 0);
        String deadlineName = "A Default Deadline";
        String meetingName = "A Default Meeting";
        String meetingLocation = "A Random Location";
        //Creates a meeting and deadline object
        Deadline deadline = new Deadline(deadlineName, deadlineDateTime);
        Meeting meeting = new Meeting(meetingName, meetingStartDateTime, meetingEndDateTime, meetingLocation);
        //Adds the deadline and meeting objects to the ArrayList so that it can work with filters
        events.addEvent(deadline);
        events.addEvent(meeting);
    }
}
