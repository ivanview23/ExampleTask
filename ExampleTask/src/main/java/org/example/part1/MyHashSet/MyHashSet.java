package org.example.part1.MyHashSet;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashSet<V> implements MySet<V>, Iterable<V> {

    private final int DEFAULT_CAPACITY = 1 << 4;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    static class MyNode<V> implements MySet.Entry<V> {

        private int hash;
        private V value;
        private MyNode<V> next;

        MyNode(V value) {
            this.value = value;
        }

        public V getValue() {
            return this.value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final String toString() { return "Value = " + value; }

    }

    /* ---------------- Static utilities -------------- */

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /* ---------------- Fields -------------- */

    private MyNode<V>[] table;
    private int size;
    private int modCount;
//    int threshold;
    private final float loadFactor;

    /* ---------------- Public operations -------------- */

    public MyHashSet() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public boolean add(V v) {
        return addVal(hash(v), v) == null;
    }

    final V addVal(int hash, V v) {
        MyNode<V>[] nodes;
        MyNode<V> n;

        if ((nodes = table) == null ) {
            table = resize();
        }

        nodes[1] = newNode(v);


        return null;
    }

//Node<K,V> newNode(int hash, K key, V value, HashMap.Node<K,V> next) {
//    return new Node<>(hash, key, value, next);
//}


    private MyNode<V> newNode(V v) {
        return new MyNode<>(v);
    }

    final MyNode<V>[] resize() {
        int cap = DEFAULT_CAPACITY;
        MyNode<V>[] newTab = (MyNode<V>[])new MyNode[cap];
        table = newTab;
        return newTab;
    }

    /* ---------------- Iterators -------------- */



    public Iterator<V> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<V> {

        private int bucketIndex = 0;
        private MyNode<V> current = null;
        private MyNode<V> next = null;
//        private int expectedModCount = modCount; // для fail-fast поведения

        public MyIterator() {
            findNext();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public V next() {
//            if (expectedModCount != modCount) {
//                throw new ConcurrentModificationException();
//            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = next;
            findNext(); // ищем следующий элемент

            return current.value;
        }

        private void findNext() {
            // Если в текущей цепочке есть следующий элемент
            if (next != null && next.next != null) {
                next = next.next;
                return;
            }

            // Ищем следующий непустой бакет
            while (bucketIndex < table.length) {
                if (table[bucketIndex] != null) {
                    next = table[bucketIndex++];
                    return;
                }
                bucketIndex++;
            }

            // Больше элементов нет
            next = null;
        }

//        @Override
//        public void remove() {
//            if (current == null) {
//                throw new IllegalStateException();
//            }
////            if (expectedModCount != modCount) {
////                throw new ConcurrentModificationException();
////            }
//
//            // Удаляем current элемент из множества
//            MyHashSet.this.remove(current.value);
//            expectedModCount = modCount; // обновляем счетчик изменений
//            current = null;
//        }
    }
}
