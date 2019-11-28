package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PercentileTest {

    private Percentile percentile;
    private RawScore rawScore;
    private Quiz quiz;
    private Question q1 = new Question(0, "test question1", false);
    private Question q2 = new Question(2, "test question2", false);
    private Question q3 = new Question(3, "test question3", false);
    private Question q4 = new Question(4, "test question4", true);
    private ArrayList<Question> questions = new ArrayList<>(Arrays.asList(q1, q2, q3, q4));

    @BeforeEach
    void setup() throws IOException {
        quiz = new Quiz(questions);
        rawScore = new RawScore(quiz);
        percentile = new Percentile(rawScore.getRawScore(), "./data/testRawScoreBankForPercentile.txt");
    }

    @Test
    void testConstructor() {
        ArrayList<Integer> expectedAllLines = new ArrayList<>(Arrays.asList(1, 4, 3, 1, 2, 3, 5, 2, 4, 4));
        assertEquals(expectedAllLines, percentile.getAllLines());

        ArrayList<Integer> expectedRawO = new ArrayList<>(Arrays.asList(1, 3));
        assertEquals(expectedRawO, percentile.getRawO());

        ArrayList<Integer> expectedRawC = new ArrayList<>(Arrays.asList(4, 5));
        assertEquals(expectedRawC, percentile.getRawC());

        ArrayList<Integer> expectedRawE = new ArrayList<>(Arrays.asList(2, 3));
        assertEquals(expectedRawE, percentile.getRawE());

        ArrayList<Integer> expectedRawA = new ArrayList<>(Arrays.asList(1, 4));
        assertEquals(expectedRawA, percentile.getRawA());

        ArrayList<Integer> expectedRawN = new ArrayList<>(Arrays.asList(2, 4));
        assertEquals(expectedRawN, percentile.getRawN());

        HashMap<Integer, Integer> expectedRawScore = new HashMap<>();
        assertEquals(expectedRawScore, percentile.getRawScore());
    }

    @Test
    void testParseRawScores() {
        ArrayList<Integer> actual = percentile.parseRawScores(2);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    void testCreateAllLines() throws IOException {
        ArrayList<Integer> actual = percentile.createAllLines("./data/testRawScoreBankForPercentile.txt");
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 4, 3, 1, 2, 3, 5, 2, 4, 4));
        assertEquals(expected, actual);
    }

    @Test
    void testGetResults() {
        rawScore.setRawScore(new ArrayList<>(Arrays.asList(0,1,2,3,4)));
        percentile.compileScores();
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(1.0,1.0,50.0,50.0,99.9));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // code taken from
        // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit

        percentile.getResults();
        assertEquals("Your results (in percentiles):\nOpenness: 1.0\nConscientiousness: " +
                "1.0\nExtroversion: 50.0\nAgreeableness: 50.0\nNeuroticism: 99.9\n", outContent.toString());
    }

    @Test
    void testCompileScoresTopPercentile() {
        rawScore.setRawScore(new ArrayList<>(Arrays.asList(5,5,5,5,5)));
        percentile.compileScores();
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(99.9, 99.9, 99.9, 99.9, 99.9));
        assertEquals(expected, percentile.getPercentile());
    }

    @Test
    void testCompileCompileScoresMiddlePercentiles() {
        rawScore.setRawScore(new ArrayList<>(Arrays.asList(0,1,2,3,4)));
        percentile.compileScores();
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(1.0,1.0,50.0,50.0,99.9));
        assertEquals(expected, percentile.getPercentile());
    }
}
