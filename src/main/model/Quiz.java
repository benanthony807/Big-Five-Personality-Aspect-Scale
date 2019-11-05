package model;

import java.util.ArrayList;

public class Quiz {

    private ArrayList<Question> questions;
    private ArrayList<Integer> answers;

    //EFFECTS: constructs a Quiz with questions and past scores but no answers
    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        this.answers = generateAnswers();
    }

    public ArrayList<Integer> generateAnswers() {
        ArrayList<Integer> answers = new ArrayList<>();
        for (Question question: questions) {
            answers.add(0);
        }
        return answers;
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

    public void setAnswers(ArrayList<Integer> answers) {
        for (int i = 0; i < answers.size(); i++) {
            this.answers.set(i, answers.get(i));
        }
    }

//    //MODIFIES: this
//    //EFFECTS: runs the quiz setup, if that passes runs the actual quiz, then runs a method to deal
//    //         with scores and returns percentile scores
//    public void run() {
//        setUpQuiz();
//        runQuiz();
//        RawScore rawScore = new RawScore(this);
//        rawScore.compileScores();
//        try {
//            rawScore.write(rawScore.read("./data/rawscorebank.txt"), "./data/rawscorebank.txt");
//        } catch (IOException e) {
//            System.out.println("Scores could not be stored");
//        }
//        rawScore.getResults();
//    }
//
//    //EFFECTS: welcomes users, asks them if they're ready to start quiz, if yes starts quiz, if not
//    //         waits until they are ready
//    public void setUpQuiz() {
//        System.out.println("Welcome! This program provides Big Five Personality tests.");
//        System.out.println("Are you ready to start the quiz? (enter yes or no)");
//        checkIfReady(getUserInput());
//    }
//
//    public void checkIfReady(String userInput) {
//        if (userInput.equals("yes")) {
//            System.out.println("Quiz beginning");
//        } else {
//            System.exit(0);
//        }
//    }
//
//    //EFFECTS: scans for user input, returns that input
//    public String getUserInput() {
//        Scanner scan = new Scanner(System.in);
//        return scan.next();
//    }
//
//    //EFFECTS: scans for user's next input integer, returns that integer,
//    //         throws IntegersOutOfBoundsException if a number < 1 or > 5 is entered
//    public int getUserAnswer() throws IntegersOutOfBoundsException {
//        Scanner scan = new Scanner(System.in);
//        int ans = scan.nextInt();
//        if (ans > 5 || ans < 1) {
//            throw new IntegersOutOfBoundsException();
//        }
//        return ans;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: asks questions, stores user's answers, and repeats until all questions are asked
//    public void runQuiz() {
//        System.out.println("Rate how much you agree with the below statement with a number 1-5.");
//        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
//        for (int i = 0; i < questions.size(); i++) {
//            try {
//                System.out.println((i + 1) + ". " + questions.get(i).getQuestion());
//                answers.set(i, getUserAnswer());
//            } catch (IntegersOutOfBoundsException e) {
//                System.out.println("You've entered an invalid number, please try again.");
//                i--;
//            } finally {
//                continue;
//            }
//        }
//        System.out.println("Quiz complete.");
//    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: filters out all reverse-coded questions/answers, then turns question-answer data into raw scores
    public void filterRegularCoded() {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getIsReverseCoded()) {
                questions.remove(i);
                answers.remove(i);
                i--;
            }
        }
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: filters out all regular-coded questions/answers, unreverses answers,
    //          then turns question-answer data into raw scores
    public void filterReverseCoded() {
        for (int i = 0; i < questions.size(); i++) {
            if (!questions.get(i).getIsReverseCoded()) {
                questions.remove(i);
                answers.remove(i);
                i--;
            }
        }
        unreverseAnswers();
    }

    //REQUIRES: rawScores are integers between 1 and 5
    //MODIFIES: this
    //EFFECTS: flips reverse coded answers so they can be interpreted as answers to regular-coded questions
    public void unreverseAnswers() {
        for (int i = 0; i < this.getAnswers().size(); i++) {
            if (answers.get(i) == 1) {
                answers.set(i, 5);
            } else if (answers.get(i) == 2) {
                answers.set(i, 4);
            } else if (answers.get(i) == 4) {
                answers.set(i, 2);
            } else if (answers.get(i) == 5) {
                answers.set(i, 1);
            }
        }
    }
}

