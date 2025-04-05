package com.andrew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FileRunner {
    public static void main(String[] args) throws IOException {
        Path inputFile = Path.of("resources", "number.txt");
        Path outputFile = Path.of("resources", "sortedNumbers.txt");
        makeFile(inputFile);
        createRandomNumFiles(inputFile);

        List<String> sorted = Files.lines(inputFile)
                .mapToInt(num -> Integer.parseInt(num))
                .sorted()
                .mapToObj(num -> String.valueOf(num))
                .toList();

        Files.write(outputFile, sorted);
    }

    private static void makeFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRandomNumFiles(Path path) throws IOException {
        Random random = new Random();
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            int randomNum = random.nextInt(101);
            string.append(randomNum).append(System.lineSeparator());
        }
        Files.writeString(path, string);
    }
}
