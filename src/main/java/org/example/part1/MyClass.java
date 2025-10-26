package org.example.part1;

import java.util.Objects;

public class MyClass {

    public String data;

    public MyClass(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return Objects.equals(data, myClass.data);
    }

    @Override
    public int hashCode() {
        return data.length();
    }
}
