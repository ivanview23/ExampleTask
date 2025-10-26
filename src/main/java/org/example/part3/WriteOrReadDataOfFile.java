package org.example.part3;

import java.util.List;

public interface WriteOrReadDataOfFile {

    void writeOnFile(String path, String text);
    void appendToFile(String path, String text);
    String readFromFile(String path);
    List<String> readLinesFromFile(String path);
    String readLineFromFile(String path, int lineNumber);

}
