package com.luminalib.service;

import com.luminalib.model.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class StorageService {
    private static final String FILE_PATH = "library_data.txt";

    // Save the entire inventory to a file.
    // Checked Exception.
    public void saveToFile(List<Book> books) throws IOException {
        List<String> lines = books.stream()
                .map(b -> String.join(",", b.getId(), b.getTitle(), b.getAuthor(), b.getStatus().toString()))
                .collect(Collectors.toList());
        Files.write(Paths.get(FILE_PATH), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Data saved to " + FILE_PATH);
    }

    public void loadFromFile() throws IOException {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;

        List<String> lines = Files.readAllLines(path);
        System.out.println(" --- Loaded from File ----");
        lines.forEach(System.out::println);
    }
}