package meet_test_check;

/**
  JavaMeet is a basic Java class whose methods will be tested by
  both JUnit and Clojure's test.check.

 **/

public class MeetJava {

    public static void main(String[] args) {

        System.out.println("If I could by the world a Coke, ...");

    }

    public ArrayList<Integer> xformIntArray (ArrayList<Integer> incoming) {
        ArrayList<Integer> outgoing = new ArrayList<Integer>();

        for (Integer in : incoming) {
            outgoing.add(in * (in % 11));
        }

        return outgoing;
    }

}
