package model;

import ui.Quiz;

public class RawScoreForRegularCoded extends RawScore {

    public RawScoreForRegularCoded(Quiz filledQuiz) {
        super(filledQuiz);
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: filters out all reverse-coded questions/answers,then turns question-answer data into raw scores
    @Override
    public void compileScores() {
        for (int i = 0; i < filledQuiz.getQuestions().size(); i++) {
            if (filledQuiz.getQuestions().get(i).getIsReverseCoded()) {
                filledQuiz.getQuestions().remove(i);
                filledQuiz.getAnswers().remove(i);
                i--;
            }
        }
        super.compileScores();
    }
}
