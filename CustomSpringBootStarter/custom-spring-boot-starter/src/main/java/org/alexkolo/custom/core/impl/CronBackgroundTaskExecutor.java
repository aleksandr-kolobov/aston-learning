package org.alexkolo.custom.core.impl;

import org.alexkolo.custom.core.BackgroundTaskExecutor;
import org.alexkolo.custom.configure.properties.BackgroundTaskProperties;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnExpression("'${background-executor.default-executor}'.equals('cron') and ${background-executor.enabled:true}")
public class CronBackgroundTaskExecutor implements BackgroundTaskExecutor {

    private final BackgroundTaskProperties backgroundTaskProperties;

    private final ConcurrentTaskScheduler concurrentTaskScheduler;

    private final TaskStopper taskStopper;

    @Override
    public void schedule(String taskId, Runnable task) {

        log.debug("CronBackgroundTaskExecutor: task with id {} preparing for schedule", taskId);

        var future = concurrentTaskScheduler.schedule(task,
                new CronTrigger(backgroundTaskProperties.getCron().getExpression()));

        taskStopper.registryTask(taskId, future);
    }
}
