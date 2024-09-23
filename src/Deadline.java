import java.time.LocalDateTime;

public class Deadline extends Event implements Completable{
    //Constructor
    public Deadline(String name, LocalDateTime deadline) {
        this.setName(name);
        this.setDateTime(deadline);
    }
    private boolean complete;
    //Sets complete variable to true when called, indicating the task that this deadline tracks is complete
    public void complete() {
        complete = true;
    }
    //Returns whether the task this deadline tracks is complete
    public boolean isComplete() {
        return complete;
    }
}
