package NegativeTest;

import Utils.*;
import org.junit.Test;

public class TestNegativeCsvSample {

    @Test
    public void testNegativeCsvSample(){
        UtilsForCsv utilsForCsv = new UtilsForCsv();
        utilsForCsv.testCsvSample(DataFlow.testNegativeSamples);
    }


}
