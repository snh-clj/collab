#!/bin/bash

java -cp .:target/meet-test-check-0.1.0-SNAPSHOT-standalone.jar:~/.m2/repository/junit/junit/4.12/junit-4.12.jar org.junit.runner.JUnitCore meet_test_check.MeetJavaTest
