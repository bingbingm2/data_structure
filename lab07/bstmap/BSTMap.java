package bstmap;

import java.util.Iterator;
import java.util.PropertyPermission;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;
    private int size;

    private class BSTNode {
        private K K;
        private V V;
        private BSTNode left;
        private BSTNode right;

        private BSTNode(K K, V V) {
            this.K = K;
            this.V = V;
        }
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return find(root, key) != null;
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            return find(root, key).V;
        }
        return null;
    }
    private BSTNode find(BSTNode T, K k) {
        if (T == null) {
            return null;
        }
        int comp = k.compareTo(T.K);
        if (comp < 0) {
            return find(T.left, k);
        }
        else if (comp > 0) {
            return find(T.right, k);
        }
        else {
            return T;
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    public void printInOrder() {
        throw new UnsupportedOperationException();
    }
    private BSTNode insert(BSTNode T, K ik, V iv) {
        if (T == null) {
            size += 1;
            return new BSTNode(ik, iv);
        }
        int comp = ik.compareTo(T.K);
        if (comp < 0) {
            T.left = insert(T.left, ik, iv);
        }
        else if (comp > 0) {
            T.right = insert(T.right, ik, iv);
        } else {
            T.V = iv;
        }
        return T;

    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
