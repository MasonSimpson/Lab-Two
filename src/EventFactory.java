import java.time.LocalDateTime;

// Creates an event based on the type and parameters provided
// Returns an event instance of specified type
public class EventFactory {
    public Event createEvent(String type, String name, LocalDateTime... params) {
        return switch (type) {
            case "Deadline" -> new Deadline(name, params[0]); // Deadline requires one parameter: dateTime
            case "Meeting" -> {
                if (params.length < 3) {
                    throw new IllegalArgumentException("Meeting requires start, end, and location parameters.");
                }
                yield new Meeting(name, params[0], params[1], params[2].toString());
            }
            default -> throw new IllegalArgumentException("Invalid event type: " + type);
        };
    }
}
