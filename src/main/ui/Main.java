package ui;

import model.Question;
import model.RawScore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Question q0 = new Question(0, "I am interested in abstract ideas", false);
        Question q1 = new Question(1, "I am always on time", false);
        Question q2 = new Question(2, "I am not the life of the party", true);
        Question q3 = new Question(3, "I like to please others", false);
        Question q4 = new Question(4, "I am fearful of the future", false);

        ArrayList<Question> bigFiveQuestions = new ArrayList<>(Arrays.asList(q0, q1, q2, q3, q4));

//        RawScore rawScoreForDatabase = new RawScore();
//        ArrayList<RawScore> pastRawScores = new ArrayList<>();
//        pastRawScores.add(rawScoreForDatabase);

//        Database bigFiveDatabase = new Database(bigFiveQuestions, pastRawScores);
        Quiz bigFiveQuiz = new Quiz(bigFiveQuestions, new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0)));
        bigFiveQuiz.run();
    }
}
// main declares a database for bigfive
// database makes all the questions, the raw scores, and the percentiles, so it calls questions and scores
// then it makes a quiz called bigfive
// bigfive's questions field is the questions field of database
// its answers field starts blank
// its pastScores field is the pastScores field of database
// then it runs bigfive
// that's in the quiz class
// bigfive.run starts with the setup method already made
// then when that gets completed it moves on to the actually runthequiz method
// the run the quiz method is asking a question, taking user input,
// adding that user input to the end of the answers arraylist
// when runthequiz finishes it runs a third method, compilescores
// compilescores is in the Score class
// it takes all the question categories and the answers and sorts them into 5 buckets, it also takes in pastScores
// then it sums the 5 buckets, these are the 5 rawscores
// now compilescores runs a method to figure out what these rawscores mean, getpercentiles, which is in database
// getpercentiles takes the raw scores, figures out where in the pastScores arraylist the raw score fits
// then it returns the percentile for that
// now compilescores has an array of 5 percentiles, one for each trait
// and now it just has to return each of those in print statements, "you got this for extraversion, this for..."
// ** later, compilescores can call a method addtodatabase that's in database
// addtodatabase takes its scores and put them in database, but for now just get this going
