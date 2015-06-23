package meet_test_check;

import java.lang.Math;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import meet_test_check.MeetJava;

public class MeetJavaTest {

    private static int[] testArray;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        testArray = initializeArray();
    }

    @Test
    public void testXformIntArray() {
        int[] xformedArray = MeetJava.xformIntArray(testArray);
        assertTrue(xformedArray.length == testArray.length);
    }

    private static int[] initializeArray() {
        int length = 100;
        int upperLimit = 5332232;
        int[] testData = new int[length];
        for (int i = 0; i < length; i++) {
            //create an integer between 0 and value of upperLimit (inclusive)
            testData[i] = (int) Math.random() * (upperLimit +1);
        }
        return testData;
    }
}
