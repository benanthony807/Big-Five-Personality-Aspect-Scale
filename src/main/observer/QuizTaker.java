package observer;

import java.util.HashMap;
import java.util.Observable;

public class QuizTaker implements java.util.Observer {

    //EFFECTS: Prints "nice job quiz done"
    @Override
    public void update(Observable o, Object arg) {
        HashMap<Integer, Integer> rawScore = (HashMap<Integer, Integer>) arg;
        System.out.println("Your results (in raw scores):");
        System.out.println("Openness: " + rawScore.get(0));
        System.out.println("Conscientiousness: " + rawScore.get(1));
        System.out.println("Extroversion: " + rawScore.get(2));
        System.out.println("Agreeableness: " + rawScore.get(3));
        System.out.println("Neuroticism: " + rawScore.get(4));
    }
}
