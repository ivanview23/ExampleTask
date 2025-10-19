package org.example.part1.MyArrayList;

import java.util.List;

public interface MyList<V> {

    void add(V v);
    void add(int index, V v);
    V get(int index);
    boolean remove(Object o);
    void addAll(MyArrayList<V> other);


}
