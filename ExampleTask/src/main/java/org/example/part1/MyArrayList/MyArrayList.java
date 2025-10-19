package org.example.part1.MyArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<V> implements MyList<V>, Iterable<V> {

    /* ---------------- Fields -------------- */

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    /* ---------------- Constructors -------------- */

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.array = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /* ---------------- Public operations -------------- */

    public void add(V v) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = v;
    }

    public void add(int index, V v) {
        checkIndex(index);

        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }

        if (size - index > 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }

        array[index] = v;
        size++;
    }

    @SuppressWarnings("unchecked")
    public V get(int index) {
        checkIndex(index);
        return (V) array[index];
    }

    @SuppressWarnings("unchecked")
    public V remove(int index) {
        checkIndex(index);

        V oldValue = (V) array[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public void addAll(MyArrayList<V> other) {
        for (int i = 0; i < other.size; i++) {
            this.add((V) other.array[i]); // используем существующий add()
        }
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    /* ---------------- Private methods -------------- */

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /* ---------------- Iterable implementation -------------- */

    public Iterator<V> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<V> {
        int cursor = 0;

        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        public V next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return (V) array[cursor++];
        }
    }
}
