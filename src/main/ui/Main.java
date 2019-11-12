package ui;

import model.Question;
import model.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Question q0 = new Question(0, "I am interested in abstract ideas", false);
        Question q1 = new Question(1, "I am always on time", false);
        Question q2 = new Question(2, "I am not the life of the party", true);
        Question q3 = new Question(3, "I like to please others", false);
        Question q4 = new Question(4, "I am fearful of the future", false);

        ArrayList<Question> bigFiveQuestions = new ArrayList<>(Arrays.asList(q0, q1, q2, q3, q4));

        Quiz bigFiveQuiz = new Quiz(bigFiveQuestions);
        QuizRunner quizRunner = new QuizRunner();
        quizRunner.run(bigFiveQuiz);
    }
}