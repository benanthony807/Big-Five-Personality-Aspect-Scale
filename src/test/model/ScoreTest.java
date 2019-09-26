package model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    Score testScore;
    ArrayList<Score> testPastScores;
    ArrayList<Question> testQuestions;
    ArrayList<Integer> testAnswers;
    Question testQ1;
    Question testQ2;
    Question testQ3;


    @Before
    public void runBefore() {
        testQ1 = new Question(0, "I am the life of the party");
        testQ2 = new Question(4, "I prefer quiet spaces");
        testQ3 = new Question(0, "test question");

        testScore = new Score();
        testPastScores = new ArrayList<>();
        testQuestions = new ArrayList<Question>(Arrays.asList(testQ1, testQ2, testQ3));
        testAnswers = new ArrayList<Integer>(Arrays.asList(1, 2, 5));

    }

    @Test
    public void testConstructor() {
        ArrayList<Integer> expectedCategory = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ArrayList<Integer> expectedRawScore = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        ArrayList<Integer> expectedPercentile = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));

        assertEquals(expectedCategory, testScore.getCategory());
        assertEquals(expectedRawScore, testScore.getRawScore());
        assertEquals(expectedPercentile, testScore.getPercentile());

        ArrayList<Integer> expectedRawScore2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 15));
        testScore.setRawScore(expectedRawScore2);

        ArrayList<Integer> expectedPercentile2 = new ArrayList<>(Arrays.asList(1, 4, 5, 6, 99));
        testScore.setPercentile(expectedPercentile2);

        assertEquals(expectedRawScore2, testScore.getRawScore());
        assertEquals(expectedPercentile2, testScore.getPercentile());

    }

    // just calls other methods, don't think needs to be tested
    @Test
    public void testCompileScores() {
        ArrayList<Integer> expectedRawScore = new ArrayList<>(Arrays.asList(6, 0, 0, 0, 2));
        ArrayList<Integer> expectedPercentile = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));

        testScore.compileScores(testQuestions, testAnswers, testPastScores);
        assertEquals(expectedRawScore, testScore.getRawScore());
        assertEquals(expectedPercentile, testScore.getPercentile());
    }

    @Test
    public void testSortAnswers1Item() {
        ArrayList<Question> testQuestion = new ArrayList<>(Arrays.asList(testQ1));
        ArrayList<Integer> testAnswer = new ArrayList<>(Arrays.asList(1));

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0));
        assertEquals(expected, testScore.sortAnswers(testQuestion, testAnswer));
    }

    @Test
    public void testSortAnswersMultipleItems() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(6, 0, 0, 0, 2));
        assertEquals(expected, testScore.sortAnswers(testQuestions, testAnswers));
    }

//
//    // Not yet at percentile functionality so no tests for this
//    @Test
//    public void testRawScoresToPercentiles() {
//
//    }
//
//    @Test
//    void testGetResults() {
//        ArrayList<Integer> expected = new ArrayList<>();
//        expected.add(3);
//        expected.add(4);
//        expected.add(6);
//    }
}
