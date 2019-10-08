package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Quiz;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RawScoreGeneralTest {
    RawScoreGeneral testRawScoreGeneral;
    RawScoreForRegularCoded testRawScoreRegularCoded;
    RawScoreForReverseCoded testRawScoreReverseCoded;
    Quiz testQuiz;
    ArrayList<Question> testQuestions;
    ArrayList<Integer> testAnswers;
    Question testQ1;
    Question testQ2;

    @BeforeEach
    public void setup() {
        testRawScoreRegularCoded = new RawScoreForRegularCoded(testQuiz);
        testRawScoreRegularCoded.setRawScore(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));
        testRawScoreReverseCoded = new RawScoreForReverseCoded(testQuiz);
        testRawScoreReverseCoded.setRawScore(new ArrayList<Integer>(Arrays.asList(1,1,1,1,1)));

    }

    @Test
    public void testFillRawScoreGeneral() {
        testRawScoreGeneral = new RawScoreGeneral(testQuiz);
        testRawScoreGeneral.fillRawScoreGeneral(testRawScoreRegularCoded, testRawScoreReverseCoded);
        ArrayList<Integer> expectedRawScore = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6));
        assertEquals(expectedRawScore, testRawScoreGeneral.getRawScore());

//        Quiz expectedQuiz = new Quiz(new ArrayList<Question>(Arrays.asList()));
//        assertEquals(expectedQuiz, testRawScoreGeneral.getFilledQuiz());
    }
}