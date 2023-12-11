package org.alexkolo.custom.core;

public interface BackgroundTaskExecutor {

    void schedule(String taskId, Runnable task);

}
