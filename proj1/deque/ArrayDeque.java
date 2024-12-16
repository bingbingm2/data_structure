package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>  {
    private final int first = 4;
    private final int last = 5;
    private final int exceeded = 16;
    private final int reduce = 4;
    private final int defaultSize = 8;
    private T[] items;
    private int _size;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        items = (T[]) new Object[defaultSize];
        _size = 0;
        nextfirst = first;
        nextlast = last;
    }

    private int minusOne(int index) {
        if (index == 0) {
            index += (items.length - 1);
        } else {
            index -= 1;
        }
        return index;
    }

    private int addOne(int index) {
        if ((index + 1) == items.length) {
            index = 0;
        } else {
            index += 1;
        }
        return index;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, nextlast, a, 0, _size - nextlast);
        System.arraycopy(items, 0, a, (_size - 1 - nextfirst), addOne(nextfirst) % _size);
        items = a;
        nextfirst = items.length - 1;
        nextlast = addOne(_size - 1);
    }

    private void resizedown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int len = items.length - addOne(nextfirst);
        while ((double) _size / items.length < 0.25) {
            if (len < capacity) {
                System.arraycopy(items, addOne(nextfirst), a, 0, len);
                System.arraycopy(items, 0, a, len, capacity - len);
            }
            if (len >= capacity) {
                System.arraycopy(items, addOne(nextfirst), a, 0, capacity);
            }
            items = a;
            nextfirst = minusOne(items.length);
            nextlast = addOne(_size - 1);
        }
    }
    @Override
    public void addFirst(T item) {
        if (_size == items.length) {
            resize(_size * 2);
        }
        items[nextfirst] = item;
        nextfirst = minusOne(nextfirst);
        _size += 1;
    }
    @Override
    public void addLast(T item) {
        if (_size == items.length) {
            resize(_size * 2);
        }
        items[nextlast] = item;
        nextlast = addOne(nextlast);
        _size += 1;
    }
    @Override
    public T removeFirst() {
        if (_size == 0) {
            return null;
        }
        nextfirst = addOne(nextfirst);
        T item = items[nextfirst];
        items[nextfirst] = null;
        _size -= 1;
        if (((double) _size / items.length < 0.25 && items.length >= exceeded)) {
            resizedown(items.length / reduce);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (_size == 0) {
            return null;
        }
        nextlast = minusOne(nextlast);
        T item = items[nextlast];
        items[nextlast] = null;
        _size -= 1;
        if (((double) _size / items.length < 0.25 && items.length >= exceeded)) {
            resizedown(items.length / reduce);
        }
        return item;
    }
    @Override
    public int size() {
        return _size;
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (_size == 0 || index < 0 || index >= _size) {
            return null;
        }
        int i = addOne(nextfirst);
        i = (i + index) % items.length;
        return items[i];
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque ArrayDeque) {
            if (this.size() != ((Deque<?>) o).size()) {
                return false;
            }
            for (int i = 0; i < _size; i += 1) {
                if (!this.get(i).equals(ArrayDeque.get(i))) {
                    return false;
                }
            }
            return true;
        } else if (o instanceof Deque) {
            if (this.size() != ((Deque<?>) o).size()) {
                return false;
            }
            for (int i = 0; i < _size; i += 1) {
                if (!this.get(i).equals(get(i))) {
                    return false;
                }
            }
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int next;
        public ArrayDequeIterator() {
            next = 0;
        }
        public boolean hasNext() {
            return next < _size;
        }
        public T next() {
            T item = get(next);
            next += 1;
            return item;
        }
    }
}
