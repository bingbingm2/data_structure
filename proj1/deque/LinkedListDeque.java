package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int _size;
    private TNode sentinel;

    private class TNode {
        private T item;
        private TNode next;
        private TNode previous;

        public TNode(T i, TNode n, TNode prev) {
            item = i;
            next = n;
            previous = prev;
        }
    }

    public LinkedListDeque() {
        //create an empty linkedlist
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        _size = 0;
    }
    private LinkedListDeque(T item) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.previous = sentinel.next;
        _size = 1;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel.next, sentinel);
        sentinel.next.next.previous = sentinel.next;
        _size += 1;
    }
    @Override
    public void addLast(T item) {
        sentinel.previous = new TNode(item, sentinel, sentinel.previous);
        sentinel.previous.previous.next = sentinel.previous;
        _size += 1;
    }

    @Override
    public int size() {
        return _size;
    }
    @Override
    public void printDeque() {
        TNode p = sentinel.next;
        while (p.next != null) {
            System.out.print(p.item +  " ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        TNode p = sentinel.next;
        T item = p.item;
        if (isEmpty()) {
            return null;
        }
        _size -= 1;
        sentinel.next = p.next;
        p.next.previous = sentinel;
        return item;
    }
    @Override
    public T removeLast() {
        TNode p = sentinel.previous;
        if (isEmpty()) {
            return null;
        }
        T item = p.item;
        _size -= 1;
        sentinel.previous = p.previous;
        p.previous.next = sentinel;
        return item;
    }
    @Override
    public T get(int index) {
        TNode p = sentinel.next;
        if (index < 0 || _size == 0) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        for (int i = 0; i < index; i += 1) {
            if (p.next != null) {
                p = p.next;
            }
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return getHelper(index, sentinel.next);
    }

    private T getHelper(int index, TNode p) {
        if (index < 0 || index > _size - 1) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getHelper(index - 1, p.next);
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>  {
        private TNode p;
        public LinkedListDequeIterator() {
            p = sentinel.next;
        }
        public boolean hasNext() {
            return p != sentinel;
        }
        public T next() {
            T item = p.item;
            p = p.next;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque LinkedListDeque) {
            if (this.size() != ((Deque<?>) o).size()) {
                return false;
            }
            for (int i = 0; i < _size; i += 1) {
                if (!this.get(i).equals(LinkedListDeque.get(i))) {
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
            return true;
        }
        return false;
    }
}
