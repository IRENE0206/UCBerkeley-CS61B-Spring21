package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        Comparator<Ball> c1 = new Ball.NameComparator();
        Comparator<Ball> c2 = new Ball.WeightComparator();
        MaxArrayDeque<Ball> balls = new MaxArrayDeque<>(c1);
        Ball ball1 = new Ball("a", 1);
        Ball ball2 = new Ball("b", 3);
        Ball ball3 = new Ball("c", 2);
        balls.addFirst(ball1);
        balls.addLast(ball2);
        balls.addLast(ball3);
        assertEquals(ball3, balls.max());
    }

    @Test
    public void maxComparatorTest() {
        Comparator<Ball> c1 = new Ball.NameComparator();
        Comparator<Ball> c2 = new Ball.WeightComparator();
        MaxArrayDeque<Ball> balls = new MaxArrayDeque<>(c1);
        Ball ball1 = new Ball("a", 1);
        Ball ball2 = new Ball("b", 3);
        Ball ball3 = new Ball("c", 2);
        balls.addFirst(ball1);
        balls.addLast(ball2);
        balls.addLast(ball3);
        assertEquals(ball2, balls.max(c2));
    }
}