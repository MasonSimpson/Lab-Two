import java.time.LocalDateTime;

public class Deadline extends Event implements Completable{
    public Deadline(String name, LocalDateTime deadline) {
        setName(name);
        setDateTime(deadline);
    }
    public boolean complete;
    public void complete() {
        complete = true;
    }
    public boolean isComplete() {
        return complete;
    }
}
