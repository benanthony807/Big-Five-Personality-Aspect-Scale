package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;


public class QuizTest {
    RawScore testRawScore;
    ArrayList<Question> testQuestions;
    ArrayList<Integer> testAnswers;
    Question testQ1;
    Question testQ2;
    Question testQ3;
    Quiz testQuiz;


    @BeforeEach
    public void runBefore() {
        testQ1 = new Question(0, "test question1", true);
        testQ2 = new Question(4, "test question2", false);
        testQ3 = new Question(0, "test question3", true);
        testQuestions = new ArrayList<Question>(Arrays.asList(testQ1, testQ2, testQ3));
        testAnswers = new ArrayList<Integer>(Arrays.asList(1, 2, 5));
        testQuiz = new Quiz(testQuestions);
        testQuiz.setAnswers(testAnswers);

        testRawScore = new RawScore(testQuiz);

    }

    @Test
    public void testConstructor() {
        assertEquals(testQuestions, testQuiz.getQuestions());

        ArrayList<Integer> expectedAnswers = new ArrayList<>(Arrays.asList(1,2,5));
        assertEquals((expectedAnswers), testQuiz.getAnswers());

        testQuestions.set(2, testQ1);
        assertEquals(testQ1, testQuestions.get(2));

        testAnswers.set(0, 1);
        assertEquals(1, testAnswers.get(0));
    }

//    @Test
//    public void testCheckIfReadyUserReady() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        // code taken from
//        // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit
//
//        testQuiz.checkIfReady("yes");
//
//        assertEquals("Quiz beginning\n", outContent.toString());
//    }

    @Test
    void testFilterRegularCoded() {
        testQuiz.filterRegularCoded();
        assertEquals(1, testQuiz.getQuestions().size());
        assertTrue(testQuiz.getQuestions().contains(testQ2));
        assertEquals(1, testQuiz.getAnswers().size());
        assertTrue(testQuiz.getAnswers().contains(2));

    }

    @Test
    void testFilterReverseCoded() {
        testQuiz.filterReverseCoded();
        assertEquals(2, testQuiz.getQuestions().size());
        assertTrue(testQuiz.getQuestions().contains(testQ1));
        assertTrue(testQuiz.getQuestions().contains(testQ3));

        assertEquals(2, testQuiz.getAnswers().size());
        assertTrue(testQuiz.getAnswers().contains(1));
        assertTrue(testQuiz.getAnswers().contains(5));
    }

    @Test
    public void testUnreverseRawScores() {
        ArrayList<Integer> answers = new ArrayList<>(Arrays.asList(1,2,3));
        ArrayList<Question> questions = new ArrayList<>(Arrays.asList(testQ1, testQ2, testQ3));
        Quiz quiz = new Quiz(questions);
        quiz.setAnswers(answers);

        quiz.unreverseAnswers();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(5,4,3));
        assertEquals(expected, quiz.getAnswers());
    }



//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
//
//    @Test
//    public void testCheckIfReadyUserNotReady() {
//        //https://stackoverflow.com/questions/309396/java-how-to-test-methods-that-call-system-exit
//        exit.expectSystemExit();
//        testQuiz.checkIfReady("no");
//    }

}
