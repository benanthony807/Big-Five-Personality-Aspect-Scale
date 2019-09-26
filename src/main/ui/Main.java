package ui;

import model.Database;
import model.Question;
import model.Quiz;
import model.Score;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Question q1 = new Question(3, "I am the life of the party");
        Question q2 = new Question(4, "I like to please others");
        Question q3 = new Question(1, "I am fearful of the future");

        ArrayList<Question> bigFiveQuestions = new ArrayList<>();
        bigFiveQuestions.add(q1);
        bigFiveQuestions.add(q2);
        bigFiveQuestions.add(q3);


        Score scoreForDatabase = new Score();
        ArrayList<Score> pastScores = new ArrayList<>();
        pastScores.add(scoreForDatabase);

        Database bigFiveDatabase = new Database(bigFiveQuestions, pastScores);
        Quiz bigFiveQuiz = new Quiz(bigFiveDatabase.getOptions(), bigFiveDatabase.getPastScores());
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
