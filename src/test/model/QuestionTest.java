package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    Question testQ1;
    Question testQ2;

    @BeforeEach
    public void runBefore() {
        testQ1 = new Question(1, "I am the life of the party");
        testQ2 = new Question(2, "I prefer quiet spaces");

    }

    @Test
    public void testConstructor() {
        // see if the constructor makes a question with the inputted category and question
        assertEquals(1, testQ1.getCategory());
        assertEquals("I am the life of the party", testQ1.getQuestion());
    }

}
