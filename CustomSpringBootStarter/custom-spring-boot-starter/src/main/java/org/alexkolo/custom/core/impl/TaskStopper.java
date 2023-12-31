package org.alexkolo.custom.core.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@RequiredArgsConstructor
public class TaskStopper {

    private final Map<String, ScheduledFuture<?>> tasks;

    public void stop(String key) {
        if (tasks.containsKey(key)) {
            var currentTask = tasks.get(key);
            currentTask.cancel(true);
            tasks.remove(key);

            log.debug("Task with key {} stopped", key);
        }
    }

    void registryTask(String id, ScheduledFuture<?> future) {
        tasks.put(id, future);
    }

}
