package model;


import ui.Quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;

public class RawScore extends FileReaderWriter implements Score {

    protected HashMap<Integer, Integer> rawScore;
    protected Quiz regularCoded;
    protected Quiz reverseCoded;

    public RawScore(Quiz regularCoded) {
        rawScore = new HashMap<>();
        this.regularCoded = new Quiz(regularCoded);
        this.regularCoded.filterRegularCoded();
        this.reverseCoded = new Quiz(regularCoded);
        reverseCoded.filterReverseCoded();
        this.regularCoded.getQuestions().addAll(reverseCoded.getQuestions());
        this.regularCoded.getAnswers().addAll(reverseCoded.getAnswers());
    }

//    getters:
    public HashMap<Integer, Integer> getRawScore() {
        return rawScore;
    }

    public Quiz getQuiz() {
        return regularCoded;
    }

    public void setRawScore(ArrayList<Integer> scores) {
        for (int i = 0; i < scores.size(); i++) {
            if (!rawScore.containsKey(i)) {
                rawScore.put(i, scores.get(i));
            } else {
                rawScore.replace(i, scores.get(i));
            }
        }
    }


    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores
    @Override
    public void compileScores() {
        this.rawScore = sortAnswers(this.regularCoded.getQuestions(), this.regularCoded.getAnswers());
    }

    //REQUIRES: every question has an answer
    //MODIFIES:
    //EFFECTS: sorts and sums answers by category
    public HashMap<Integer, Integer> sortAnswers(ArrayList<Question> questions, ArrayList<Integer> answers) {
        ArrayList<Integer> summedAnswers = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        for (int i = 0; i < questions.size(); i++) {
            int category = questions.get(i).getCategory();
            int updatedRawScore = answers.get(i) + summedAnswers.get(category);
            summedAnswers.set(category, updatedRawScore);
        }
        HashMap<Integer, Integer> actualAnswers = new HashMap<>();
        for (int i = 0; i < summedAnswers.size(); i++) {
            actualAnswers.put(i, summedAnswers.get(i));
        }
        return actualAnswers;
    }

    //EFFECTS: prints out results of quiz (in raw scores)
    @Override
    public void getResults() {
        System.out.println("Your results (in raw scores):");
        System.out.println("Openness: " + this.rawScore.get(0));
        System.out.println("Conscientiousness: " + this.rawScore.get(1));
        System.out.println("Extroversion: " + this.rawScore.get(2));
        System.out.println("Agreeableness: " + this.rawScore.get(3));
        System.out.println("Neuroticism: " + this.rawScore.get(4));
    }


    //MODIFIES: file with path name output
    //EFFECTS: adds this.rawScores to the end of writerFile
    @Override
    public void write(List<String> lines, String output) throws FileNotFoundException, UnsupportedEncodingException {
//        try {
        PrintWriter writer = new PrintWriter(output, "UTF-8");
        lines.add(this.rawScore.get(0) + "   " + this.rawScore.get(1) + "   " + this.rawScore.get(2) + "   "
                + this.rawScore.get(3) + "   " + this.rawScore.get(4));
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }
}