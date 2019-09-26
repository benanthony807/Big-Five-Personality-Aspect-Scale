package model;

//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

//import static org.junit.Assert.assertEquals;

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


    @BeforeEach
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
    public void testCheckIfReadyUserReady() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // code taken from
        // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit

        testQuiz.checkIfReady("yes");

        assertEquals("Quiz beginning\n", outContent.toString());
    }

//    @Test
//    public void testCheckIfReadyUserNotReady() {
//        https://stackoverflow.com/questions/309396/java-how-to-test-methods-that-call-system-exit
//        public final ExpectedSystemExit exit = ExpectedSystemExit.none();
//
//        testQuiz.checkIfReady("no");
//
//        assertEquals(System.exit(0), testQuiz.checkIfReady("no"));
//    }



//
//    @Test
//    public void testRun() {
//
//
//    }
//
//    // method doesn't need to be tested bc takes user input
//    @Test
//    public void testSetUpQuiz() {
//
//    }
//
//
//    @Test
//    public void testRunQuiz() {
//
//    }


}
