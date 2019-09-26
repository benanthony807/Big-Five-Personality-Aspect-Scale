package model;

public class Question {

    private int category;  // 0 = openness, 1= conscientiousness, 2 = extroversion, 3 = agreeableness, 4 = neuroticism
    private String question;

    public Question(int category, String question) {
        this.category = category;
        this.question = question;
    }

    //getters:
    public int getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    //setters:
    public void setCategory(int category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
