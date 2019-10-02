package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quiz {

    private ArrayList<Question> questions;
    private ArrayList<Integer> answers;
//    private ArrayList<Score> pastScores;

    //EFFECTS: constructs a Quiz with questions and past scores but no answers
    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
//        this.pastRawScores = pastRawScores;
        answers = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
    }

    //getters:
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

//    public ArrayList<RawScore> getPastRawScores() {
//        return pastRawScores;
//    }

    //setters
    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    //REQUIRES
    //MODIFIES: this
    //EFFECTS: runs the quiz setup, if that passes runs the actual quiz, then runs a method to deal
    //         with scores and returns percentile scores
    public void run() throws IOException {
        setUpQuiz();
        runQuiz();
        RawScore quizRawScore = new RawScore(this);
        quizRawScore.compileScores();
        quizRawScore.write(quizRawScore.read("./data/rawscorebank.txt"), "./data/rawscorebank.txt");
//        Percentile quizPercentile = new Percentile(quizRawScore.getRawScore());
//        quizPercentile.compileScores();
//        quizPercentile.getResults();
        quizRawScore.getResults();


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

