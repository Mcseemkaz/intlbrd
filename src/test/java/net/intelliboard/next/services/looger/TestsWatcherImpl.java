package net.intelliboard.next.services.looger;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.AbstractTest;
import net.intelliboard.next.services.ConsoleColors;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static io.qameta.allure.Allure.addAttachment;

public class TestsWatcherImpl implements TestWatcher {

    public static Map<String, TestResultStatus> testResultsStatus = new HashMap<>();
    Logger loggerWatcher = LoggerFactory.getLogger(AbstractTest.class);

    public enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        loggerWatcher.info(String.format("Test %sPASSED%s: %s", ConsoleColors.GREEN, ConsoleColors.RESET, context.getDisplayName()));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.SUCCESSFUL);
        shutDownSystem();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        loggerWatcher.error(String.format("Test %sFAILED%s: %s \n %s", ConsoleColors.RED_BACKGROUND, ConsoleColors.RESET,
                context.getDisplayName(), cause));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.FAILED);
        try {
            File screenshotAs = Selenide.screenshot(OutputType.FILE);
            addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        shutDownSystem();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        loggerWatcher.warn(String.format("Test %sABORTED%s: %s \n %s", ConsoleColors.YELLOW, ConsoleColors.RESET,
                context.getDisplayName(), cause));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.ABORTED);
        shutDownSystem();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        loggerWatcher.warn(String.format("Test %sDISABLED%s: %s \n %s", ConsoleColors.RED_BACKGROUND, ConsoleColors.RESET, context.getDisplayName(),
                reason.orElse("No reason")));
        testResultsStatus.put(context.getDisplayName(), TestResultStatus.DISABLED);
        shutDownSystem();
    }

    private void shutDownSystem() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.driver().close();
    }
}