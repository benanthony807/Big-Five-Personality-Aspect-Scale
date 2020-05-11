package ui;

import model.Percentile;
import model.Question;
import model.Quiz;
import model.RawScore;
import network.WebScraper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

class StaticGUI extends JFrame {
    // based off of code from https://www.codespeedy.com/quiz-game-using-java-swing-gui/
    //                    and https://stackoverflow.com/questions/29029277/quiz-game-with-multiple-choice-in-java-gui

    private static final int SIZE = 30;

    private static Quiz quiz;

    static {
        try {
            quiz = createQuiz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StaticGUI instance;
    private static Container startPage = createStartPage();
    private static Container questionPage = createQuestionPage();
    private static Container instructionsPage = createInstructionsPage();
    private static Percentile percentile;

    private static JButton b1;
    private static JButton b2;
    private static JButton b3;
    private static JButton b4;
    private static JButton b5;
    private static JLabel lb1;
    private static JLabel lb2;
    private static int count = 0;

    //EFFECTS: builds and runs a big five quiz GUI
    static void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        instance = new StaticGUI();
        instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        instance.setTitle("Big Five Personality Aspect Scale");
        instance.setVisible(true);
        navigateTo(startPage);
    }

    private static void navigateTo(Container page) {
        instance.setContentPane(page);
        instance.setSize(page.getSize());
    }

    private static Quiz createQuiz() throws IOException {
//        Question q1 = new Question(1, "I am always on time", false);
//        Question q2 = new Question(2, "I am not the life of the party", true);
//        Question q3 = new Question(3, "I like to please others", false);
//        Question q4 = new Question(4, "I am fearful of the future", false);
//        ArrayList<Question> bigFiveQuestions = new ArrayList<>(Arrays.asList(q1, q2, q3, q4));
        WebScraper webScraper = new WebScraper();
        ArrayList<Question> bigFiveQuestions = webScraper.createQuestionBank(SIZE);
        return new Quiz(bigFiveQuestions);
    }

    private static Container createStartPage() {
        JPanel result = new JPanel(null);
        result.setSize(900, 600);

        JLabel lb2 = new JLabel("Big Five Personality Aspect Scale");
        lb2.setBounds(40, 30, 600, 80);
        lb2.setFont(new Font("Helvetica", Font.BOLD, 20));

        //image adding taken from https://stackoverflow.com/questions/3775373/java-how-to-add-image-to-jlabel
        ImageIcon img1 = new ImageIcon("./data/BigFiveImage2.png");
        JLabel imgLabel1 = new JLabel(img1);
        imgLabel1.setBounds(240, 20, 450, 400);

        JButton b1 = new JButton("Start");
        b1.setBounds(280, 450, 350, 60);
        b1.addActionListener(e -> navigateTo(instructionsPage));

        result.add(b1);
        result.add(lb2);
        result.add(imgLabel1);
        return result;
    }

    private static Container createQuestionPage() {
        JPanel result = new JPanel(null);
        result.setSize(900, 600);
        makeQuestionButtons(result);
        makeQuestionLabel(result);
        makeQuestionButtonsActionable(e -> {
            try {
                questionPageActionPerformed(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return result;
    }

    private static void questionPageActionPerformed(ActionEvent e) throws IOException {
        setAnswer(e);
        ++count;
        if (quiz.getQuestions().size() > count) {
            lb1.setText(quiz.getQuestions().get(count).getQuestion());
            lb2.setText((count + 1) + "/" + SIZE);
        } else {
            RawScore rawScore = new RawScore(quiz);
            rawScore.compileScores();
            try {
                rawScore.write(rawScore.read("./data/rawscorebank.txt"), "./data/rawscorebank.txt");
            } catch (IOException ee) {
                System.out.println("Scores could not be stored");
            }
            percentile = new Percentile(rawScore.getRawScore(), "./data/rawscorebank.txt");
            percentile.compileScores();
            Container resultsPage = createResultsPage();
            navigateTo(resultsPage);
        }
    }

    private static void makeQuestionButtons(Container result) {
        b1 = new JButton("Strongly disagree");
        b1.setBounds(100, 400, 200, 30);
        result.add(b1);
        b2 = new JButton("Disagree");
        b2.setBounds(300, 400, 100, 30);
        result.add(b2);
        b3 = new JButton("Neutral");
        b3.setBounds(400, 400, 100, 30);
        result.add(b3);
        b4 = new JButton("Agree");
        b4.setBounds(500, 400, 100, 30);
        result.add(b4);
        b5 = new JButton("Strongly agree");
        b5.setBounds(600, 400, 200, 30);
        result.add(b5);
    }

    private static void makeQuestionButtonsActionable(ActionListener al) {
        b1.addActionListener(al);
        b2.addActionListener(al);
        b3.addActionListener(al);
        b4.addActionListener(al);
        b5.addActionListener(al);
    }

    private static void makeQuestionLabel(Container result) {
        lb1 = new JLabel(quiz.getQuestions().get(0).getQuestion());
        lb1.setBounds(200, 200, 700, 40);
        result.add(lb1);
        lb1.setFont(new Font("Helvetica", Font.PLAIN, 30));
        lb2 = new JLabel((count + 1) + "/" + SIZE);
        lb2.setBounds(750, 20, 100, 30);
        result.add(lb2);
    }

    private static Container createInstructionsPage() {
        JPanel result = new JPanel(null);
        result.setSize(900, 600);
        JLabel lb2 = new JLabel("Rate how strongly you agree with the following statements");
        lb2.setFont(new Font("Helvetica", Font.PLAIN, 25));
        lb2.setBounds(130,200,700,30);
        result.add(lb2);

        JButton b1 = new JButton("Continue");
        b1.setBounds(280, 450, 350, 60);
        b1.addActionListener(e -> navigateTo(questionPage));
        result.add(b1);
        return result;
    }

    private static Container createResultsPage() {
        JPanel result = new JPanel(null);
        result.setSize(900, 600);
        createResultsLabel(result);
        createExitButton(result);
        return result;
    }

    private static void createExitButton(JPanel result) {
        JButton b1 = new JButton("Exit");
        b1.setBounds(250, 400, 414, 60);
        b1.addActionListener(e -> System.exit(0));
        result.add(b1);
    }

    private static void createResultsLabel(Container result) {
        JLabel t1 = new JLabel("Your results (in percentiles):");
        JLabel t2 = new JLabel("Openness: " + percentile.getPercentile().get(0));
        JLabel t3 = new JLabel("Conscientiousness: " + percentile.getPercentile().get(1));
        JLabel t4 = new JLabel("Extroversion: " + percentile.getPercentile().get(2));
        JLabel t5 = new JLabel("Agreeableness: " + percentile.getPercentile().get(3));
        JLabel t6 = new JLabel("Neuroticism: " + percentile.getPercentile().get(4));

        t1.setBounds(350, 50, 300, 30);
        t2.setBounds(350, 100, 300, 30);
        t3.setBounds(350, 120, 300, 30);
        t4.setBounds(350, 140, 300, 30);
        t5.setBounds(350, 160, 300, 30);
        t6.setBounds(350, 180, 300, 30);

        result.add(t1);
        result.add(t2);
        result.add(t3);
        result.add(t4);
        result.add(t5);
        result.add(t6);
    }

    private static void setAnswer(ActionEvent e) {
        if (e.getSource() == b1) {
            quiz.getAnswers().set(count, 1);
        } else if (e.getSource() == b2) {
            quiz.getAnswers().set(count, 2);
        } else if (e.getSource() == b3) {
            quiz.getAnswers().set(count, 3);
        } else if (e.getSource() == b4) {
            quiz.getAnswers().set(count, 4);
        } else {
            quiz.getAnswers().set(count, 5);
        }
    }
}