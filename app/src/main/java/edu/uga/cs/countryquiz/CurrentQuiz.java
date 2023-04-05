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
    private String date;
    private int curScore;

    public CurrentQuiz() {
        this.quests = new Question[6];
        this.curQuest = 0;
        this.date = null;
        this.curScore = 0;
    }

    /**
     * Adds questions to the array.
     * @param question
     */
    public void addQuest(Question question){
        if (curQuest < 6) {
            quests[curQuest] = question;
            curQuest++;
        }
    }

    /**
     * Returns question at curQuest index of question array.
     * @return
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
     * @return
     */
    public int getCurScore() {
        return curScore;
    }

    // Checks state of quiz.
    public boolean finishQuiz() {
        return curQuest >= 6;
    }

    //Resets quiz to inital state.
    public void reset() {
        this.quests = new Question[6];
        this.curQuest = 0;
        this.date = null;
        this.curScore = 0;
    }

    //CurrentQuiz object.
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
