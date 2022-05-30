package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

// Perform some basic ArrayDeque tests.
public class ArrayDequeTest {

    // Adds a few things to the list, checks get() is correct, finally prints the results.
    @Test
    public void addGetTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        assertTrue("A newly initialized ArrayDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");
        assertEquals("front", ad1.get(0));
        assertEquals(1, ad1.size());
        assertFalse("lld1 should now contain 1 item", ad1.isEmpty());
        ad1.addLast("middle");
        assertEquals("middle", ad1.get(1));
        assertEquals(2, ad1.size());
        ad1.addLast("back");
        assertEquals("back", ad1.get(2));
        assertEquals(3, ad1.size());
        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    // Tests removing from an empty deque
    public void removeEmpty() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    // Check if you can create ArrayDeque with different parameterized types.
    public void multipleParamTest() {

        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigArrayDequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 9; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }

    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                L2.addFirst(randVal);
            } else if (operationNumber == 2) {
                // size
                int size = L.size();
                int size2 = L2.size();
                assertEquals(size, size2);
            } else if (operationNumber == 3 && L.size() > 0 && L2.size() > 0) {
                // getLast
                int removeFirst1 = L.removeFirst();
                int removeFirst2 = L2.removeFirst();
                assertEquals(removeFirst1, removeFirst2);
            } else if (L.size() > 0 && L2.size() > 0) {
                // removeLast
                int removeLast1 = L.removeLast();
                int removeLast2 = L2.removeLast();
                assertEquals(removeLast1, removeLast2);
            }
        }
        assertTrue("L and L2 should be equal", L2.equals(L));
    }

}