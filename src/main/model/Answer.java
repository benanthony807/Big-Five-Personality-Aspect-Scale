package model;

import java.util.Objects;

public class Answer {

    private Question question;
    private int answer;

    public Answer(Question q) {
        this.answer = 0;
        this.question = q;
    }

    public void setQuestion(Question q) {
        question = q;
        if (!question.getAnswer().equals(this)) {
            q.setAnswer(answer);
        }
    }

    public void setAnswer(int answer) {
        this.answer = answer;
        if (!question.getAnswer().equals(this)) {
            question.setAnswer(answer);
        }
    }

    public Question getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer1 = (Answer) o;
        return answer == answer1.answer
                &&
                Objects.equals(question, answer1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}

