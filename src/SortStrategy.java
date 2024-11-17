import java.util.Comparator;

public interface SortStrategy {
    Comparator<Event> getComparator();
}
