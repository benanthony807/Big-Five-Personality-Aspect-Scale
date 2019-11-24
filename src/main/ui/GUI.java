package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class GUI extends JFrame implements ActionListener {
    // based off of code from https://www.codespeedy.com/quiz-game-using-java-swing-gui/
    //                    and https://stackoverflow.com/questions/29029277/quiz-game-with-multiple-choice-in-java-gui
    Container startPage;
    Container questionPage;
    Container resultsPage;
    Quiz quiz;
    JFrame fr;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JLabel lb1;
//    ButtonGroup bg;
    int count = 0;

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

    void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        navigateTo(startPage);
    }

    Container createStartPage() {
        JPanel result = new JPanel(null);
        result.setSize(1280, 800);
        JLabel lb1 = new JLabel("Are you ready to start the quiz?");
        lb1.setBounds(400, 100, 600, 80);
        JButton b1 = new JButton("Start");
        b1.setBounds(400, 650, 414, 60);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateTo(questionPage);
            }
        });
        result.add(b1);
        result.add(lb1);
        return result;
    }

    Container createQuestionPage() {
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

    Container createResultsPage() {
        JPanel result = new JPanel(null);
        result.setSize(1280, 800);

        JButton b1 = new JButton("Exit");
        b1.setBounds(80, 580, 526, 82);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        result.add(b1);
        return result;
    }

    private void makeButtonsActionable() {
//        bg = new ButtonGroup();
//        bg.add(rb1);
//        bg.add(rb2);
//        bg.add(rb3);
//        bg.add(rb4);
//        bg.add(rb5);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
    }

    void navigateTo(Container page) {
        setContentPane(page);
        setSize(page.getSize());
    }

    private void makeLabel() {
        lb1 = new JLabel(quiz.getQuestions().get(0).getQuestion());
        lb1.setBounds(200, 50, 600, 30);
        fr.add(lb1);
        lb1.setFont(new Font("chiller", Font.BOLD, 20));
    }

    private void makeLabel(Container result) {
        lb1 = new JLabel(quiz.getQuestions().get(0).getQuestion());
        lb1.setBounds(200, 50, 600, 30);
        result.add(lb1);
        lb1.setFont(new Font("chiller", Font.BOLD, 20));
    }

    private void makeButtons() {
        b1 = new JButton("Strongly disagree");
        b1.setBounds(100, 100, 200, 30);
        fr.add(b1);
        b2 = new JButton("Disagree");
        b2.setBounds(300, 100, 100, 30);
        fr.add(b2);
        b3 = new JButton("Neutral");
        b3.setBounds(400, 100, 100, 30);
        fr.add(b3);
        b4 = new JButton("Agree");
        b4.setBounds(500, 100, 100, 30);
        fr.add(b4);
        b5 = new JButton("Strongly agree");
        b5.setBounds(600, 100, 200, 30);
        fr.add(b5);
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

    private void makeFrame() {
        fr = new JFrame();
        fr.setLayout(null);
        fr.setSize(800, 800);
        Container c = fr.getContentPane();
        c.setBackground(Color.white);
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
