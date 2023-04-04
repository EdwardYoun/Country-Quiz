package edu.uga.cs.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name,
 * and country's continent.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class CurrentQuiz {

    private Question[] quests;
    //reference of array position, not current question otherwise it would be 1, not 0
    private int curQuest;
    private String date;
    private long curScore;
    //questions answered
    private long ansQ;
    //user's answer
    private long userAns;

    public CurrentQuiz() {
        this.quests = new Question[6];
        this.curQuest = 0;
        this.date = null;
        this.curScore = 0;
        this.ansQ = 0;
        this.userAns = 0;
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
        if(getCurQuest().getRightAnswer() == userAns) {
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
        this.userAns = 0;
    }

    public CurrentQuiz(Question[] quests, int curQuest, String date, long curScore, long ansQ, long userAns) {
        this.quests = quests;
        this.curQuest = curQuest;
        this.date = date;
        this.curScore = curScore;
        this.ansQ = ansQ;
        this.userAns = userAns;
    }

    public String getDate() { return date; }
    public void setDate(String date) {this.date = date; }
    public long getAnsQ() { return ansQ; }
    public void setAnsQ(long ansQ) { this.ansQ = ansQ; }
    public long getUserAns() { return userAns; }
    public void setUserAns(long userAns) { this.userAns = userAns; }

    public String toString()
    {
        return quests + " " + curQuest + " " + date + " " + curScore + " " + ansQ + " " + userAns;
    }
}
