package org.example.part3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WriteOrReadDataOfFileImpl implements WriteOrReadDataOfFile {



//    private File currentFile;
//    private List<String> fileContent;
//
//    public WriteOrReadDataOfFileImpl() {
//        this.fileContent = new ArrayList<>();
//    }
//
//    @Override
//    public void loadFile(String path) {
//
//    }
//
//    @Override
//    public void writeOnFile() {
//
//    }
//
//    @Override
//    public void readFromFile() {
//
//    }
//    package org.example.part3;


        private String currentFilePath;
        private List<String> fileContent;
        private boolean fileLoaded;

        public WriteOrReadDataOfFileImpl() {
            this.fileContent = new ArrayList<>();
            this.fileLoaded = false;
        }

        @Override
        public void loadFile(String path) {
            try {
                // Валидация пути
                if (path == null || path.trim().isEmpty()) {
                    throw new WriteOrReadException("Путь к файлу не может быть пустым");
                }

                Path filePath = Paths.get(path);

                // Проверка существования файла
                if (!Files.exists(filePath)) {
                    throw new WriteOrReadException("Файл не существует: " + path);
                }

                // Проверка, что это файл, а не директория
                if (!Files.isRegularFile(filePath)) {
                    throw new WriteOrReadException("Указанный путь не является файлом: " + path);
                }

                // Чтение файла
                List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                this.fileContent.clear();
                this.fileContent.addAll(lines);
                this.currentFilePath = path;
                this.fileLoaded = true;

                System.out.println("Файл успешно загружен: " + path);
                System.out.println("Прочитано строк: " + lines.size());

            } catch (IOException e) {
                throw new WriteOrReadException("Ошибка при загрузке файла: " + path, e);
            }
        }

        @Override
        public void writeOnFile() {
            writeOnFile(this.currentFilePath, false);
        }

        // Перегруженный метод для записи в указанный файл
        public void writeOnFile(String path, boolean append) {
            try {
                if (path == null || path.trim().isEmpty()) {
                    throw new WriteOrReadException("Путь к файлу не может быть пустым");
                }

                if (fileContent.isEmpty()) {
                    throw new WriteOrReadException("Нет данных для записи");
                }

                // Создание директорий, если нужно
                createParentDirectories(path);

                try (BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(path, append), StandardCharsets.UTF_8))) {

                    for (String line : fileContent) {
                        writer.write(line);
                        writer.newLine();
                    }

                    writer.flush();
                }

                System.out.println("Данные успешно записаны в файл: " + path);
                System.out.println("Записано строк: " + fileContent.size());

            } catch (IOException e) {
                throw new WriteOrReadException("Ошибка при записи в файл: " + path, e);
            }
        }

        @Override
        public void readFromFile() {
            if (!fileLoaded) {
                throw new WriteOrReadException("Файл не загружен. Сначала вызовите loadFile()");
            }

            if (fileContent.isEmpty()) {
                System.out.println("Файл пуст");
                return;
            }

            System.out.println("=== СОДЕРЖИМОЕ ФАЙЛА: " + currentFilePath + " ===");
            for (int i = 0; i < fileContent.size(); i++) {
                System.out.printf("%3d: %s%n", i + 1, fileContent.get(i));
            }
            System.out.println("=== КОНЕЦ ФАЙЛА ===");
        }

        // Вспомогательные методы

        private void createParentDirectories(String filePath) {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                try {
                    Files.createDirectories(parentDir);
                    System.out.println("Создана директория: " + parentDir);
                } catch (IOException e) {
                    throw new WriteOrReadException("Не удалось создать директорию: " + parentDir, e);
                }
            }
        }

        // Методы для работы с данными

        public void addLine(String line) {
            if (line == null) {
                throw new WriteOrReadException("Добавляемая строка не может быть null");
            }
            fileContent.add(line);
            System.out.println("Добавлена строка: " + line);
        }

        public void addLines(List<String> lines) {
            if (lines == null) {
                throw new WriteOrReadException("Список строк не может быть null");
            }
            fileContent.addAll(lines);
            System.out.println("Добавлено строк: " + lines.size());
        }

        public void clearContent() {
            fileContent.clear();
            System.out.println("Содержимое очищено");
        }

        public void removeLine(int index) {
            if (index < 0 || index >= fileContent.size()) {
                throw new WriteOrReadException("Неверный индекс строки: " + index);
            }
            String removedLine = fileContent.remove(index);
            System.out.println("Удалена строка [" + index + "]: " + removedLine);
        }

        // Геттеры

        public String getCurrentFilePath() {
            return currentFilePath;
        }

        public List<String> getFileContent() {
            return new ArrayList<>(fileContent); // Возвращаем копию для безопасности
        }

        public int getLineCount() {
            return fileContent.size();
        }

        public boolean isFileLoaded() {
            return fileLoaded;
        }

        /* ---------------- Experimental method -------------- */

        public void writeExp() {
            try(FileOutputStream fileOutputStream = new FileOutputStream("test.txt")) {
                String greeting = "helloba";

                fileOutputStream.write(greeting.getBytes());

            } catch (WriteOrReadException e) {
                System.out.println("Ошибка создания ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
//}
