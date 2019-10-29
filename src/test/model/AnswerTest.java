package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    private Answer testAns;
    private Question testQ;

    @BeforeEach
    void setup() {
        testQ = new Question(0, "question", true);
        testAns = new Answer(testQ);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testAns.getAnswer());
        assertTrue(testAns.getQuestion().equals(testQ));
    }

    @Test
    void testSetAnswer() {
        testAns.setAnswer(2);
        assertEquals(2, testAns.getAnswer());
        assertEquals(2, testAns.getQuestion().getAnswer().getAnswer());
    }

    @Test
    void testSetQuestion() {
        Question q2 = new Question(5, "new question", false);
        testAns.setQuestion(q2);
        assertTrue(testAns.getQuestion().equals(q2));
    }



}
