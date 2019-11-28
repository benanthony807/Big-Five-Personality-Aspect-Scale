package model;

import java.io.IOException;
import java.util.*;

public class Percentile extends FileReader implements Score {

    private ArrayList<Integer> percentile;
    private ArrayList<Integer> allLines;
    private ArrayList<Integer> rawO;
    private ArrayList<Integer> rawC;
    private ArrayList<Integer> rawE;
    private ArrayList<Integer> rawA;
    private ArrayList<Integer> rawN;
    private HashMap<Integer, Integer> rawScore;

    public Percentile(HashMap<Integer, Integer> rawScore, String input) throws IOException {
        this.rawScore = rawScore;
        allLines = createAllLines(input);
        rawO = parseRawScores(0);
        rawC = parseRawScores(1);
        rawE = parseRawScores(2);
        rawA = parseRawScores(3);
        rawN = parseRawScores(4);
        percentile =  new ArrayList<>(Arrays.asList(0,0,0,0,0));
    }

    //getters
    public ArrayList<Integer> getAllLines() {
        return allLines;
    }

    public ArrayList<Integer> getRawO() {
        return rawO;
    }

    public ArrayList<Integer> getRawC() {
        return rawC;
    }

    public ArrayList<Integer> getRawE() {
        return rawE;
    }

    public ArrayList<Integer> getRawA() {
        return rawA;
    }

    public ArrayList<Integer> getRawN() {
        return rawN;
    }

    public HashMap<Integer, Integer> getRawScore() {
        return rawScore;
    }

    public ArrayList<Integer> getPercentile() {
        return percentile;
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores
    @Override
    public void compileScores() {
        ArrayList<ArrayList<Integer>> allRaws = new ArrayList<>(Arrays.asList(rawO, rawC, rawC, rawE, rawA, rawN));
        int count = 0;
        for (ArrayList<Integer> raw : allRaws) {
            for (int i = 0; i < raw.size(); i++) {
                if (raw.get(i) > rawScore.get(count)) {
                    percentile.set(count, i / raw.size() * 100);
                    break;
                }
            }
            if (percentile.get(count) == 0) {
                percentile.set(count, 100);
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


    ArrayList<Integer> createAllLines(String input) throws IOException {
        List<String> lines = read(input);
        ArrayList<Integer> allLines = new ArrayList<>();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            for (String part : partsOfLine) {
                allLines.add(Integer.parseInt(part));
            }
        }
        return allLines;
    }


    ArrayList<Integer> parseRawScores(int category) {
        ArrayList<Integer> parsed = new ArrayList<>();
        for (int i = category; i < allLines.size(); i += 5) {
            parsed.add(allLines.get(i));
        }
        Collections.sort(parsed);
        return parsed;
    }
}
