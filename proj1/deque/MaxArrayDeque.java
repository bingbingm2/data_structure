package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> c1;
    private int index = 0;

    public MaxArrayDeque(Comparator<T> c) {
        //creates a MaxArrayDeque with the given Comparator.
        c1 = c;
    }

    public T max() {
        // max in itself;
        // returns the maximum element in the deque as governed by the previously given Comparator.
        if (size() == 0) {
            return null;
        }
        for (int i = 0; i < size(); i += 1) {
            if (c1.compare(get(i), get(index)) >= 0) {
                index = i;
            }
        }
        return get(index);
    }

    public T max(Comparator<T> c) {
        /* max in different from the given
        returns the maximum element in the deque as governed by the parameter Comparator c.
        If the MaxArrayDeque is empty, simply return null.*/
        if (size() == 0) {
            return null;
        }
        for (int i = 0; i < size(); i += 1) {
            if (c.compare(get(i), get(index)) >= 0) {
                index = i;
            }
        }
        return get(index);
    }
}
