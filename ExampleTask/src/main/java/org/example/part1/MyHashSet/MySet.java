package org.example.part1.MyHashSet;

public interface MySet<V> {

    boolean add(V v);

    interface Entry<T> {

        T getValue();
    }

//    public int size();


//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return false;
//    }
//
//    @Override
//    public Iterator iterator() {
//        return null;
//    }
//
//    @Override
//    public Object[] toArray() {
//        return new Object[0];
//    }
//

//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public boolean removeAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection c) {
//        return false;
//    }
//
//    @Override
//    public Object[] toArray(Object[] a) {
//        return new Object[0];
//    }
}
