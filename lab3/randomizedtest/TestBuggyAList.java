package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctAList = new AListNoResizing<>();
        BuggyAList<Integer> wrongAList = new BuggyAList<>();
        correctAList.addLast(4);
        correctAList.addLast(5);
        correctAList.addLast(6);
        wrongAList.addLast(4);
        wrongAList.addLast(5);
        wrongAList.addLast(6);
        assertEquals(correctAList.size(), wrongAList.size());
        for (int i = 0; i < 3; i++) {
            assertEquals(correctAList.removeLast(), wrongAList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                assertEquals(size, size2);
            } else if (operationNumber == 2 && L.size() > 0 && L2.size() > 0) {
                // getLast
                int getLast1 = L.getLast();
                int getLast2 = L2.getLast();
                assertEquals(getLast1, getLast2);
            } else if (L.size() > 0 && L2.size() > 0) {
                // removeLast
                int removeLast1 = L.removeLast();
                int removeLast2 = L2.removeLast();
                assertEquals(removeLast1, removeLast2);
                System.out.println("removeLast: " + removeLast1);
            }
        }
    }
}
