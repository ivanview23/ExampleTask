package org.example.part2;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;

public class Book {

    private String name;
    private int size;
    private Year releaseYears;

    public Book(String name, int size, Year releaseYears) {
        this.name = name;
        this.size = size;
        this.releaseYears = releaseYears;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Year getReleaseYears() {
        return releaseYears;
    }

    @Override
    public String toString() {
        return "Book {" +
                '\'' + name + '\'' +
                ", " + size + " страниц, " +
                 releaseYears + " года" +
                '}';
    }
}
