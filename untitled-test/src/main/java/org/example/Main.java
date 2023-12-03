package org.example;

import main.core.BackgroundTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@ComponentScan("main")
@SpringBootApplication
public class Main {

    @Autowired
    BackgroundTaskExecutor backgroundTaskExecutor;

    @EventListener(ApplicationReadyEvent.class)
    public void onEvent() {
        Thread thread = new Thread(() -> {
            String id = UUID.randomUUID().toString();
            backgroundTaskExecutor.schedule(id, () -> System.out.println("task for " + id));
        });
        thread.start();
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}