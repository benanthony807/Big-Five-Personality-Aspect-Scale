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


public class RawScoreTest {
    RawScore testRawScore;
    ArrayList<Question> testQuestions;
    ArrayList<Integer> testAnswers;
    Question testQ1;
    Question testQ2;
    Question testQ3;
    Question testQ4;
    Quiz testQuiz;


    @BeforeEach
    public void runBefore() {
        testQ1 = new Question(0, "test question1", false);
        testQ2 = new Question(4, "test question2", false);
        testQ3 = new Question(0, "test question3", false);
        testQ4 = new Question(1, "test question4", true);
        testQuestions = new ArrayList<Question>(Arrays.asList(testQ1, testQ2, testQ3, testQ4));
        testAnswers = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 2));

        testQuiz = new Quiz(testQuestions, testAnswers);

        testRawScore = new RawScore(testQuiz);

    }

    @Test
    public void testConstructor() {
        assertEquals(new HashMap<Integer, Integer>(), testRawScore.getRawScore());

        ArrayList<Integer> ans = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 4));
        assertEquals(ans, testRawScore.getQuiz().getAnswers());
        assertTrue(testRawScore.getQuiz().getQuestions().equals(testQuestions));

        HashMap<Integer, Integer> expected = new HashMap<>();
        ArrayList<Integer> asList = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 15));
        for (int i = 0; i < asList.size(); i++) {
            expected.put(i, asList.get(i));
        }
        testRawScore.setRawScore(asList);

        assertEquals(expected, testRawScore.getRawScore());
    }

    @Test
    public void testCompileScores() {
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
    public void testSortAnswers1Item() {
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
    public void testSortAnswersMultipleItems() {
        ArrayList<Integer> asList = new ArrayList<>(Arrays.asList(6, 2, 0, 0, 2));
        HashMap<Integer, Integer> expected = new HashMap<>();
        for (int i = 0; i < asList.size(); i++) {
            expected.put(i, asList.get(i));
        }
        assertEquals(expected, testRawScore.sortAnswers(testQuestions, testAnswers));
    }

    @Test
    public void testGetResults() {
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
    public void readTestNoExceptionExpected() throws IOException {
        try {
            List<String> expectedResult = Files.readAllLines(Paths.get("./data/rawscorebank.txt"));
            assertEquals(expectedResult, testRawScore.read("./data/rawscorebank.txt"));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void readTestIOExceptionExpected() throws IOException {
        try {
            testRawScore.read("./data/nonexistentfile.txt");
            fail();
        } catch (IOException e) {

        }
    }

    @Test
    public void writeTestNoExceptionExpected() throws IOException {
        try {
            List<String> input = Files.readAllLines(Paths.get("./data/testrawscorebankinput.txt"));
            ArrayList<Integer> newScores = new ArrayList<>(Arrays.asList(1,2,3,4,5));
            testRawScore.setRawScore(newScores);
            testRawScore.write(input, "./data/testrawscorebankoutput.txt");
            assertEquals(Arrays.asList("5   1   1   4   2", "1   2   3   4   5"), testRawScore.read("./data/testrawscorebankoutput.txt"));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void writeTestIOExceptionExpected() throws IOException {
        try {
            List<String> input = Files.readAllLines(Paths.get("./data/testrawscorebankinput.txt"));
            testRawScore.write(input, "");
            fail();
        } catch (IOException e) {

        }
    }
}

