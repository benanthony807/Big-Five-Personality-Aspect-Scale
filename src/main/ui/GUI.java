package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class GUI extends JFrame implements ActionListener {
    // based off of code from https://www.codespeedy.com/quiz-game-using-java-swing-gui/
    //                    and https://stackoverflow.com/questions/29029277/quiz-game-with-multiple-choice-in-java-gui
    private Container startPage;
    private Container questionPage;
    private Container resultsPage;
    private Quiz quiz;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JLabel lb1;
    private int count = 0;

    GUI(Quiz quiz) {
        this.quiz = quiz;
        run();
        startPage = createStartPage();
        questionPage = createQuestionPage();
        resultsPage = createResultsPage();
//        makeFrame();
//        makeLabel();
//        makeButtons();
//        makeButtonsActionable();
//        fr.setVisible(true);
    }

    private void run() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.navigateTo(startPage);
    }

    private Container createStartPage() {
        JPanel result = new JPanel(null);
        result.setSize(1280, 800);
        JLabel lb1 = new JLabel("Are you ready to start the quiz?");
        lb1.setBounds(400, 100, 600, 80);
        JButton b1 = new JButton("Start");
        b1.setBounds(400, 650, 414, 60);
        b1.addActionListener(e -> navigateTo(questionPage));
        result.add(b1);
        result.add(lb1);
        return result;
    }

    private Container createQuestionPage() {
        JPanel result = new JPanel(null);
        result.setSize(1280, 800);
        makeButtons(result);
        makeLabel(result);
        ActionListener actionListener = e -> {
            setAnswer(e);
            ++count;
            if (quiz.getQuestions().size() > count) {
                lb1.setText(quiz.getQuestions().get(count).getQuestion());
            } else {
                navigateTo(resultsPage);
            }
        };
        makeButtonsActionable(actionListener);
        return result;
    }

    private Container createResultsPage() {
        JPanel result = new JPanel(null);
        result.setSize(1280, 800);

        JButton b1 = new JButton("Exit");
        b1.setBounds(80, 580, 526, 82);
        b1.addActionListener(e -> System.exit(0));
        result.add(b1);
        return result;
    }

    private void navigateTo(Container page) {
        this.setContentPane(page);
        this.setSize(page.getSize());
    }

    private void makeLabel(Container result) {
        lb1 = new JLabel(quiz.getQuestions().get(0).getQuestion());
        lb1.setBounds(200, 50, 600, 30);
        result.add(lb1);
        lb1.setFont(new Font("chiller", Font.BOLD, 20));
    }

    private void makeButtons(Container result) {
        b1 = new JButton("Strongly disagree");
        b1.setBounds(100, 100, 200, 30);
        result.add(b1);
        b2 = new JButton("Disagree");
        b2.setBounds(300, 100, 100, 30);
        result.add(b2);
        b3 = new JButton("Neutral");
        b3.setBounds(400, 100, 100, 30);
        result.add(b3);
        b4 = new JButton("Agree");
        b4.setBounds(500, 100, 100, 30);
        result.add(b4);
        b5 = new JButton("Strongly agree");
        b5.setBounds(600, 100, 200, 30);
        result.add(b5);
    }

    private void makeButtonsActionable(ActionListener al) {
        b1.addActionListener(al);
        b2.addActionListener(al);
        b3.addActionListener(al);
        b4.addActionListener(al);
        b5.addActionListener(al);
    }

    public void actionPerformed(ActionEvent e) {
        setAnswer(e);
        ++count;
        if (quiz.getQuestions().size() > count) {
            lb1.setText(quiz.getQuestions().get(count).getQuestion());
        } else {
            navigateTo(resultsPage);
        }
    }

    private void setAnswer(ActionEvent e) {
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
