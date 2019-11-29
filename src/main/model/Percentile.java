package model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Percentile extends FileReader implements Score {

    private ArrayList<Double> percentile;
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
        percentile =  new ArrayList<>(Arrays.asList(null,null,null,null,null));
    }

    //getters
    ArrayList<Integer> getAllLines() {
        return allLines;
    }

    ArrayList<Integer> getRawO() {
        return rawO;
    }

    ArrayList<Integer> getRawC() {
        return rawC;
    }

    ArrayList<Integer> getRawE() {
        return rawE;
    }

    ArrayList<Integer> getRawA() {
        return rawA;
    }

    ArrayList<Integer> getRawN() {
        return rawN;
    }

    HashMap<Integer, Integer> getRawScore() {
        return rawScore;
    }

    public ArrayList<Double> getPercentile() {
        return percentile;
    }

    //REQUIRES: answers are integers from 1-5
    //MODIFIES: this
    //EFFECTS: turns question-answer data into raw scores
    @Override
    public void compileScores() {
        ArrayList<ArrayList<Integer>> allRaws = new ArrayList<>(Arrays.asList(rawO, rawC, rawE, rawA, rawN));
        // double formatting taken from https://stackoverflow.com/questions/8137218/trim-double-to-2-decimal-places
        DecimalFormat df = new DecimalFormat("#.##");
        int count = 0;
        for (ArrayList<Integer> raw : allRaws) {
            for (int i = 0; i < raw.size(); i++) {
                if (raw.get(i) > rawScore.get(count)) {
                    percentile.set(count, Double.parseDouble(df.format(((double) i / (double) raw.size() * 100))));
                    break;
                }
            }
            if (percentile.get(count) == null) {
                percentile.set(count, 99.99);
            } else if (percentile.get(count) == 0.0) {
                percentile.set(count, 1.00);
            }
            count++;
        }
    }

    //EFFECTS: prints out results of quiz (in percentiles)
    @Override
    public void getResults() {
        System.out.println("Your results (in percentiles):");
        System.out.println("Openness: " + (percentile.get(0)));
        System.out.println("Conscientiousness: " + (percentile.get(1)));
        System.out.println("Extroversion: " + (percentile.get(2)));
        System.out.println("Agreeableness: " + (percentile.get(3)));
        System.out.println("Neuroticism: " + (percentile.get(4)));
    }

    //EFFECTS: loads past raw scores from input, returns them as an ArrayList<Integer>
    ArrayList<Integer> createAllLines(String input) throws IOException {
        List<String> lines = read(input);
        ArrayList<Integer> allLines = new ArrayList<>();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnTab(line);
            for (String part : partsOfLine) {
                allLines.add(Integer.parseInt(part));
            }
        }
        return allLines;
    }

    //EFFECTS: filters out all elements of allLines not in category
    ArrayList<Integer> parseRawScores(int category) {
        ArrayList<Integer> parsed = new ArrayList<>();
        for (int i = category; i < allLines.size(); i += 5) {
            parsed.add(allLines.get(i));
        }
        Collections.sort(parsed);
        return parsed;
    }
}
