package model;

import java.util.Objects;

public class Question {

    private int category;  // 0 = openness, 1= conscientiousness, 2 = extroversion, 3 = agreeableness, 4 = neuroticism
    private String question;
    private Boolean isReverseCoded;

    //EFFECTS: creates a Question with category, question, and isReverseCoded set to true if the question is reverse
    //         coded, false ow
    public Question(int category, String question, Boolean isReverseCoded) {
        this.category = category;
        this.question = question;
        this.isReverseCoded = isReverseCoded;

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


    //setters:
    public void setCategory(int category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setIsReverseCoded(Boolean reverseCoded) {
        isReverseCoded = reverseCoded;
    }

}