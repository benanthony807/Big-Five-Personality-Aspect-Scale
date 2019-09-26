package model;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Score {

    private ArrayList<Integer> category; // 0 = openness, 1= conscientiousness,
                                         // 2 = extroversion, 3 = agreeableness, 4 = neuroticism
    private ArrayList<Integer> rawScore;
    private ArrayList<Integer> percentile; // [1, 99]

    public Score() {
        category = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
        rawScore = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
        percentile = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
    }

    //getters:
    public ArrayList<Integer> getCategory() {
        return category;
    }

    public ArrayList<Integer> getPercentile() {
        return percentile;
    }

    public ArrayList<Integer> getRawScore() {
        return rawScore;
    }

    //setters:
    public void setRawScore(ArrayList<Integer> rawScores) {
        for (int i = 0; i < rawScores.size(); i++) {
            this.rawScore.set(i, rawScores.get(i));
        }
    }

    public void setPercentile(ArrayList<Integer> percentile) {
        this.percentile = percentile;
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores (for now, eventually percentiles)
    public void compileScores(ArrayList<Question> questions, ArrayList<Integer> answers, ArrayList<Score> pastScores) {
        this.rawScore = (sortAnswers(questions, answers));
        this.percentile = (rawScoresToPercentiles(pastScores));
    }

    //REQUIRES: every question has an answer
    //MODIFIES:
    //EFFECTS: sorts and sums answers by category
    public ArrayList<Integer> sortAnswers(ArrayList<Question> questions, ArrayList<Integer> answers) {
        ArrayList<Integer> summedAnswers = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        for (int i = 0; i < questions.size(); i++) {
            int category = questions.get(i).getCategory();
            int updatedRawScore = answers.get(i) + summedAnswers.get(category);
            summedAnswers.set(category, updatedRawScore);
        }
        return summedAnswers;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns corresponding percentiles for the raw scores it's been given
    public ArrayList<Integer> rawScoresToPercentiles(ArrayList<Score> pastScores) {
        //some sort of looping through of the past score arraylists until you get to the right one
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
        return a;
    }

    public void getResults() {
        System.out.println("Your results (in raw scores):");
        System.out.println("Openness: " + this.rawScore.get(0));
        System.out.println("Conscientiousness: " + this.rawScore.get(1));
        System.out.println("Extroversion: " + this.rawScore.get(2));
        System.out.println("Agreeableness: " + this.rawScore.get(3));
        System.out.println("Neuroticism: " + this.rawScore.get(4));
    }
}

