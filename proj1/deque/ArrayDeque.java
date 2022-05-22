package deque;

import java.lang.reflect.Array;

/**
 * Build the ArrayDeque class using arrays as the core data structure.
 * @author Irene Jiaxin Fan
*/

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length;
        nextLast = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * Assume that item is never null.
     */
    public void addFirst(T item) {
        size += 1;
        resize();
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    /**
     * Adds an item of type T to the back of the deque.
     * Assume that item is never null.
     */
    public void addLast(T item) {
        size += 1;
        resize();
        items[nextLast] = item;
        if (nextLast == items.length) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int index = getFirst();
        while (index != nextLast) {
            System.out.print(items[index] + " ");
            index += 1;
            if (index == items.length) {
                index = 0;
            }
        }
        System.out.println();
    }

    /**
     * Get the index of current first item of the deque.
     * Assume the deque is not empty.
     */
    private int getFirst() {
        if (nextFirst == items.length - 1) {
            return 0;
        }
        return nextFirst + 1;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
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
        return nextLast - 1;
    }

    /**
     * For arrays of length 16 or more, usage factor should always be at least 25%.
     */
    private void resize() {
        int newLength;
        T[] newArray;
        if (size >= 16 && size * 4 < items.length) {
            newLength = items.length / 2;
        } else if (size == items.length - 2) {
            newLength = 2 * size;
        } else {
            return;
        }
        newArray = (T[]) new Object[newLength];
        if (nextFirst < nextLast) {
            System.arraycopy(items, getFirst(), newArray, 0, size);
        } else {
            System.arraycopy(items, getFirst(), newArray, 0, items.length -getFirst());
            System.arraycopy(items, 0, newArray, size - 1, getLast() + 1);
        }
        nextFirst = newLength - 1;
        nextLast = size;
    }

    /**
     * public T get(int index): Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = getFirst() + index;
        if (i < items.length) {
            return items[i];
        } else {
            return items[i - items.length];
        }
    }

    /**
     * Returns whether the parameter o is equal to the Deque.
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as governed by the generic T’s equals method) in the same order.
     */
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque) || ((ArrayDeque) o).size() != this.size) {
            return false;
        }
        int index = this.getFirst();
        int indexO = ((ArrayDeque) o).getFirst();
        for (int i = 0; i < this.size; i++) {
            if (index + i >= this.items.length) {
                index = index + i - this.items.length;
            } else {
                index += i;
            }
            if (indexO + i >= ((ArrayDeque) o).items.length) {
                indexO = indexO + i - ((ArrayDeque) o).items.length;
            } else {
                indexO += i;
            }
            if (!this.items[index].equals(((ArrayDeque) o).items[indexO])) {
                return false;
            }
        }
        return true;
    }
}