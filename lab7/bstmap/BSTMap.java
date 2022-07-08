package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    @Override
    public void clear() {
        this.size = 0;
        this.BST = null;
    }

    @Override
    public boolean containsKey(K k) {
        if (this.BST == null) {
            return false;
        }
        return find(this.BST, k) != null;
    }

    @Override
    public V get(K k) {
        if (this.BST == null) {
            return null;
        }
        Entry e = find(this.BST, k);
        if (e == null) {
            return null;
        } else {
            return e.value;
        }
    }

    private Entry find(Entry e, K k) {
        if (e == null || k == null) {
            return null;
        }
        if (e.key.equals(k)) {
            return e;
        } else if (e.key.compareTo(k) < 0) {
            return find(e.right, k);
        } else {
            return find(e.left, k);
        }
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        this.BST = insert(this.BST, key, value);
        this.size += 1;
    }

    private Entry insert(Entry e, K key, V value) {
        if (e == null) {
            return new Entry(key, value, null, null);
        } else if (e.key.compareTo(key) < 0) {
            e.right = insert(e.right, key, value);
        } else if (e.key.compareTo(key) > 0) {
            e.left = insert(e.left, key, value);
        }
        return e;
    }
    @Override
    public Set<K> keySet() {
        Iterator<K> keyIterator = this.iterator();
        Set<K> keySet = new HashSet<>();
        while (keyIterator.hasNext()) {
            keySet.add(keyIterator.next());
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        removed = null;
        this.BST = removeHelper(this.BST, key);
        if (removed == null) {
            return null;
        } else {
            this.size -= 1;
            return removed.value;
        }
    }

    private Entry removeHelper(Entry e, K k) {
        if (e == null) {
            return null;
        } else if (e.key.compareTo(k) < 0) {
            e.right = removeHelper(e.right, k);
        } else if (e.key.compareTo(k) > 0) {
            e.left = removeHelper(e.left, k);
        } else {
            removed = e;
            if (e.left == null) {
                return e.right;
            } else if (e.right == null) {
                return e.left;
            } else {
                e.right = swapSmallest(e, e.right);
            }
        }
        return e;
    }

    @Override
    public V remove(K key, V value) {
        removed = null;
        this.BST = removeHelper(this.BST, key, value);
        if (removed == null) {
            return null;
        } else {
            this.size -= 1;
            return value;
        }
    }

    private Entry removed;

    private Entry removeHelper(Entry e, K k, V v) {
        if (e == null) {
            return null;
        }
        int cmp = e.key.compareTo(k);
        if (cmp < 0) {
            e.right = removeHelper(e.right, k, v);
        } else if (cmp > 0) {
            e.left = removeHelper(e.left, k, v);
        } else if (e.value.equals(v)) {
            removed = e;
            if (e.left == null) {
                return e.right;
            } else if (e.right == null) {
                return e.left;
            } else {
                e.right = swapSmallest(e, e.right);
            }
        }
        return e;
    }

    private Entry swapSmallest(Entry e, Entry right) {
        if (right.left == null) {
            e.key = right.key;
            e.value = right.value;
            return right.right;
        } else {
            right.left = swapSmallest(e, right.left);
            return right;
        }
    }


    @Override
    public Iterator<K> iterator() {
        Entry e = this.BST;
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {

        private Entry curr;
        public BSTMapIterator() {
            curr = BST;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public K next() {
            Entry result = curr;
            curr = nextEntry(BST, curr.key);
            return result.key;
        }
    }

    private Entry nextEntry(Entry e, K k) {
        if (e == null) {
            return null;
        } else if (e.key.compareTo(k) <= 0) {
            return nextEntry(e.right, k);
        } else {
            Entry leftNext = nextEntry(e.left, k);
            if (leftNext == null) {
                return e;
            } else {
                return leftNext;
            }
        }
    }
    private int size;
    private Entry BST;

    private class Entry {
        K key;
        V value;
        Entry left, right;

        Entry(K k, V v, Entry l, Entry r) {
            this.key = k;
            this.value = v;
            this.left = l;
            this.right = r;
        }

    }

}