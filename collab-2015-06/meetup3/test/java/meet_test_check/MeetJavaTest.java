package meet_test_check;

import java.lang.Math;
import java.lang.Integer;

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

    @Test
    public void allItemsPositive() {
        boolean allItemsPositive = true;
        int[] xformedArray = MeetJava.xformIntArray(testArray);
        for (int i = 0; i < xformedArray.length; i++) {
            if (xformedArray[i] < 0) {
                allItemsPositive = false;
            }
        }
        assertTrue(allItemsPositive);
    }


    private static int[] initializeArray() {
        int length = 100;
        int upperLimit = Integer.MAX_VALUE;
        int[] testData = new int[length];
        for (int i = 0; i < length; i++) {
            //create an integer between 0 and value of upperLimit (inclusive)
            testData[i] = (int) Math.random() * (upperLimit +1);
        }
        return testData;
    }
}
