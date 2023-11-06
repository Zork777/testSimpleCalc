package PositiveTest;

import Utils.DataFlow;
import Utils.LogMy;
import org.junit.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TestClickButton extends LogMy{

    @Test
    public void testClickButton() {
        //open url

        open("/styled/apps/calculator.html");
        String displayValue;
        String testValue = "";
            for (String nameButton : DataFlow.numbers.keySet()) {
                executeJavaScript(
                        DataFlow.numbers.get(nameButton)+ ".click();"
                );
                displayValue = $(By.id("calculated-display")).val();
                testValue += nameButton;
                Assert.assertEquals(testValue, displayValue);
            }
    }

}
