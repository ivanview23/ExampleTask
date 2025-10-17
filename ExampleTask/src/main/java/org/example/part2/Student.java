package org.example.part2;

import java.time.OffsetDateTime;
import java.time.Year;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Student {

    private String name;
    private List<Book> book;

    public Student(String name, List<Book> book) {
        this.name = name;
        this.book = book;
    }

    @Override
    public String toString() {
        return "Student {" +
                '\'' + name + '\'' +
                '}';
    }

    public List<Book> getBook() {
        return book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public static void main(String[] args) {
        List<Student> students = createTestStudents();

        students.stream()
                .peek(System.out::println)
                .map(Student::getBook)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Book::getSize))
                .distinct()
                .filter(book -> book.getReleaseYears().isAfter(Year.of(2000)))
                .limit(3)
                .findAny()
                .ifPresentOrElse(book -> System.out.println(book.getReleaseYears()),
                        () -> System.out.println("Такой книги нет"));
    }

    public static List<Student> createTestStudents() {
        Book book1 = new Book("Java language reference", 525, Year.of(1997));
        Book book2 = new Book("Java. Полное руководство", 1488, Year.of(2018));
        Book book3 = new Book("Java. Библиотека профессионала", 864, Year.of(2020));
        Book book4 = new Book("Java. Эффективное программирование", 378, Year.of(2019));
        Book book5 = new Book("Spring в действии", 544, Year.of(2022));
        Book book6 = new Book("Java Concurrency на практике", 498, Year.of(2020));
        Book book7 = new Book("Алгоритмы на Java", 848, Year.of(2019));
        Book book8 = new Book("Чистый код", 464, Year.of(2018));
        Book book9 = new Book("Java and object orientation", 473, Year.of(1998));
        Book book10 = new Book("Taming Java threads", 300, Year.of(2000));
        Book book11 = new Book("Teach yourself Java in 21 days", 325, Year.of(1996));
        Book book12 = new Book("JDBC database access with Java", 458, Year.of(1997));

        return List.of(
                new Student("Зотов Руслан", List.of(book2, book6, book7)),
                new Student("Михайлов Михаил", List.of(book12, book3, book1, book5)),
                new Student("Титов Сергей", List.of(book5, book1, book4)),
                new Student("Пушкин Анатолий", List.of(book4, book8, book7, book9, book12)),
                new Student("Димидов Олег", List.of(book3, book6, book11, book2)),
                new Student("Уолберг Билл", List.of(book7, book5, book1)),
                new Student("Немов Евгений", List.of(book2, book9, book12, book8)),
                new Student("Грибоедов Василий", List.of(book5, book10, book2, book4)),
                new Student("Синицин Роман", List.of(book11, book6, book10, book7)),
                new Student("Летов Кирилл", List.of(book1, book9, book3, book6)),
                new Student("Огурченко Людмил", List.of(book7, book5, book4, book1)),
                new Student("Шматко Николай", List.of(book10, book5, book8, book3, book11))
                );
    }
}
