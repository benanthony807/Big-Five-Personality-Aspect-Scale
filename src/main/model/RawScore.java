package model;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import observer.QuizTaker;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;

public class RawScore extends FileReaderWriter implements Score {

    protected HashMap<Integer, Integer> rawScore;
    protected Quiz quiz;

    public RawScore(Quiz uncoded) {
        rawScore = new HashMap<>();
        quiz = code(uncoded);
        addObserver(new QuizTaker());
    }

//    getters:
    public HashMap<Integer, Integer> getRawScore() {
        return rawScore;
    }

    public Quiz getQuiz() {
        return quiz;
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
        this.rawScore = sortAnswers(this.quiz.getQuestions(), this.quiz.getAnswers());
        setChanged();
        notifyObservers(rawScore);
    }

    //REQUIRES: every question has an answer
    //MODIFIES:
    //EFFECTS: sorts and sums answers by category
    public HashMap<Integer, Integer> sortAnswers(ArrayList<Question> questions, ArrayList<Integer> answers) {
        HashMap<Integer, Integer> sortedAndSummed = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            sortedAndSummed.put(i, 0);
        }
        for (int i = 0; i < questions.size(); i++) {
            int category = questions.get(i).getCategory();
            int updatedScore = answers.get(i) + sortedAndSummed.get(category);
            sortedAndSummed.replace(category, updatedScore);
        }
        return sortedAndSummed;
    }

    //EFFECTS: prints out results of quiz (in raw scores)
    @Override
    public void getResults() {
        System.out.println("Your results (in raw scores):");
        System.out.println("Openness: " + rawScore.get(0));
        System.out.println("Conscientiousness: " + rawScore.get(1));
        System.out.println("Extroversion: " + rawScore.get(2));
        System.out.println("Agreeableness: " + rawScore.get(3));
        System.out.println("Neuroticism: " + rawScore.get(4));
    }

    //MODIFIES: file with path name output
    //EFFECTS: adds this.rawScores to the end of writerFile
    @Override
    public void write(List<String> lines, String output) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(output, "UTF-8");
        lines.add(rawScore.get(0) + "   " + rawScore.get(1) + "   " + rawScore.get(2) + "   "
                + rawScore.get(3) + "   " + rawScore.get(4));
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    //MODIFIES: regularCoded
    //EFFECTS: splits quiz into regular and reverse coded questions, unreverses the reverse coded questions,
    //         recombines and returns the whole quiz regular coded
    public Quiz code(Quiz uncoded) {
        Quiz regularCoded = new Quiz(uncoded);
        Quiz reverseCoded = new Quiz(uncoded);
        regularCoded.filterRegularCoded();
        reverseCoded.filterReverseCoded();
        regularCoded.getQuestions().addAll(reverseCoded.getQuestions());
        regularCoded.getAnswers().addAll(reverseCoded.getAnswers());
        return regularCoded;
    }

}