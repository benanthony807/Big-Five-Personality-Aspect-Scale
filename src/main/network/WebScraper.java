package network;

import model.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WebScraper {

    int category;
    boolean keyed;
    ArrayList<Question> questions = new ArrayList<Question>();

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: pulls questions from a website, returns a random list of Question with size elements
    public ArrayList<Question> createQuestionBank(int size) throws IOException {
        scrapeQuestions();
        cleanQuestions();
        return filterToSize(size);
    }

    private void scrapeQuestions() throws IOException {
//        taken from https://stackoverflow.com/questions/31360275/parsing-extracting-a-html-table-website-in-java
        org.jsoup.nodes.Document doc = Jsoup.connect("https://ipip.ori.org/newBigFive5broadKey.htm").get();
        org.jsoup.select.Elements rows = doc.select("tr");
        parseQuestion(rows);
    }

    private void parseQuestion(Elements rows) {
        for (int i = 2; i < rows.size(); i++) {
            Elements columns = rows.get(i).select("td");
            for (int j = 0; j < columns.size(); j++) {
                Element column = columns.get(j);
                if (categoryCheck(column)) {
                    i += 2;
                    break;
                }
                if (keyedCheck(column)) {
                    j += 1;
                    continue;
                }
                if (skippable(column)) {
                    continue;
                }
                questions.add(new Question(category, column.text(), keyed));
            }
        }
    }

    private boolean categoryCheck(Element column) {
        if (column.text().contains("Extraversion")) {
            category = 2;
            return true;
        } else if (column.text().contains("Agreeableness")) {
            category = 3;
            return true;
        } else if (column.text().contains("Conscientiousness")) {
            category = 1;
            return true;
        } else if (column.text().contains("Emotional Stability")) {
            category = 4;
            return true;
        } else if (column.text().contains("Intellect or Imagination")) {
            category = 0;
            return true;
        } else {
            return false;
        }

    }

    private boolean keyedCheck(Element column) {
        if (column.text().contains("+ keyed")) {
            keyed = false;
            return true;
        } else if (column.text().contains("– keyed")) {
            keyed = true;
            return true;
        } else {
            return false;
        }
    }

    private boolean skippable(Element column) {
        return (column.text().contains("Alpha"))
                ||
                (column.text().contains("&nbsp;"));

    }

    private void cleanQuestions() {
        // from https://www.geeksforgeeks.org/how-to-remove-duplicates-from-arraylist-in-java/
        ArrayList<Question> newList = new ArrayList<>();
        for (Question question : questions) {
            if (question.getQuestion().equals("")) {
                continue;
            } else if (!newList.contains(question)) {
                newList.add(question);
            }
        }
        questions = newList;
        flipNeuroticism();
    }

    private void flipNeuroticism() {
        for (Question question : questions) {
            if (question.getCategory() == 4) {
                if (question.getIsReverseCoded()) {
                    question.setIsReverseCoded(false);
                } else {
                    question.setIsReverseCoded(true);
                }
            }
        }
    }

    private ArrayList<Question> filterToSize(int size) {
        ArrayList<Question> newList = new ArrayList<>();
        while (newList.size() < size) {
//            random functionality taken from https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
            Random rand = new Random();
            Question question = questions.get(rand.nextInt(questions.size()));
            if (!newList.contains(question)) {
                newList.add(question);
            }
        }
        return newList;
    }
}


