package model;

import ui.Quiz;

import java.util.ArrayList;
import java.util.Arrays;

public class RawScoreGeneral extends RawScore {

    public RawScoreGeneral(Quiz filledQuiz) {
        super(filledQuiz);
    }

    //MODIFIES: this
    //EFFECTS: combines reverse-coded and regular-coded raw scores into one
    public void fillRawScoreGeneral(RawScore reg, RawScore reverse) {
        for (int i = 0; i < rawScore.size(); i++) {
            rawScore.set(i, reg.rawScore.get(i) + reverse.rawScore.get(i));
        }
    }
}
