package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


class RawScoreTest {
    private RawScore testRawScore;
    private ArrayList<Question> testQuestions;
    private ArrayList<Integer> testAnswers;
    private Question testQ1;


    @BeforeEach
    void runBefore() {
        testQ1 = new Question(0, "test question1", false);
        Question testQ2 = new Question(4, "test question2", false);
        Question testQ3 = new Question(0, "test question3", false);
        Question testQ4 = new Question(1, "test question4", true);
        testQuestions = new ArrayList<>(Arrays.asList(testQ1, testQ2, testQ3, testQ4));
        testAnswers = new ArrayList<>(Arrays.asList(1, 2, 5, 2));

        Quiz testQuiz = new Quiz(testQuestions);
        testQuiz.setAnswers(testAnswers);

        testRawScore = new RawScore(testQuiz);

    }

    @Test
    void testConstructor() {
        assertEquals(new HashMap<Integer, Integer>(), testRawScore.getRawScore());

        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(1, 2, 5, 4));
        assertEquals(ans, testRawScore.getQuiz().getAnswers());
        assertEquals(testRawScore.getQuiz().getQuestions(), testQuestions);

        HashMap<Integer, Integer> expected = new HashMap<>();
        ArrayList<Integer> asList = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 15));
        for (int i = 0; i < asList.size(); i++) {
            expected.put(i, asList.get(i));
        }
        testRawScore.setRawScore(asList);

        assertEquals(expected, testRawScore.getRawScore());
    }

    @Test
    void testCompileScores() {
        ArrayList<Integer> asList = new ArrayList<>(Arrays.asList(6, 4, 0, 0, 2));
        HashMap<Integer, Integer> expected = new HashMap<>();
        for (int i = 0; i < asList.size(); i++) {
            expected.put(i, asList.get(i));
        }
        testRawScore.compileScores();
        assertEquals(expected, testRawScore.getRawScore());
       // this.rawScore = sortAnswers(this.filledQuiz.getQuestions(), this.filledQuiz.getAnswers());
    }


    @Test
    void testSortAnswers1Item() {
        ArrayList<Question> testQuestion = new ArrayList<>(Arrays.asList(testQ1));
        ArrayList<Integer> testAnswer = new ArrayList<>(Arrays.asList(1));
        ArrayList<Integer> asList = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0));
        HashMap<Integer, Integer> expected = new HashMap<>();
        for (int i = 0; i < asList.size(); i++) {
            expected.put(i, asList.get(i));
        }
        assertEquals(expected, testRawScore.sortAnswers(testQuestion, testAnswer));
    }

    @Test
    void testSortAnswersMultipleItems() {
        ArrayList<Integer> asList = new ArrayList<>(Arrays.asList(6, 2, 0, 0, 2));
        HashMap<Integer, Integer> expected = new HashMap<>();
        for (int i = 0; i < asList.size(); i++) {
            expected.put(i, asList.get(i));
        }
        assertEquals(expected, testRawScore.sortAnswers(testQuestions, testAnswers));
    }

    @Test
    void testGetResults() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 4, 5, 0, 0));
        testRawScore.setRawScore(expected);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // code taken from
        // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit

        testRawScore.getResults();
        assertEquals("Your results (in raw scores):\nOpenness: 3\nConscientiousness: " +
                "4\nExtroversion: 5\nAgreeableness: 0\nNeuroticism: 0\n", outContent.toString());
    }

    @Test
    void readTestNoExceptionExpected() {
        try {
            List<String> expectedResult = Files.readAllLines(Paths.get("./data/rawscorebank.txt"));
            assertEquals(expectedResult, testRawScore.read("./data/rawscorebank.txt"));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void readTestIOExceptionExpected() {
        try {
            testRawScore.read("./data/nonexistentfile.txt");
            fail();
        } catch (IOException e) {
        }
    }

    @Test
    void writeTestNoExceptionExpected() {
        try {
            List<String> input = Files.readAllLines(Paths.get("./data/testrawscorebankinput.txt"));
            ArrayList<Integer> newScores = new ArrayList<>(Arrays.asList(1,2,3,4,5));
            testRawScore.setRawScore(newScores);
            testRawScore.write(input, "./data/testrawscorebankoutput.txt");
            assertEquals(Arrays.asList("5   1   1   4   2", "1\t2\t3\t4\t5"), testRawScore.read("./data/testrawscorebankoutput.txt"));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void writeTestIOExceptionExpected() {
        try {
            List<String> input = Files.readAllLines(Paths.get("./data/testrawscorebankinput.txt"));
            testRawScore.write(input, "");
            fail();
        } catch (IOException e) {
        }
    }
}

