package main.core;

public interface BackgroundTaskExecutor {

    void schedule(String taskId, Runnable task);

}
