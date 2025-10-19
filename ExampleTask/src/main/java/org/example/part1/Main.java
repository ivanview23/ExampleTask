package org.example.part1;

import org.example.part1.MyArrayList.MyArrayList;
import org.example.part1.MyHashSet.MyHashSet;

public class Main {



    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        System.out.println("=== ТЕСТ ADD ===");
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");
        System.out.println("После добавления: " + list);
        System.out.println("Размер: " + list.size());

        System.out.println("\n=== ТЕСТ GET ===");
        System.out.println("get(0): " + list.get(0));
        System.out.println("get(1): " + list.get(1));

        System.out.println("\n=== ТЕСТ REMOVE by index ===");
        String removed = list.remove(1);
        System.out.println("Удален: " + removed);
        System.out.println("После remove(1): " + list);

        System.out.println("\n=== ТЕСТ REMOVE by object ===");
        boolean removedObj = list.remove("Apple");
        System.out.println("Удален 'Apple': " + removedObj);
        System.out.println("После remove('Apple'): " + list);

        System.out.println("\n=== ТЕСТ ADDALL ===");
        MyArrayList<String> anotherList = new MyArrayList<>();
        anotherList.add("Grape");
        anotherList.add("Kiwi");

        list.addAll(anotherList);
        System.out.println("После addAll: " + list);

        System.out.println("\n=== ТЕСТ ИТЕРАТОРА ===");
        for (String fruit : list) {
            System.out.println("Элемент: " + fruit);
        }
    }
//    public static void main(String[] args) {
//
//        MyHashSet<String> set = new MyHashSet<>();
//
//        System.out.println("=== ТЕСТ ADD ===");
//        System.out.println("Добавляем 'apple': " + set.add("apple"));
//        System.out.println("Добавляем 'banana': " + set.add("banana"));
//        System.out.println("Добавляем 'apple' (дубликат): " + set.add("apple"));
//        System.out.println("Размер: " + set.size());
//
//        System.out.println("\n=== ТЕСТ REMOVE ===");
//        System.out.println("Удаляем 'apple': " + set.remove("apple"));
//        System.out.println("Удаляем 'orange' (не существует): " + set.remove("orange"));
//        System.out.println("Размер после удаления: " + set.size());
//
//        System.out.println("\n=== ПРОВЕРКА ИТЕРАТОРОМ ===");
//        for (String fruit : set) {
//            System.out.println("Элемент: " + fruit);
//        }
//    }
}