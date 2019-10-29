// package model;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Percentile extends FileReaderWriter implements Score {
//
//    private ArrayList<Integer> percentile;
//    private ArrayList<Integer> rawScore;
//
//    public Percentile(ArrayList<Integer> rawScore) {
//        percentile = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
//        this.rawScore = rawScore;
//    }
//
//    //REQUIRES: answers are integers from 1-5
//    //MODIFIES: this
//    //EFFECTS: turns question-answer data into raw scores
//    @Override
//    public void compileScores() {
//        //this will have to take in all of the raw scores also, so Percentile needs a rawscore field
//        // so it takes the rawScore
//        // then it orders the bank
//        // then it looks through the bank to see where your score is in the bank
//        // then it returns that number as a fraction of the size of the bank
//    }
//
//    //EFFECTS: prints out results of quiz (in percentiles)
//    @Override
//    public void getResults() {
//
//    }
//
//    //REQUIRES:
//    //MODIFIES:
//    //EFFECTS: returns corresponding percentiles for the raw scores it's been given
//    public ArrayList<Integer> rawScoresToPercentiles(ArrayList<RawScore> pastRawScores) {
//        //some sort of looping through of the past score arraylists until you get to the right one
//        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));
//        return a;
//    }
//
//    @Override
//    public void write(List<String> l, String wf) throws FileNotFoundException, UnsupportedEncodingException {
//
//    }
