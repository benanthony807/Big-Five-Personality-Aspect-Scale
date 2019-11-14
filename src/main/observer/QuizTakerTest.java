package observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Question;
import model.Quiz;
import model.RawScore;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizTakerTest {

    QuizTaker quizTaker;
    RawScore rawScore;
    ArrayList<Question> questions;

    @BeforeEach
    void setup() {
        questions = new ArrayList<>();
        quizTaker = new QuizTaker();
        rawScore = new RawScore(new Quiz(questions));
    }

    @Test
    void testUpdate() {
        HashMap<Integer, Integer> integerHashMap = new HashMap<>();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // code taken from
        // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit

        quizTaker.update(rawScore, integerHashMap);
        assertEquals("nice job quiz done\n", outContent.toString());
//        assertEquals("Your results (in raw scores):\nOpenness: null\nConscientiousness: "
//                +
//                "null\nExtroversion: null\nAgreeableness: null\nNeuroticism: null\n", outContent.toString());
    }
}
