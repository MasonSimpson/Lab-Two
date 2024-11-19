import java.time.LocalDateTime;

// Creates an event based on the type and parameters provided
// Returns an event instance of specified type
public class EventFactory {
    public Event createEvent(String type, String name, String location, LocalDateTime... params) {
        switch (type) {
            case "Deadline":
                if (params.length < 1)
                    throw new IllegalArgumentException("Deadline requires one LocalDateTime parameter.");
                return new Deadline(name, params[0]);
            case "Meeting":
                if (params.length < 2)
                    throw new IllegalArgumentException("Meeting requires start and end times as LocalDateTime.");
                if (location == null || location.isEmpty())
                    throw new IllegalArgumentException("Meeting requires a valid location.");
                return new Meeting(name, params[0],params[1],location);
            default:
                throw new IllegalArgumentException("Unknown event type.");
        }
    }
}
