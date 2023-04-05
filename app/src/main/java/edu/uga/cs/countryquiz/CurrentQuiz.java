package edu.uga.cs.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name,
 * and country's continent.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 *
 * CurrentQuiz Object class that allows quiz objects to be created and stored.
 * Then later returned when see results is clicked.
 */
public class CurrentQuiz {

    //array of quiz questions
    private Question[] quests;
    //reference of array position
    private int curQuest;
    //date of quiz
    private String date;
    //current score in the quiz so far
    private int curScore;

    /**
     * default values of CurrentQuiz object
     */
    public CurrentQuiz() {
        this.quests = new Question[6];
        this.curQuest = 0;
        this.date = null;
        this.curScore = 0;
    }

    /**
     * Adds questions to the array.
     * @param question question used for quiz
     */
    public void addQuest(Question question){
        if (curQuest < 6) {
            quests[curQuest] = question;
            curQuest++;
        }
    }

    /**
     * Returns question at curQuest index of question array.
     * @return question in position or nothing if there is no question in the array position
     */
    public Question getCurQuest() {
        if(curQuest < 6) {
            return quests[curQuest];
        } else {
            return null;
        }
    }


    public void setCurScore(int curScore) { this.curScore = curScore; }

    /**
     * Returns current score.
     * @return the current score of the quiz
     */
    public int getCurScore() {
        return curScore;
    }

    /**
     * constructor of CurrentQuiz
     * @param quests questions for quiz
     * @param curQuest current question position in array
     * @param date date of quiz
     * @param curScore current score of quiz
     */
    public CurrentQuiz(Question[] quests, int curQuest, String date, int curScore) {
        this.quests = quests;
        this.curQuest = curQuest;
        this.date = date;
        this.curScore = curScore;
    }

    // Return date quiz was taken.
    public String getDate() { return date; }
    public void setDate(String date) {this.date = date; }

    public String toString()
    {
        return quests + " " + curQuest + " " + date + " " + curScore;
    }
}
