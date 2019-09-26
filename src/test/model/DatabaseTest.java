package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

//import static org.junit.Assert.assertEquals;


public class DatabaseTest {
    Score testScore;
    ArrayList<Score> testPastScores;
    ArrayList<Question> testQuestions;
    Question testQ1;
    Question testQ2;
    Question testQ3;
    Database testDatabase;

    @BeforeEach
    public void runBefore() {
        testQ1 = new Question(0, "I am the life of the party");
        testQ2 = new Question(4, "I prefer quiet spaces");
        testQ3 = new Question(0, "test question");

        testScore = new Score();
        testPastScores = new ArrayList<>();
        testQuestions = new ArrayList<Question>(Arrays.asList(testQ1, testQ2, testQ3));

        testDatabase = new Database(testQuestions, testPastScores);
    }

    @Test
    public void testConstructor() {
        assertEquals(testQuestions, testDatabase.getOptions());
        assertEquals(testPastScores, testDatabase.getPastScores());
    }
}

