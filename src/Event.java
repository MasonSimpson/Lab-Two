import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Event implements Comparable<Event> {
    //Private variable declaration
    private String name;
    private LocalDateTime dateTime;
    //Getter for event name
    public String getName() {
        return name;
    }
    //Getter for the date and time of the event
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    //Setter for event name
    public void setName(String name) {
        this.name = name;
    }
    //Setter for date and time of the event
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    //Returns a String format of information for the EventPanel
    public ArrayList<String> getDisplayString() {
        ArrayList<String> displayString = new ArrayList<>();
        displayString.add(getName());
        displayString.add(getDateTime().toString());
        return displayString;
    }
    //This method is overriden in Meeting.java and Deadline.java
    //I just needed a simple version of it here
    public boolean isComplete(){
        return true;
    }
    //This method is overridden in Meeting.java and Deadline.java
    public void complete() {}
    /* Takes an event,'e', and
       Compares it with 'this' dateTime variable
       Returns an int based on the result of the comparison
       Positive int = 'this' event happens before event e
       Negative int = 'this' event happens after event e
       0 = both events happen at the same time*/
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
