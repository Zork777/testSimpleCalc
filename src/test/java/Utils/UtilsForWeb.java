package Utils;

import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static junit.framework.TestCase.fail;

public class UtilsForWeb {

    public void enterNumber(String number){
        for (String s : number.split("")) {
            pushButton(s);
        }
    }
    public Double calculateTest(Double number1, Double number2, String function) {

        try {
            switch (function) {
                case "+":
                    return number1 + number2;
                case "-":
                    return number1 - number2;
                case "*":
                    return number1 * number2;
                case "/":
                    return number1 / number2;
                default:
                    return Double.POSITIVE_INFINITY;
            }
        } catch (ArithmeticException e) {
            return Double.POSITIVE_INFINITY;
        }
    }

    public void pushButton(String nameButton){
        if (DataFlow.numbers.get(nameButton) != null) {
            executeJavaScript(
                    DataFlow.numbers.get(nameButton) + ".click();"
            );
        } else if (DataFlow.functions.get(nameButton) != null) {
            executeJavaScript(
                    DataFlow.functions.get(nameButton) + ".click();"
            );
        } else {
            executeJavaScript(
                    "document.getElementById(\"calculated-display\").value = \""+ nameButton+"\";"
            );
        }
    }
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
