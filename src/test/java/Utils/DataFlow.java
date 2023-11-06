package Utils;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFlow {
    public static Map<String, String> numbers = new HashMap<>();
    public static Map <String, String> functions = new HashMap<>();
    public static List<TestSampleStruct> testPositiveSamples = new ArrayList<>();
    public static List<TestSampleStruct> testNegativeSamples = new ArrayList<>();

    public DataFlow(){
        prepareData();
    }
    public void prepareData(){
        prepareButtonData();
        prepareFunctionData();
        prepareTestSample("src/main/resources/positiveTests/samples.csv");
        prepareTestSample("src/main/resources/negativeTests/samples.csv");
    }

    private void prepareTestSample(String path){
        UtilsForCsv utilsForCsv = new UtilsForCsv();
        List<String[]> data = utilsForCsv.loadCSV(path, ";");
        if (data.isEmpty()){
            fail(path + " not found");
        }
        if (path.contains("positiveTests")) {
            data.forEach(sample -> testPositiveSamples.add(new TestSampleStruct(sample[0], sample[1])));
        }
        else {
            data.forEach(sample -> testNegativeSamples.add(new TestSampleStruct(sample[0], sample[1])));
        }

    }
    private void prepareButtonData(){
        numbers.put("1", "button01");
        numbers.put("2", "button02");
        numbers.put("3", "button03");
        numbers.put("4", "button04");
        numbers.put("5", "button05");
        numbers.put("6", "button06");
        numbers.put("7", "button07");
        numbers.put("8", "button08");
        numbers.put("9", "button09");
        numbers.put("0", "button00");
        numbers.put(".", "buttondot");
    }

    private void prepareFunctionData(){
        functions.put("+", "buttonplus");
        functions.put("-", "buttonminus");
        functions.put("*", "buttonmultiply");
        functions.put("/", "buttondivide");
        functions.put("=", "buttonequals");
        functions.put("ac", "buttonallclear");
        functions.put("ce", "buttonclearentry");
        functions.put("m+", "buttonmemoryplus");
        functions.put("mr", "buttonmemoryrecall");
        functions.put("min", "buttonmemoryin");
    }
}
