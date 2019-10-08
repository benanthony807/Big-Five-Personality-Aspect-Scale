package ui;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quiz {

    private ArrayList<Question> questions;
    private ArrayList<Integer> answers;

    //EFFECTS: constructs a Quiz with questions and past scores but no answers
    public Quiz(ArrayList<Question> questions, ArrayList<Integer> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public Quiz(Quiz another) {
        this.questions = new ArrayList<>(another.questions);
        this.answers = new ArrayList<>(another.answers);
    }

    //getters:
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    //REQUIRES
    //MODIFIES: this
    //EFFECTS: runs the quiz setup, if that passes runs the actual quiz, then runs a method to deal
    //         with scores and returns percentile scores
    public void run() throws IOException {
        setUpQuiz();
        runQuiz();
        RawScoreForRegularCoded quizRawScoreRegularCoded = new RawScoreForRegularCoded(new Quiz(this));
        RawScoreForReverseCoded quizRawScoreReverseCoded = new RawScoreForReverseCoded(new Quiz(this));
        quizRawScoreReverseCoded.compileScores();
        quizRawScoreRegularCoded.compileScores();
        RawScoreGeneral quizRawScoreGeneral = new RawScoreGeneral(this);
        quizRawScoreGeneral.fillRawScoreGeneral(quizRawScoreRegularCoded, quizRawScoreReverseCoded);
        quizRawScoreGeneral.write(quizRawScoreGeneral.read("./data/rawscorebank.txt"), "./data/rawscorebank.txt");
//        Percentile quizPercentile = new Percentile(quizRawScore.getRawScore());
//        quizPercentile.compileScores();
//        quizPercentile.getResults();
        quizRawScoreGeneral.getResults();
    }

    //REQUIRES:
    //MODIFIES:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: scans for user input, returns that input
    public String getUserInput() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: scans for user's next input integer, returns that integer
    public int getUserAnswer() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }


    //MODIFIES: this
    //EFFECTS: asks questions, stores user's answers, and repeats until all questions are asked
    public void runQuiz() {
        System.out.println("Rate how much you agree with the below statement with a number 1-5.");
        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getQuestion());
            answers.set(i, getUserAnswer());
        }
        System.out.println("Quiz complete.");
    }
}

