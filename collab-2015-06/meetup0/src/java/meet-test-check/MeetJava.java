package meet_test_check;

import java.util.Arrays;
/**
  JavaMeet is a basic Java class whose methods will be tested by
  both JUnit and Clojure's test.check.

 */

public class MeetJava {

    public static void main(String[] args) {

        int[] incoming = { 399328, 8379938, 588377027 };
        MeetJava mj = new MeetJava();
        int[] result = mj.xformIntArray(incoming);

        System.out.println("Original array contents: " + Arrays.toString(incoming));
        System.out.println("Transformed array contents: " + Arrays.toString(result));
    }

    public static int[] xformIntArray (int[] incoming) {

        int[] outgoing = new int[incoming.length];

        int i = 0;
        while (i < incoming.length) {
            outgoing[i] = incoming[i] * (incoming[i] % 11);
            i++;
        }

        return outgoing;
    }

}
