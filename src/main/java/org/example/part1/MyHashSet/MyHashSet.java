package org.example.part1.MyHashSet;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashSet<V> implements MySet<V>, Iterable<V> {

    static class MyNode<V> implements MySet.Entry<V> {

        private final int hash;
        private final V value;
        private MyNode<V> next;

        MyNode(V value, int hash) {
            this.value = value;
            this.hash = hash;
        }

        public V getValue() {
            return this.value;
        }

        public final String toString() { return "Value = " + value; }

    }

    /* ---------------- Static utilities -------------- */

    static int hash(Object value) {
        int h;
        return (value == null) ? 0 : (h = value.hashCode()) ^ (h >>> 16);
    }

    /* ---------------- Fields -------------- */

    private final int DEFAULT_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;

    private MyNode<V>[] table;
    private int size;
    float loadFactor;
    int threshold;

    /* ---------------- Public operations -------------- */

    public MyHashSet() {
        this.loadFactor = LOAD_FACTOR;
        this.threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    public boolean add(V v) {
        return addVal(hash(v), v) == null;
    }

    public int size() {
        return size;
    }

    final V addVal(int hash, V v) {
        MyNode<V>[] nodes;
        MyNode<V> node;
        int s, index;

        if ((nodes = table) == null || (s = nodes.length) == 0 ) {
            s = (nodes = resize()).length;
        }

        if ((node = nodes[index = (s - 1) & hash]) == null) {
            nodes[index] = newNode(v, hash);
        } else {
            MyNode<V> currentNode = node;
            while (true) {
                if (currentNode.hash == hash &&
                        (Objects.equals(v, currentNode.value))) {
                    return currentNode.value;
                }
                if (currentNode.next == null) break;
                currentNode = currentNode.next;
            }
            currentNode.next = newNode(v, hash);
        }

        if(++size > threshold) {
            resize();
        }

        return null;
    }

    private MyNode<V> newNode(V v, int hash) {
        return new MyNode<>(v, hash);
    }

    final MyNode<V>[] resize() {

        MyNode<V>[] oldTable = table;
        int oldCapacity = table == null ? 0 : table.length;
        int newCapacity;

        if (oldCapacity > 0) {
            newCapacity = oldCapacity * 2;
            threshold = (int) (newCapacity * loadFactor);
        } else {
            newCapacity = DEFAULT_CAPACITY;
        }

        @SuppressWarnings({"unchecked"})
        MyNode<V>[] newTab = (MyNode<V>[])new MyNode[newCapacity];
        table = newTab;

        if(oldTable != null) {
            for (MyNode<V> myNode : oldTable) {
                if (myNode != null) {
                    table[(table.length - 1) & myNode.hash] = myNode;
                }
            }
        }
        return newTab;
    }

    public boolean remove(V v) {
        return removeNode(hash(v), v) != null;
    }

    private MyNode<V> removeNode(int hash, V value) {
        MyNode<V>[] tab = table;
        if (tab == null) return null;

        int index = (tab.length - 1) & hash;
        MyNode<V> node = tab[index];
        MyNode<V> prev = null;

        while (node != null) {
            if (node.hash == hash &&
                    (Objects.equals(value, node.value))) {

                if (prev == null) {
                    tab[index] = node.next;
                } else {
                    prev.next = node.next;
                }

                size--;
                return node;
            }
            prev = node;
            node = node.next;
        }

        return null;
    }


    /* ---------------- Iterator -------------- */

    public Iterator<V> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<V> {

        private int bucketIndex = 0;
        private MyNode<V> next = null;

        public MyIterator() {
            findNext();
        }

        public boolean hasNext() {
            return next != null;
        }

        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            MyNode<V> current = next;
            findNext();

            return current.value;
        }

        private void findNext() {
            if (next != null && next.next != null) {
                next = next.next;
                return;
            }

            while (bucketIndex < table.length) {
                if (table[bucketIndex] != null) {
                    next = table[bucketIndex++];
                    return;
                }
                bucketIndex++;
            }

            next = null;
        }
    }
}
