package org.example.part3;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        WriteOrReadDataOfFileImpl writeOrReadDataOfFile = new WriteOrReadDataOfFileImpl();
//
//        Path filePath = Paths.get("test.txt");
//        try {
//            // Files.createFile() создаст файл, если его нет.
//            // Если файл уже существует, будет выброшено исключение FileAlreadyExistsException.
//            Files.createFile(filePath);
//            System.out.println("Файл создан: " + filePath.toAbsolutePath());
//        } catch (FileAlreadyExistsException e) {
//            System.out.println("Файл уже существует.");
//        } catch (IOException e) {
//            System.out.println("Произошла ошибка при создании файла: " + e.getMessage());
//        }
////        Scanner scanner = new Scanner(System.in);
////        while(true) {
////
////            scanner.next();
////
////        }
//
//        System.out.println("hello");
        WriteOrReadDataOfFile fileOperator = new WriteOrReadDataOfFileImpl();

        fileOperator.writeExp();

//        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С ФАЙЛАМИ ===\n");
//
//        try {
//            // 1. Создание и запись тестовых данных
//            System.out.println("1. ПОДГОТОВКА ДАННЫХ:");
//            ((WriteOrReadDataOfFileImpl) fileOperator).addLine("Первая строка");
//            ((WriteOrReadDataOfFileImpl) fileOperator).addLine("Вторая строка");
//            ((WriteOrReadDataOfFileImpl) fileOperator).addLines(Arrays.asList(
//                    "Третья строка",
//                    "Четвертая строка",
//                    "Пятая строка"
//            ));
//
//            // 2. Запись в файл
//            System.out.println("\n2. ЗАПИСЬ В ФАЙЛ:");
//            fileOperator.writeOnFile(); // Использует текущий путь (будет ошибка)
//
//        } catch (WriteOrReadException e) {
//            System.out.println("Ожидаемая ошибка: " + e.getMessage());
//        }
//
//        try {
//            // 3. Запись в конкретный файл
//            System.out.println("\n3. ЗАПИСЬ В КОНКРЕТНЫЙ ФАЙЛ:");
//            ((WriteOrReadDataOfFileImpl) fileOperator).writeOnFile("test_data/example.txt", false);
//
//            // 4. Чтение файла
//            System.out.println("\n4. ЧТЕНИЕ ФАЙЛА:");
//            fileOperator.loadFile("test_data/example.txt");
//            fileOperator.readFromFile();
//
//            // 5. Добавление данных и дозапись
//            System.out.println("\n5. ДОЗАПИСЬ ДАННЫХ:");
//            ((WriteOrReadDataOfFileImpl) fileOperator).addLine("--- Новая строка после загрузки ---");
//            ((WriteOrReadDataOfFileImpl) fileOperator).writeOnFile("test_data/example.txt", true);
//
//            // 6. Чтение обновленного файла
//            System.out.println("\n6. ЧТЕНИЕ ОБНОВЛЕННОГО ФАЙЛА:");
//            fileOperator.loadFile("test_data/example.txt");
//            fileOperator.readFromFile();
//
//            // 7. Обработка ошибок
//            System.out.println("\n7. ТЕСТИРОВАНИЕ ОШИБОК:");
//
//            // Несуществующий файл
//            try {
//                fileOperator.loadFile("nonexistent_file.txt");
//            } catch (WriteOrReadException e) {
//                System.out.println("Поймано исключение: " + e.getMessage());
//            }
//
//            // Пустой путь
//            try {
//                fileOperator.loadFile("");
//            } catch (WriteOrReadException e) {
//                System.out.println("Поймано исключение: " + e.getMessage());
//            }
//
//        } catch (WriteOrReadException e) {
//            System.err.println("Критическая ошибка: " + e.getMessage());
//            if (e.getCause() != null) {
//                System.err.println("Причина: " + e.getCause().getMessage());
//            }
//        }
//
//        System.out.println("\n=== ПРОГРАММА ЗАВЕРШЕНА ===");
    }
}
