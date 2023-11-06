package Test;
import NegativeTest.TestNegativeCsvSample;
import PositiveTest.TestClickButton;
import PositiveTest.TestFunctionToRandom;
import PositiveTest.TestPositiveCsvSample;
import Utils.DataFlow;
import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
//        TestForLog.class,
        TestPositiveCsvSample.class,
        TestNegativeCsvSample.class,
//        TestClickButton.class,
//        TestFunctionToRandom.class
})

public class AllTest {

    static DataFlow dataFlow = new DataFlow();

    @BeforeClass
    public static void setUp() {
        Configuration.browser = "safari";
        Configuration.baseUrl = "https://testpages.eviltester.com";
    }
}
