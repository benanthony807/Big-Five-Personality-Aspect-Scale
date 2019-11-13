package model;

import java.util.Objects;

public class Question {

    private int category;  // 0 = openness, 1= conscientiousness, 2 = extroversion, 3 = agreeableness, 4 = neuroticism
    private String question;
    private Boolean isReverseCoded;
    private Answer answer;

    public Question(int category, String question, Boolean isReverseCoded) {
        this.category = category;
        this.question = question;
        this.isReverseCoded = isReverseCoded;
//        this.answer = new Answer(this);
    }

    //getters:
    public int getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public Boolean getIsReverseCoded() {
        return isReverseCoded;
    }

    public Answer getAnswer() {
        return answer;
    }

    //setters:
    public void setCategory(int category) {
        this.category = category;
        if (!answer.getQuestion().equals(this)) {
            answer.setQuestion(this);
        }
    }

    public void setQuestion(String question) {
        this.question = question;
        if (!answer.getQuestion().equals(this)) {
            answer.setQuestion(this);
        }
    }

    public void setIsReverseCoded(Boolean reverseCoded) {
        isReverseCoded = reverseCoded;
        if (!answer.getQuestion().equals(this)) {
            answer.setQuestion(this);
        }
    }


    public void setAnswer(int a) {
        answer.setAnswer(a);
        if (!answer.getQuestion().equals(this)) {
            answer.setQuestion(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question1 = (Question) o;
        return category == question1.category
                &&
                Objects.equals(question, question1.question)
                &&
                Objects.equals(isReverseCoded, question1.isReverseCoded)
                &&
                Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, question, isReverseCoded, answer);
    }
}
