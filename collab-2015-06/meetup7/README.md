# meet-test-check

A Clojure library designed to help you interact with [test.check](https://github.com/clojure/test.check). We'll run some tests, fix some fns and some tests, and explore writing generators.

Helpful Docs:
* [Generators writing guide](https://github.com/clojure/test.check/blob/master/doc/intro.md)
* [API](http://clojure.github.io/test.check/clojure.test.check.generators.html)

## Usage

1. Open up ```src/meet_test_check/core.clj``` and ```test/meet_test_check/core_test.clj``` in your text editor.
1. Run ```$ lein test``` to see whether or not the tests pass.
1. Make changes to ```meet-test-check.core``` and ```meet-test-check.core-test``` until the tests all pass. :)
1. Check out the suggestions in the comment at the end of the ```meet-test-check.core-test``` ns to build your own cool generators.

### Java Interop
MeetJava.java and MeetJavaTest.java (in src/java and test/java, respectively) demonstrate
how JUnit and test.check can be integrated to utilize test.check generators in JUnit tests
and how to test Java classes with test.check tests.

To run the MeetJava class: `java -cp target/meet-test-check-0.1.0-SNAPSHOT-standalone.jar meet_test_check.MeetJava`

To run the MeetJavaTest class: `run-junit-tests.sh` 

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.