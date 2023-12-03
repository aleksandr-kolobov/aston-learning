package main.configure.analyzer;

import main.exception.BackgroundTaskPropertyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class PropertyGuardEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        boolean enabled = Boolean.parseBoolean(environment.getProperty("background-executor.enabled"));
        String defaultExecutor = environment.getProperty("background-executor.default-executor");
        String taskSize = environment.getProperty("background-executor.task-size");
        String timeValue = environment.getProperty("background-executor.time.in-seconds");
        String cronExpression = environment.getProperty("background-executor.cron.expression");

        if (enabled) {
            check(defaultExecutor, taskSize, timeValue, cronExpression);
        }
    }

    private void check(String defaultExecutor, String taskSize, String timeValue, String cronExpression) {
        boolean isInvalidType = (!StringUtils.hasText(defaultExecutor))
                || (!Objects.equals(defaultExecutor, "cron"))
                && (!Objects.equals(defaultExecutor, "time"));
        if (isInvalidType) {
            throw new BackgroundTaskPropertyException("Set cron or time");
        }

        if (Objects.equals(defaultExecutor, "cron") && (!StringUtils.hasText(cronExpression))) {
            throw new BackgroundTaskPropertyException("Set cron expression");
        }

        if (Objects.equals(defaultExecutor, "time") && (!StringUtils.hasText(timeValue))) {
            throw new BackgroundTaskPropertyException("Set value for time");
        }

        if ((!StringUtils.hasText(taskSize)) || (!taskSize.matches("-?\\d*"))
                || (Integer.parseInt(taskSize) <= 0)) {
            throw new BackgroundTaskPropertyException("Invalid task-size value");
        }
    }

}
