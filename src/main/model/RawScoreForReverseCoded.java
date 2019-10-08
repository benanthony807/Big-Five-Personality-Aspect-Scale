package model;

import ui.Quiz;

public class RawScoreForReverseCoded extends RawScore {
    public RawScoreForReverseCoded(Quiz filledQuiz) {
        super(filledQuiz);
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: filters out all regular-coded questions/answers, unreverses answers,
    //          then turns question-answer data into raw scores
    @Override
    public void compileScores() {
        for (int i = 0; i < filledQuiz.getQuestions().size(); i++) {
            if (!filledQuiz.getQuestions().get(i).getIsReverseCoded()) {
                filledQuiz.getQuestions().remove(i);
                filledQuiz.getAnswers().remove(i);
                i--;
            }
        }
        unreverseRawScores();
        super.compileScores();
    }

    //REQUIRES: rawScores are integers between 1 and 5
    //MODIFIES: this
    //EFFECTS: flips reverse coded answers so they can be interpreted as answers to regular-coded questions
    public void unreverseRawScores() {
        for (int i = 0; i < filledQuiz.getAnswers().size(); i++) {
            if (filledQuiz.getAnswers().get(i) == 1) {
                filledQuiz.getAnswers().set(i, 5);
            } else if (filledQuiz.getAnswers().get(i) == 2) {
                filledQuiz.getAnswers().set(i, 4);
            } else if (filledQuiz.getAnswers().get(i) == 4) {
                filledQuiz.getAnswers().set(i, 2);
            } else if (filledQuiz.getAnswers().get(i) == 5) {
                filledQuiz.getAnswers().set(i, 1);
            }
        }
    }
}
