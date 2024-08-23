package example;

public class Interval<E extends Comparable<E>> {
    private final E start;
    private final E end;

    public Interval(E start, E end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start must be less than or equal to end.");
        }
        this.start = start;
        this.end = end;
    }

    public boolean within(E value) {
        return (value.compareTo(start) >= 0) && (value.compareTo(end) <= 0);
    }

    public boolean subinterval(Interval<E> other) {
        return (start.compareTo(other.start) >= 0) && (end.compareTo(other.end) <= 0);
    }

    public boolean overlaps(Interval<E> other) {
        return (start.compareTo(other.end) <= 0) && (end.compareTo(other.start) >= 0);
    }

    @Override
    public String toString() {
        return "Interval [" + start + " to " + end + "]";
    }
}
