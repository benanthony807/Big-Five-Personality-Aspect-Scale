package ui;

import exceptions.IntegersOutOfBoundsException;
import model.Quiz;
import model.RawScore;

import java.io.IOException;
import java.util.Scanner;

public class QuizRunner {

    //MODIFIES: this
    //EFFECTS: runs the quiz setup, if that passes runs the actual quiz, then runs a method to deal
    //         with scores and returns percentile scores
    public void run(Quiz quiz) {
        setUpQuiz();
        runQuiz(quiz);
        RawScore rawScore = new RawScore(quiz);
        rawScore.compileScores();
        try {
            rawScore.write(rawScore.read("./data/rawscorebank.txt"), "./data/rawscorebank.txt");
        } catch (IOException e) {
            System.out.println("Scores could not be stored");
        }
        rawScore.getResults();
    }

    //EFFECTS: welcomes users, asks them if they're ready to start quiz, if yes starts quiz, if not
    //         waits until they are ready
    public void setUpQuiz() {
        System.out.println("Welcome! This program provides Big Five Personality tests.");
        System.out.println("Are you ready to start the quiz? (enter yes or no)");
        checkIfReady(getUserInput());
    }

    public void checkIfReady(String userInput) {
        if (userInput.equals("yes")) {
            System.out.println("Quiz beginning");
        } else {
            System.exit(0);
        }
    }

    //EFFECTS: scans for user input, returns that input
    public String getUserInput() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    //EFFECTS: scans for user's next input integer, returns that integer,
    //         throws IntegersOutOfBoundsException if a number < 1 or > 5 is entered
    public int getUserAnswer() throws IntegersOutOfBoundsException {
        Scanner scan = new Scanner(System.in);
        int ans = scan.nextInt();
        if (ans > 5 || ans < 1) {
            throw new IntegersOutOfBoundsException();
        }
        return ans;
    }

    //MODIFIES: this
    //EFFECTS: asks questions, stores user's answers, and repeats until all questions are asked
    public void runQuiz(Quiz quiz) {
        System.out.println("Rate how much you agree with the below statement with a number 1-5.");
        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            try {
                System.out.println((i + 1) + ". " + quiz.getQuestions().get(i).getQuestion());
                quiz.getAnswers().set(i, getUserAnswer());
            } catch (IntegersOutOfBoundsException e) {
                System.out.println("You've entered an invalid number, please try again.");
                i--;
            } finally {
                continue;
            }
        }
        System.out.println("Quiz complete.");
    }
}
