package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuizTest {
    Quiz testQuiz;
    Database testDatabase;
    Score testScore1;
    Score testScore2;
    ArrayList<Question> testQuestions;
    ArrayList<Integer> testAnswers;
    ArrayList<Score> testPastScores;
    Question testQ1;
    Question testQ2;
    Question testQ3;


    @Before
    public void runBefore() {
        testQ1 = new Question(0, "I am the life of the party");
        testQ2 = new Question(4, "I prefer quiet spaces");
        testQ3 = new Question(0, "test question");

        testScore1 = new Score();
        testScore2 = new Score();
        testPastScores = new ArrayList<Score>(Arrays.asList(testScore2));
        testQuestions = new ArrayList<Question>(Arrays.asList(testQ1, testQ2, testQ3));
        testAnswers = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
        testDatabase = new Database(testQuestions, testPastScores);
        testQuiz = new Quiz(testDatabase.getOptions(), testDatabase.getPastScores());
    }

    @Test
    public void testConstructor() {
        assertEquals(testQuestions, testQuiz.getQuestions());
        assertEquals(testPastScores, testQuiz.getPastScores());
        assertEquals(testAnswers, testQuiz.getAnswers());

        testQuestions.set(2, testQ1);
        assertEquals(testQ1, testQuestions.get(2));

        ArrayList<Integer> testRawScores = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        testScore1.setRawScore(testRawScores);
        testPastScores.set(0, testScore1);
        assertEquals(testScore1, testPastScores.get(0));

        testAnswers.set(0, 1);
        assertEquals(1, testAnswers.get(0));
    }


    @Test
    public void testRun() {


    }

    // method doesn't need to be tested bc takes user input
    @Test
    public void testSetUpQuiz() {

    }


    @Test
    public void testRunQuiz() {

    }


}
