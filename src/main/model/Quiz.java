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

