import java.time.LocalDateTime;

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
