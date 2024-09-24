import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Event implements Completable{
    //Constructor
    public Deadline(String name, LocalDateTime deadline) {
        this.setName(name);
        this.setDateTime(deadline);
    }
    private boolean complete;
    //Sets complete variable to true when called, indicating the task that this deadline tracks is complete
    @Override
    public void complete() {
        complete = true;
    }
    //Returns whether the task this deadline tracks is complete
    public boolean isComplete() {
        return complete;
    }
    @Override
    public ArrayList<String> getDisplayString() {
        ArrayList<String> displayString = new ArrayList<>();
        displayString.add("Event Name: " + this.getName());
        displayString.add("Date and Time: " + this.getDateTime().toString());
        displayString.add("Completion Status: " + this.isComplete());
        return displayString;
    }
}
