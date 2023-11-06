package Utils;

import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.AllTests;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMy {
    static public Logger logger = LoggerFactory.getLogger(AllTests.class);

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests();

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            logger.info("Test {} is running.", method.getName());
        }
        public void succeeded(FrameworkMethod method) {
            logger.info("Test {} succesfully run.", method.getName());
        }
        public void failed(Throwable e, FrameworkMethod method) {
            logger.error("Test {} failed with {} reason.",
                    method.getName(), e.getMessage());

        }
    };
}
