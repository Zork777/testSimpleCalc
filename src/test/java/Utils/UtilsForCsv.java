package Utils;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.fail;


public class UtilsForCsv extends LogMy {

    public void testCsvSample(List<TestSampleStruct> data){
        open("/styled/apps/calculator.html");
        List<String> sequenceValue;
        UtilsForWeb utilsForWeb = new UtilsForWeb();
        String resultWeb;
        List <String> errors = new ArrayList<>();

        for (TestSampleStruct testSample : data) {
            utilsForWeb.pushButton("ac");
            sequenceValue = splitTestSample(testSample.getSample());
            if (sequenceValue.contains(null)){
                fail("Error in input data: " + testSample.getSample());
            }
            sequenceValue.forEach(function -> {
                if (utilsForWeb.isNumeric(function)) {
                    for (String symbol : function.split("")) {
                        utilsForWeb.pushButton(symbol); //вводим число
                    }
                } else {
                    utilsForWeb.pushButton(function);
                }
            });


            utilsForWeb.pushButton("=");

            resultWeb = $(By.id("calculated-display")).val();
            try {
                Assert.assertEquals(testSample.getAnswer(), resultWeb);
            } catch (AssertionError e){
                logger.error("Sample: " + sequenceValue + " Ошибка в тесте: {}", e.getMessage());
                errors.add(e.getMessage());
            }

        }
        if (!errors.isEmpty()) {
            String errorMessage = "";
            for (String error : errors) {
                errorMessage += "\n" + error;
            }
            fail(errorMessage);
        }

    }
    public static List <String> splitTestSample(String sample){
        String operators = "-|\\+|\\*|/|ac|ce|mr|min|m\\+";
        List <String> sampleSplit = new ArrayList<>();
        String[] numbers;
        Pattern operatorPattern = Pattern.compile(operators);
        numbers = operatorPattern.split(sample);
        Matcher operatorMatcher = operatorPattern.matcher(sample);
        Iterator <String> numbersIterator = Arrays.stream(numbers).iterator();

        while (operatorMatcher.find()){
            String operator = operatorMatcher.group();
            if (numbersIterator.hasNext()) {
                sampleSplit.add(numbersIterator.next());
            }
            sampleSplit.add(operator);
        }
        if (numbersIterator.hasNext()) {
            sampleSplit.add(numbersIterator.next());
        }
        return sampleSplit.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String[]> loadCSV (String filePath, String csvSeparator) {
        return loadCSV(filePath, csvSeparator, 1);
    }


    public List<String[]> loadCSV (String filePath, String csvSeparator, int startLine) {
        List<String[]> data = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (startLine <= 0) {
                    String[] columns = line.split(csvSeparator);
                    data.add(new String[]{columns[0], columns[1]});
                }
                --startLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
