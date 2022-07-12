package net.intelliboard.next.services.looger;

import net.intelliboard.next.services.ConsoleColors;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.*;


public class TestsWatcherImpl implements TestWatcher {

    public static Map<String, TestResultStatus> testResultsStatus = new HashMap<>();

    public enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println(String.format("Test %sPASSED%s: %s", ConsoleColors.GREEN, ConsoleColors.RESET, context.getDisplayName()));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println(String.format("Test %sFAILED%s: %s \n %s", ConsoleColors.RED_BACKGROUND, ConsoleColors.RESET,
                context.getDisplayName(), cause));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.FAILED);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println(String.format("Test %sABORTED%s: %s \n %s", ConsoleColors.YELLOW, ConsoleColors.RESET,
                context.getDisplayName(), cause));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.ABORTED);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println(String.format("Test %sFAILED%s: %s \n %s", ConsoleColors.RED_BACKGROUND, ConsoleColors.RESET, context.getDisplayName(),
                reason.orElse("No reason")));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.DISABLED);
    }
}
