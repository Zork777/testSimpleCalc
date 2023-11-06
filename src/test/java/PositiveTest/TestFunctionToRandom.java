package PositiveTest;

import Utils.LogMy;
import Utils.ScreenShooter;
import Utils.UtilsForWeb;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.fail;


public class TestFunctionToRandom extends LogMy {

    @Test
    public void testFunctionRandom() {
        UtilsForWeb utilsForWeb = new UtilsForWeb();
        String numericalExample;

        //open url
        open("/styled/apps/calculator.html");
        List <String> arrayFunction = new ArrayList<String>(Arrays.asList("+", "-", "/", "*"));
        String function;
        Double resultCalc, resultWeb, number1, number2;


        final Integer N = 20;

        Random random = new Random();


        for (int i = 0; i < N; ++i){
            utilsForWeb.pushButton("ac");
            number1 = random.nextDouble(1000000);
            utilsForWeb.enterNumber(Double.toString(number1));
            resultCalc = number1;
            numericalExample = Double.toString(number1);

            while (true) {
                function = arrayFunction.get(random.nextInt(arrayFunction.size()));
                number2 = random.nextDouble(1000000);
                utilsForWeb.pushButton(function);
                utilsForWeb.enterNumber(Double.toString(number2));
                resultCalc = utilsForWeb.calculateTest(resultCalc, number2, function);
                numericalExample += " " + function + " " + number2;
                if (random.nextBoolean()){
                    break;
                }
            }
            utilsForWeb.pushButton("=");
            numericalExample += "=" + resultCalc;

            resultWeb = Double.parseDouble($(By.id("calculated-display")).val());
            try {
                Assert.assertEquals(resultCalc, resultWeb, 0.000001);
            } catch (AssertionError e){
                logger.error("Ошибка в тесте: {}", e.getMessage());
                logger.error(numericalExample);
                fail(e.getMessage());
            }
        }

    }
}
