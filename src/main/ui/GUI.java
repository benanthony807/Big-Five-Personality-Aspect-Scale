package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class GUI implements ActionListener {
    // based off of code from https://www.codespeedy.com/quiz-game-using-java-swing-gui/
    //                    and https://stackoverflow.com/questions/29029277/quiz-game-with-multiple-choice-in-java-gui
    Quiz quiz;
    JFrame fr;
    JButton rb1;
    JButton rb2;
    JButton rb3;
    JButton rb4;
    JButton rb5;
    JLabel lb1;
    ButtonGroup bg;
    String op1 = "Strongly disagree";
    String op2 = "Disagree";
    String op3 = "Neutral";
    String op4 = "Agree";
    String op5 = "Strongly agree";
    int count = 0;

//    public static void main(String s[]) {
//        new GUI();
//    }

    GUI(Quiz quiz) {
        this.quiz = quiz;
        makeFrame();
        makeLabel();
        makeButtons();
        makeButtonsActionable();
        fr.setVisible(true);
    }

    private void makeButtonsActionable() {
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        bg.add(rb5);
        rb1.addActionListener(this);
        rb2.addActionListener(this);
        rb3.addActionListener(this);
        rb4.addActionListener(this);
        rb5.addActionListener(this);
    }

    private void makeLabel() {
        lb1 = new JLabel(quiz.getQuestions().get(0).getQuestion());
        lb1.setBounds(200, 50, 600, 30);
        fr.add(lb1);
        lb1.setFont(new Font("chiller", Font.BOLD, 20));
    }

    private void makeButtons() {
        rb1 = new JButton(op1);
        rb1.setBounds(100, 100, 200, 30);
        fr.add(rb1);
        rb2 = new JButton(op2);
        rb2.setBounds(300, 100, 100, 30);
        fr.add(rb2);
        rb3 = new JButton(op3);
        rb3.setBounds(400, 100, 100, 30);
        fr.add(rb3);
        rb4 = new JButton(op4);
        rb4.setBounds(500, 100, 100, 30);
        fr.add(rb4);
        rb5 = new JButton(op5);
        rb5.setBounds(600, 100, 200, 30);
        fr.add(rb5);
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
            // todo: go to final screen
        }
    }

    private void setAnswer(ActionEvent e) {
        if (e.getSource() == rb1) {
            quiz.getAnswers().set(count, 1);
        } else if (e.getSource() == rb2) {
            quiz.getAnswers().set(count, 2);
        } else if (e.getSource() == rb3) {
            quiz.getAnswers().set(count, 3);
        } else if (e.getSource() == rb4) {
            quiz.getAnswers().set(count, 4);
        } else {
            quiz.getAnswers().set(count, 5);
        }
    }
}

//
//            if (rb1.isSelected())
//                en = rb1.getText();
//            if (rb2.isSelected())
//                en = rb2.getText();
//            if (rb3.isSelected())
//                en = rb3.getText();
//            if (rb4.isSelected())
//                en = rb4.getText();
////            if (en.equals(ans[cn]))
////                JOptionPane.showMessageDialog(null, "Right Answer");
////            else
////                JOptionPane.showMessageDialog(null, "Wrong Answer");
//        }
//        if (e.getSource() == b2) {
//            cn++;
//            lb1.setText(ques[cn]);
////            rb1.setText(op1[cn]);
////            rb2.setText(op2[cn]);
////            rb3.setText(op3[cn]);
////            rb4.setText(op4[cn]);
//        }
//    }
//}