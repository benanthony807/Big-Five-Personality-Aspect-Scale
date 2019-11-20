package model;

public interface Score {

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores
    void compileScores();

    //EFFECTS: prints out results of quiz
    void getResults();
}
