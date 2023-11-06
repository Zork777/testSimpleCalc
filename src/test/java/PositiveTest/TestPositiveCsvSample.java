package PositiveTest;

import Utils.*;
import org.junit.Test;

public class TestPositiveCsvSample {

    @Test
    public void testPositiveCsvSample(){
        UtilsForCsv utilsForCsv = new UtilsForCsv();
        utilsForCsv.testCsvSample(DataFlow.testPositiveSamples);

    }


}
