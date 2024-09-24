import java.time.*;
import java.util.ArrayList;

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
   @Override
    public void complete() {
        complete = true;
    }
    //Returns whether the meeting is complete
    public boolean isComplete() {
        return complete;
    }
    // Getter for the end time of the meeting
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    //Getter for the duration of the meeting
    public Duration getDuration() {
        return Duration.between(getDateTime(), endDateTime);
    }
    //Getter for the meeting location
    public String getLocation() {
        return location;
    }
    //Setter for the meeting location
    public void setLocation(String location) {
        this.location = location;
    }
    //Setter for the end time of the meeting
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
    @Override
    public ArrayList<String> getDisplayString() {
        ArrayList<String> displayString = new ArrayList<>();
        displayString.add("Event Name: " + this.getName());
        displayString.add("Date and Time: " + this.getDateTime().toString());
        displayString.add("Location: " + this.getLocation());
        displayString.add("Duration: " + this.getDuration().toString());
        displayString.add("Completion Status: " + this.isComplete());
        return displayString;
    }

}
