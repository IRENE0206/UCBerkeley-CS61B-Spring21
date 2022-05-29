package deque;
import java.util.Comparator;

public class Ball implements Comparable<Ball> {
    private String name;
    private int weight;

    public Ball(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int compareTo(Ball b) {
        return this.weight - b.weight;
    }

    public static class NameComparator implements Comparator<Ball> {
        public int compare(Ball b1, Ball b2) {
            return b1.name.compareTo(b2.name);
        }
    }

    public static class WeightComparator implements Comparator<Ball> {
        public int compare(Ball b1, Ball b2) {
            return b1.weight - b2.weight;
        }
    }
}
