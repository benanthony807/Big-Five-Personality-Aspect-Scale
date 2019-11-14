package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    Question testQ1;
    Question testQ2;

    @BeforeEach
    public void runBefore() {
        testQ1 = new Question(1, "I am the life of the party", false);
        testQ2 = new Question(2, "I prefer quiet spaces", true);

    }

    @Test
    public void testConstructor() {
        // see if the constructor makes a question with the inputted category and question
        assertEquals(1, testQ1.getCategory());
        assertEquals("I am the life of the party", testQ1.getQuestion());

        testQ1.setCategory(0);
//        assertTrue(testQ1.getAnswer().getQuestion().equals(testQ1));
        testQ1.setQuestion("New question");
//        assertTrue(testQ1.getAnswer().getQuestion().equals(testQ1));
        assertEquals(0, testQ1.getCategory());
        assertEquals("New question", testQ1.getQuestion());
        testQ1.setIsReverseCoded(true);
        assertTrue(testQ1.getIsReverseCoded());
//        assertTrue(testQ1.getAnswer().getQuestion().equals(testQ1));

//        Answer a = new Answer(testQ1);
//        assertTrue(testQ1.getAnswer().equals(a));
    }

    @Test
    void testSetAnswer() {
//       testQ1.setAnswer(2);
//        assertEquals(2, testQ1.getAnswer().getAnswer());
    }



}
