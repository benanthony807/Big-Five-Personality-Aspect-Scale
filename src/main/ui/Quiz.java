package ui;

import java.util.Scanner;

public class Quiz {

    private String questions;
    private int answers;

    public Quiz(String questions, int answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public static void main(String[] args) {
        String q = "1. I have a hard time understanding abstract ideas";
        Quiz bigFive = new Quiz(q, 0);
        bigFive.setUpQuiz();
    }

    public void setUpQuiz() {
        System.out.println("Welcome! This application provides Big Five Personality tests.");
        System.out.println("Are you ready to start the quiz? (enter yes or no)");
        Scanner scan = new Scanner(System.in);
        if (scan.next().equals("yes")) {
            System.out.println("You answered " + runQuiz() + ". That means absolutely nothing");
        } else {
            System.out.println("Let me know when you're ready! (enter ready when ready)");
            while (true) {
                if (scan.next().equals("ready")) {
                    break;
                }
            }
            System.out.println("You answered " + runQuiz() + ". That means absolutely nothing");

        }
    }

    public int runQuiz() {
        System.out.println("Rate how much you agree with the below statement with a number 1-5.");
        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
        System.out.println(questions);
        Scanner scan = new Scanner(System.in);
        answers = scan.nextInt();
        return answers;

    }


}
