package model;

import java.util.ArrayList;

public class Database {

    private ArrayList<Question> options;
    private ArrayList<Score> pastScores;

    public Database(ArrayList<Question> options, ArrayList<Score> pastScores) {
        this.options = options;
        this.pastScores = pastScores;
    }

    public ArrayList<Question> getOptions() {
        return this.options;
    }

    public ArrayList<Score> getPastScores() {
        return pastScores;
    }
}
