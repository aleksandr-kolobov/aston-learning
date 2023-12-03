package main.configure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ConfigurationProperties(prefix = "background-executor")
public class BackgroundTaskProperties {

    private Boolean enabled;

    private String defaultExecutor;

    private int tasksSize;

    @NestedConfigurationProperty
    private CronExecutorProperties cron;

    @NestedConfigurationProperty
    private TimeExecutorProperties time;

}
