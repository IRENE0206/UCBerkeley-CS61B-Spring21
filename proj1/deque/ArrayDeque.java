package deque;

/**
 * Build the ArrayDeque class using arrays as the core data structure.
 * @author Irene Jiaxin Fan
*/

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = items.length - 1;
        this.nextLast = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * Assume that item is never null.
     */
    @Override
    public void addFirst(T item) {
        resize();
        size += 1;
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst = (nextFirst - 1) % items.length;
        }
    }

    /**
     * Adds an item of type T to the back of the deque.
     * Assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        resize();
        size += 1;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
    }

    // Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        int firstIndex = getFirst();
        int actualIndex = firstIndex;
        for (int index = 0; index < size; index++) {
            System.out.print(items[actualIndex] + " ");
            actualIndex = (actualIndex + 1) % items.length;
        }
        System.out.println();
    }

    /**
     * Get the index of current first item of the deque.
     * Assume the deque is not empty.
     */
    private int getFirst() {
        return (nextFirst + 1) % items.length;
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
        size -= 1;
        resize();
        int index = getFirst();
        T item = items[index];
        nextFirst = index;
        return item;
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
        size -= 1;
        resize();
        int index = getLast();
        T item = items[index];
        nextLast = index;
        return item;
    }

    /**
     * Get the index of current last item of the deque.
     * Assume the deque is not empty.
     */
    private int getLast() {
        if (nextLast == 0) {
            return items.length - 1;
        }
        return (nextLast - 1) % items.length;
    }

    /**
     * For arrays of length 16 or more, usage factor should always be at least 25%.
     */
    private void resize() {
        int newLength;
        T[] newArray;
        if (size >= 16 && size * 4 < items.length) {
            if (isEmpty()) {
                newLength = 8;
            } else {
                newLength = items.length / 2;
            }
        } else if (size == items.length) {
            newLength = 2 * size;
        } else {
            return;
        }
        newArray = (T[]) new Object[newLength];
        if (!isEmpty()) {
            int prevFirstIndex = getFirst();
            int prevLastIndex = getLast();
            if (prevFirstIndex < prevLastIndex) {
                System.arraycopy(items, prevFirstIndex, newArray, 0, size);
            } else {
                System.arraycopy(items, prevFirstIndex, newArray, 0, items.length - prevFirstIndex);
                System.arraycopy(items, 0, newArray, size - 1,  prevLastIndex + 1);
            }
        }
        items = newArray;
        nextFirst = newLength - 1;
        nextLast = size;
    }

    /**
     * public T get(int index): Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = getFirst() + index;
        return items[i % items.length];
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
        int index = getFirst();
        for (int i = 0; i < size; i++) {
            index = (index + i) % items.length;
            if (!items[index].equals(oPro.get(i))) {
                return false;
            }
        }
        return true;
    }
}