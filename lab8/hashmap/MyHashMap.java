package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Irene Jiaxin Fan
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        nums = 0;
        size = 16;
        loadFactor = 0.75;
        buckets = createTable(size);
    }

    @Override
    public boolean containsKey(K key) {
        return findNode(buckets[findBucket(key)], key) != null;
    }

    private int findBucket(K key) {
        return Math.floorMod(key.hashCode(), size);
    }
    private Node findNode(Collection<Node> bucket, K key) {
        if (bucket == null) {
            return null;
        }
        for (Node node: bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = findNode(buckets[findBucket(key)], key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public int size() {
        return nums;
    }

    @Override
    public void put(K key, V value) {
        int bucketIndex = findBucket(key);
        Collection<Node> bucket = buckets[bucketIndex];
        Node node = findNode(bucket, key);
        if (bucket == null) {
            buckets[bucketIndex] = createBucket();
            buckets[bucketIndex].add(createNode(key, value));
            nums += 1;
        } else if (node == null) {
            bucket.add(createNode(key, value));
            nums += 1;
        } else if (!node.value.equals(value)) {
            node.value = value;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Collection<Node> b: buckets) {
            for (Node n: b) {
                keySet.add(n.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = findBucket(key);
        Node removed = findNode(buckets[bucketIndex], key);
        if (removed != null) {
            buckets[bucketIndex].remove(removed);
            nums -= 1;
            return removed.value;
        } else {
            return null;
        }
    }

    @Override
    public V remove(K key, V value) {
        int bucketIndex = findBucket(key);
        Node removed = findNode(buckets[bucketIndex], key);
        if (removed == null || removed.value != value) {
            return null;
        } else {
            nums -= 1;
            return value;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIter();
    }

    private class MyHashMapIter implements Iterator<K> {
        Collection<Node> curr;
        int index;
        Iterator<Node> nodeIter;
        MyHashMapIter() {
            for (int i = 0; i < size; i++) {
                curr = buckets[i];
                index = i;
                if (curr != null) {
                    nodeIter = curr.iterator();
                    break;
                }
            }
        }
        @Override
        public boolean hasNext() {
            if (curr == null) {
                return false;
            }
            return nodeIter.hasNext();
        }

        @Override
        public K next() {
            if (curr == null || !nodeIter.hasNext()) {
                return null;
            }
            Node node = nodeIter.next();
            if (!nodeIter.hasNext()) {
                for (int i = index + 1; i < size; i++) {
                    curr = buckets[i];
                    index = i;
                    if (curr != null) {
                        nodeIter = curr.iterator();
                        break;
                    }
                }
            }
            return node.key;
        }
    }
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private int nums;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        size = 16;
        nums = 0;
        loadFactor = 0.75;
        buckets = createTable(size);
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        nums = 0;
        loadFactor = 0.75;
        buckets = createTable(size);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = initialSize;
        loadFactor = maxLoad;
        nums = 0;
        buckets = createTable(size);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }


}
