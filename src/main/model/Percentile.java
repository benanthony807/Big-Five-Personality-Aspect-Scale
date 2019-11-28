package model;

import java.io.IOException;
import java.util.*;

public class Percentile extends FileReaderWriter implements Score {

    private ArrayList<Integer> percentile = new ArrayList<>();
    private ArrayList<Integer> allLines;
    private ArrayList<Integer> rawO;
    private ArrayList<Integer> rawC;
    private ArrayList<Integer> rawE;
    private ArrayList<Integer> rawA;
    private ArrayList<Integer> rawN;
    private HashMap<Integer, Integer> rawScore;

    public Percentile(HashMap<Integer, Integer> rawScore) throws IOException {
        this.rawScore = rawScore;
        allLines = createAllLines();
        rawO = parseRawScores(0);
        rawC = parseRawScores(1);
        rawE = parseRawScores(2);
        rawA = parseRawScores(3);
        rawN = parseRawScores(4);
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores
    @Override
    public void compileScores() {
        ArrayList<ArrayList<Integer>> allRaws = new ArrayList<>(Arrays.asList(rawO, rawC, rawC, rawE, rawA, rawN));
        int count = 0;
        for (ArrayList<Integer> raw: allRaws) {
            for (int i = 0; i < raw.size(); i++) {
                if (rawO.get(i) > rawScore.get(count)) {
                    percentile.add(i / raw.size() * 100);
                    break;
                }
            }
            count++;
        }
    }

    //EFFECTS: prints out results of quiz (in percentiles)
    @Override
    public void getResults() {
        System.out.println("Your results (in percentiles):");
        System.out.println("Openness: " + percentile.get(0));
        System.out.println("Conscientiousness: " + percentile.get(1));
        System.out.println("Extroversion: " + percentile.get(2));
        System.out.println("Agreeableness: " + percentile.get(3));
        System.out.println("Neuroticism: " + percentile.get(4));
    }


    private ArrayList<Integer> createAllLines() throws IOException {
        List<String> asString = read("./data/rawscorebank.txt");
        ArrayList<Integer> allLines = new ArrayList<>();
        for (String s : asString) {
            allLines.add(Integer.parseInt(s));
        }
        return allLines;
    }

    private ArrayList<Integer> parseRawScores(int category) {
        ArrayList<Integer> parsed = new ArrayList<>();
        for (int i = category; i < allLines.size(); i += 5) {
            parsed.add(allLines.get(i));
        }
        Collections.sort(parsed);
        return parsed;
    }
}
