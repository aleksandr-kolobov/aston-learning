package main.configure.analyzer;

import main.exception.BackgroundTaskPropertyException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import java.text.MessageFormat;

public class BackgroundTaskPropertyFailureAnalyzer extends AbstractFailureAnalyzer<BackgroundTaskPropertyException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, BackgroundTaskPropertyException cause) {
        return new FailureAnalysis(MessageFormat.format("Exception when try set property: {}",
               cause.getMessage()), "Set app properties ", cause);
    }
}
