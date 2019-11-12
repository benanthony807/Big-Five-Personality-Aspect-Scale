package observer;

import java.util.Observable;

public class QuizTaker implements java.util.Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("The quiz is completed, the results have been compiled.");
    }
}
