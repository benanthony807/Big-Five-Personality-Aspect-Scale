package model;


import ui.Quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.PrintWriter;

public abstract class RawScore extends FileReaderWriter implements Score {

    protected ArrayList<Integer> rawScore;
    protected Quiz filledQuiz;

    public RawScore(Quiz filledQuiz) {
        rawScore = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
        this.filledQuiz = filledQuiz;
    }

    //getters:
    public ArrayList<Integer> getRawScore() {
        return rawScore;
    }

    public Quiz getFilledQuiz() {
        return filledQuiz;
    }

    //setters:
    public void setRawScore(ArrayList<Integer> rawScores) {
        for (int i = 0; i < rawScores.size(); i++) {
            this.rawScore.set(i, rawScores.get(i));
        }
    }

    public void setFilledQuiz(Quiz filledQuiz) {
        this.filledQuiz = filledQuiz;
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores

    @Override
    public void compileScores() {
        this.rawScore = sortAnswers(this.filledQuiz.getQuestions(), this.filledQuiz.getAnswers());
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
        PrintWriter writer = new PrintWriter(output, "UTF-8");
        lines.add(this.rawScore.get(0) + "   " + this.rawScore.get(1) + "   " + this.rawScore.get(2) + "   "
                + this.rawScore.get(3) + "   " + this.rawScore.get(4));
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }
//
//    @Override
//    public ArrayList<String> splitOnSpace(String line) {
//        String[] splits = line.split("   ");
//        return new ArrayList<>(Arrays.asList(splits));}

}


