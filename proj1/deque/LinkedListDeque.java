package deque;

/**
 * Build the LinkedListDeque class, which is LinkedList based.
 * @author Irene Jiaxin Fan
 */
public class LinkedListDeque<T> {
    private LinkedNode sentinel;
    public int size;
    /**
     * Build LinkedList data structure using circular sentinel topology.
     */
    private class LinkedNode {
        public T item;
        public LinkedNode prev;
        public LinkedNode next;

        public LinkedNode(T i, LinkedNode p, LinkedNode n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }
    // Creates an empty linked list deque.
    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     */
    public void addFirst(T item) {
        sentinel.next = new LinkedNode(item, sentinel, sentinel.next);
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     */
    public void addLast(T item) {
        sentinel.prev = new LinkedNode(item, sentinel.prev, sentinel);
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        LinkedNode p = sentinel;
        while (p.next != sentinel) {
            if (index == 0) {
                return p.next.item;
            }
            p = p.next;
            index -= 1;
        }
        return null;
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);
    }

    // Helper-method of getRecursive.
    private T getRecursiveHelper(LinkedNode s, int index) {
        if (s.next != sentinel && index == 0) {
            return s.next.item;
        } else if (index < 0 || s.next == sentinel) {
            return null;
        } else {
            return getRecursiveHelper(s.next, index - 1);
        }
    }
}