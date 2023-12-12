package org.example;

import org.alexkolo.custom.core.BackgroundTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@ComponentScan("org.alexkolo.custom")
@SpringBootApplication
public class Main {

    @Autowired
    BackgroundTaskExecutor backgroundTaskExecutor;

    @EventListener(ApplicationReadyEvent.class)
    public void onReadyEvent() {
        Thread thread = new Thread(() -> {
            while (true) {
                String id = UUID.randomUUID().toString();
                backgroundTaskExecutor.schedule(id, () -> System.out.println("task for " + id));
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}