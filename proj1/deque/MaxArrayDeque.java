package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    // Creates a MaxArrayDeque with the given Comparator
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    /**
     * Returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null.
     * */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < size(); i++) {
            T current = this.get(i);
            if (this.comparator.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }

    /**
     * Returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < size(); i++) {
            T current = this.get(i);
            if (c.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }
}
