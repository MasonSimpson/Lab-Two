import java.time.*;

public class Meeting extends Event implements Completable {
    // Private variable declaration
    private LocalDateTime endDateTime; //Holds the time and date the meeting is complete
    private String location; //Where the meeting is held
    private boolean complete; //Boolean variable that holds whether the meeting is complete
    //Constructor
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        this.setName(name);
        this.setDateTime(start);
        this.location = location;
        this.endDateTime = end;
    }
    //Indicates the meeting is complete when called
    public void complete() {
        complete = true;
    }
    //Returns whether the meeting is complete
    public boolean isComplete() {
        return complete;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    public Duration getDuration() {
        return Duration.between(getDateTime(), endDateTime);
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEndTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

}
