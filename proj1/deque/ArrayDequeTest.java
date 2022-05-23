package deque;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Perform some basic ArrayDeque tests.
 */
public class ArrayDequeTest {

    /**
     * Adds a few things to the list, checks get() is correct, finally prints the results.
     */
    @Test
    public void addGetTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        assertTrue("A newly initialized ArrayDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");
        assertEquals(1, ad1.size());
        assertFalse("lld1 should now contain 1 item", ad1.isEmpty());
        ad1.addLast("middle");
        assertEquals(2, ad1.size());
        ad1.addLast("back");
        assertEquals(3, ad1.size());
        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /**
     * Tests removing from an empty deque
     */
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
}