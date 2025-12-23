package com.luminalib;

import com.luminalib.model.AbstractResource;
import com.luminalib.model.Book;
import com.luminalib.model.DigitalMedia;
import com.luminalib.model.ResourceStatus;
import com.luminalib.service.InventoryManager;
import com.luminalib.service.StorageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LuminaApp {
    public static void main(String[] args) throws InterruptedException {
        InventoryManager<AbstractResource> manager = new InventoryManager<>();

        Book cleanCode = new Book("Clean Code", "Robert Martin", "978-0132350884");
        DigitalMedia javaVideo = new DigitalMedia("Java Mastery", "MP4");

        manager.addResource(cleanCode);
        manager.addResource(javaVideo);

        String searchId = cleanCode.getId();
        Optional<AbstractResource> found = manager.findById(searchId);
        found.ifPresentOrElse(
                resource -> System.out.println("Found Resource: " + resource.getTitle() + " | Loan period: " + resource.getLoanPeriodInDays()),
                ()-> System.out.println("Resources not found!")
        );

        System.out.println("Current Inventory Status");
        manager.getAllResources().forEach(r-> System.out.println("- " + r.getTitle() + " [" + r.getStatus() + "]"));

        boolean success = manager.reserveResource(cleanCode.getId());
        System.out.println("Reservation 1 successful? " + success);

        boolean failed = manager.reserveResource(cleanCode.getId());
        System.out.println("Reservation 2 successful? " + failed);

        // use streams to find all reserved items
        List<AbstractResource> reservedItems = manager.findByStatus(ResourceStatus.RESERVED);
        System.out.println("Reserved items count: " + reservedItems.size());

        List<AbstractResource> result = manager.searchByKeyword(javaVideo.getTitle());
        System.out.println("Searched using keyword: " + result.size());

        Book targetBook = new Book("Concurrency in Practice", "Brain Goetz", "1111");
        manager.addResource(targetBook);

        // create a thread pool with 10 sec.
        ExecutorService executer = Executors.newFixedThreadPool(10);

        System.out.println("Stress Test");

        // Simulate 10 simultaneous reservation attempts.

        for (int i = 0; i < 10; i++) {
            executer.submit(() -> {
                boolean response = manager.reserveResource(targetBook.getId());
                System.out.println(Thread.currentThread().getName() + " reservation: " + response);
            });
        }
        executer.shutdown();
        executer.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Final Book Status: " + targetBook.getStatus());

        StorageService storage = new StorageService();

        try {
            List<Book> bookList = manager.getAllResources().stream()
                    .filter(r -> r instanceof Book)
                    .map(r -> (Book) r)
                    .collect(Collectors.toList());
            storage.saveToFile(bookList);
            storage.loadFromFile();
        } catch(IOException e) {
            System.err.println("Could not manage file: " + e.getMessage());
        }

    }
}