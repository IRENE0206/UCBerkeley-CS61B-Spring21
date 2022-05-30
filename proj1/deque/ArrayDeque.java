package deque;

import java.util.Iterator;

/**
 * Builds the ArrayDeque class using arrays as the core data structure.
 * @author Irene Jiaxin Fan
*/

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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
        items[nextFirst] = item;
        nextFirst = indexBefore(nextFirst);
        size += 1;
    }

    /**
     * Gets the index before a given index.
     */
    private int indexBefore(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * Assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = indexAfter(nextLast);
        size += 1;
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
        int actualIndex = indexAfter(nextFirst);
        for (int index = 0; index < size; index++) {
            System.out.print(items[actualIndex] + " ");
            actualIndex = indexAfter(actualIndex);
        }
        System.out.println();
    }

    /**
     * Gets the index after the given index.
     */
    private int indexAfter(int index) {
        return (index + 1) % items.length;
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
        resize();
        int first = indexAfter(nextFirst);
        T item = items[first];
        nextFirst = first;
        size -= 1;
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
        resize();
        int last = indexBefore(nextLast);
        T item = items[last];
        nextLast = last;
        size -= 1;
        return item;
    }

    /**
     * For arrays of length 16 or more, usage factor should always be at least 25%.
     */
    private void resize() {
        int newLength;
        T[] newArray;
        if (items.length >= 16 && (size - 1) * 4 < items.length) {
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
            int prevFirstIndex = indexAfter(nextFirst);
            int prevLastIndex = indexBefore(nextLast);
            if (prevFirstIndex < prevLastIndex) {
                System.arraycopy(items, prevFirstIndex, newArray, 0, size);
            } else {
                int secondPartLength = items.length - prevFirstIndex;
                System.arraycopy(items, prevFirstIndex, newArray, 0, secondPartLength);
                System.arraycopy(items, 0, newArray, secondPartLength,  prevLastIndex + 1);
            }
        }
        items = newArray;
        nextFirst = newLength - 1;
        nextLast = size;
    }

    /**
     * public T get(int index): Gets the item at the given index.
     * If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = indexAfter(nextFirst) + index;
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
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(oPro.get(i))) {
                return false;
            }
        }
        return true;
    }

    private class NewIterator<T> implements Iterator<T> {
        int current;
        ArrayDeque<T> arrayDeque;
        private NewIterator(ArrayDeque<T> aDeque) {
            current = 0;
            arrayDeque = aDeque;
        }

        @Override
        public boolean hasNext() {
            return current < arrayDeque.size();
        }

        @Override
        public T next() {
            current += 1;
            return arrayDeque.get(current - 1);
        }
    }
    public Iterator<T> iterator() {
        return new NewIterator<>(this);
    }
}
