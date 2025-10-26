package org.example.part1.MyHashSet;

public interface MySet<V> {

    boolean add(V v);

    boolean remove(V o);

    int size();

    interface Entry<T> {
        T getValue();
    }

}
