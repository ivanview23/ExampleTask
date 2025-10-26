package org.example.part3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileManagerImpl implements FileManager {

    public FileManagerImpl() {}

    /* ---------------- Write files -------------- */

    public void writeOnFile(String path, String text) throws WriteOrReadException {
        validateInput(path, text);

        try {
            Path filePath = Paths.get(path);
            Path parentDir = filePath.getParent();

            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }

            Files.writeString(filePath, text, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new WriteOrReadException("Ошибка записи в файл '" + path + "': " + e.getMessage(), e);
        }
    }

    public void appendToFile(String path, String text) throws WriteOrReadException {
        validateInput(path, text);

        try {
            Files.writeString(
                    Paths.get(path),
                    text,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            throw new WriteOrReadException("Ошибка дописывания в файл '" + path + "': " + e.getMessage(), e);
        }
    }

    /* ---------------- Read files -------------- */

    public String readFromFile(String path) throws WriteOrReadException {
        validatePath(path);

        try {
            return Files.readString(Paths.get(path), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new WriteOrReadException("Ошибка чтения файла '" + path + "': " + e.getMessage(), e);
        }
    }

    public List<String> readLinesFromFile(String path) throws WriteOrReadException {
        validatePath(path);

        try {
            return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new WriteOrReadException("Ошибка чтения файла '" + path + "': " + e.getMessage(), e);
        }
    }

    public String readLineFromFile(String path, int lineNumber) throws WriteOrReadException {
        validatePath(path);

        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

            if (lineNumber < 1 || lineNumber > lines.size()) {
                throw new WriteOrReadException("Некорректный номер строки: " + lineNumber +
                        ". Файл содержит " + lines.size() + " строк.");
            }

            return lines.get(lineNumber - 1); // -1 потому что пользователи считают с 1

        } catch (IOException e) {
            throw new WriteOrReadException("Ошибка чтения файла '" + path + "': " + e.getMessage(), e);
        }
    }

    /* ---------------- Support -------------- */

    public boolean fileExists(String path) {
        if (isNotValidPath(path)) {
            return false;
        }
        return Files.exists(Paths.get(path));
    }

    public long getFileSize(String path) throws WriteOrReadException {
        validatePath(path);

        try {
            return Files.size(Paths.get(path));
        } catch (IOException e) {
            throw new WriteOrReadException("Ошибка получения размера файла '" + path + "': " + e.getMessage(), e);
        }
    }

    /* ---------------- Validation -------------- */

    private void validateInput(String path, String text) throws WriteOrReadException {
        if (isNotValidPath(path)) {
            throw new WriteOrReadException("Некорректный путь к файлу: " + path);
        }

        if (text == null) {
            throw new WriteOrReadException("Текст не может быть null");
        }
    }

    private void validatePath(String path) throws WriteOrReadException {
        if (isNotValidPath(path)) {
            throw new WriteOrReadException("Некорректный путь к файлу: " + path);
        }

        if (!Files.exists(Paths.get(path))) {
            throw new WriteOrReadException("Файл не существует: " + path);
        }

        if (!Files.isReadable(Paths.get(path))) {
            throw new WriteOrReadException("Нет прав на чтение файла: " + path);
        }
    }

    private boolean isNotValidPath(String path) {
        if (path == null || path.trim().isEmpty()) {
            return true;
        }

        if (!path.contains(".") || path.endsWith(".") || path.endsWith("/") || path.endsWith("\\")) {
            return true;
        }

        return path.contains("..") || path.contains("//") || path.contains("\\\\");
    }

    /* ---------------- Test -------------- */

    public static void main(String[] args) {
        FileManagerImpl fileManager = new FileManagerImpl();

        try {
            // Тест записи
            fileManager.writeOnFile("data/notes.txt", "Привет, мир!\nЭто тестовый файл.");
            System.out.println("Файл успешно записан!");

            // Тест дописывания
            fileManager.appendToFile("data/notes.txt", "\nДописанная строка");
            System.out.println("Текст дописан!");

            // Тест чтения всего файла
            String content = fileManager.readFromFile("data/notes.txt");
            System.out.println("\nСодержимое файла:");
            System.out.println(content);

            // Тест чтения построчно
            List<String> lines = fileManager.readLinesFromFile("data/notes.txt");
            System.out.println("\nСтроки файла:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println((i + 1) + ": " + lines.get(i));
            }

            // Тест чтения конкретной строки
            String thirdLine = fileManager.readLineFromFile("data/notes.txt", 3);
            System.out.println("\nТретья строка: " + thirdLine);

            // Тест служебных методов
            System.out.println("\nФайл существует: " + fileManager.fileExists("data/notes.txt"));
            System.out.println("Размер файла: " + fileManager.getFileSize("data/notes.txt") + " байт");

        } catch (WriteOrReadException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        // Тестирование ошибок
        try {
            fileManager.readFromFile("nonexistent.txt");
        } catch (WriteOrReadException e) {
            System.out.println("\nОжидаемая ошибка: " + e.getMessage());
        }

        try {
            fileManager.readLineFromFile("data/notes.txt", 10); // Несуществующая строка
        } catch (WriteOrReadException e) {
            System.out.println("Ожидаемая ошибка: " + e.getMessage());
        }
    }
}