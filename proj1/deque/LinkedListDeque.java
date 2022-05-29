package deque;

/**
 * Build the LinkedListDeque class, which is LinkedList based.
 * @author Irene Jiaxin Fan
 */
public class LinkedListDeque<T> implements Deque<T> {
    private LinkedNode sentinel;
    private int size;
    /**
     * Build LinkedList data structure using circular sentinel topology.
     */
    private class LinkedNode {
        public T item;
        public LinkedNode prev;
        public LinkedNode next;

        public LinkedNode() {
            this.prev = this;
            this.next = this;
            this.item = null;
        }

        public LinkedNode(T i, LinkedNode p, LinkedNode n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }
    // Creates an empty linked list deque.
    public LinkedListDeque() {
       this.sentinel = new LinkedNode();
        this.size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addFirst(T item) {
        LinkedNode firstNode = new LinkedNode(item, sentinel, sentinel.next);
        size += 1;
        if (isEmpty()) {
            sentinel.prev = firstNode;
        } else {
            sentinel.next.prev = firstNode;
        }
        sentinel.next = firstNode;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        LinkedNode lastNode = new LinkedNode(item, sentinel.prev, sentinel);
        size += 1;
        if (isEmpty()) {
            sentinel.next = lastNode;
        } else {
            sentinel.prev.next = lastNode;
        }
        sentinel.prev = lastNode;
    }

    // Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    @Override
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
        if (!isEmpty() && index == 0) {
            return s.next.item;
        } else if (index < 0 || isEmpty()) {
            return null;
        } else {
            return getRecursiveHelper(s.next, index - 1);
        }
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        LinkedNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Returns whether the parameter o is equal to the Deque.
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as governed by the generic T’s equals method) in the same order.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Deque)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        Deque<T> oPro = (Deque<T>) o;
        if (oPro.size() != this.size()) {
            return false;
        }
        LinkedNode p = this.sentinel;
        if (oPro.isEmpty() && this.isEmpty()) {
            return true;
        }
        for (int i = 0; i < size; i++) {
            if (!p.next.item.equals(oPro.get(i))) {
                return false;
            }
            p = p.next;
        }
        return true;
    }

}