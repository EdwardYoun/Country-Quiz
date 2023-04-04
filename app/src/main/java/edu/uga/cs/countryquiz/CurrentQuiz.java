package edu.uga.cs.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name,
 * and country's continent.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class CurrentQuiz {

    private Question[] quests;
    private int curQuest;

    private String date;
    private long curScore;
    private long ansQ;

    public CurrentQuiz() {
        this.quests = new Question[6];
        this.curQuest = 0;
        this.date = null;
        this.curScore = -1;
        this.ansQ = -1;
    }

    public void addQuest(Question question){
        if (curQuest < 6) {
            quests[curQuest] = question;
            curQuest++;
        }
    }

    public Question getCurQuest() {
        if(curQuest < 6) {
            return quests[curQuest];
        } else {
            return null;
        }
    }

    public void setCurScore() {
        if(getCurQuest().getRightAnswer() == ansQ) {
            curScore++;
        }
    }

    public long getCurScore() {
        return curScore;
    }

    public boolean finishQuiz() {
        return curQuest >= 6;
    }

    public void reset() {
        this.quests = new Question[6];
        this.curQuest = 0;
        this.ansQ = 0;
        this.curScore = 0;
    }

    public CurrentQuiz(Question[] quests, int curQuest, String date, long curScore, long ansQ) {
        this.quests = quests;
        this.curQuest = curQuest;
        this.date = date;
        this.curScore = curScore;
        this.ansQ = ansQ;
    }

    public String getDate() { return date; }
    public void setDate(String date) {this.date = date; }
    public long getAnsQ() { return ansQ; }
    public void setAnsQ(long ansQ) { this.ansQ = ansQ; }

    public String toString()
    {
        return date + " " + curScore + " " + ansQ;
    }
}
