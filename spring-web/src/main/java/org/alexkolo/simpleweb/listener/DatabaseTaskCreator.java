package org.alexkolo.simpleweb.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alexkolo.simpleweb.model.Task;
import org.alexkolo.simpleweb.service.TaskService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseTaskCreator {

    private final TaskService taskServiceImpl;

    @EventListener(ApplicationStartedEvent.class)
    public void createTaskData() {
        log.debug("Call createTaskData in DatabaseTaskCreator");

        List<Task> tasks = new ArrayList<>();
        long firstId = System.currentTimeMillis();
        Random random = new Random();

        for (int value = 1; value <= 10; value++) {
            Task task = new Task();
            task.setId(firstId + value);
            task.setTitle("Task" + value);
            task.setDescription("Description of Task" + value);
            task.setPriority(random.nextInt(10) + 1);
            tasks.add(task);
        }
        taskServiceImpl.butchInsert(tasks);
    }

}
