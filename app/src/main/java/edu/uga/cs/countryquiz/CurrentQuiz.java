package edu.uga.cs.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name,
 * and country's continent.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class CurrentQuiz {

    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;
    private Question q5;
    private Question q6;
    private String date;
    private long curScore;
    private long ansQ;

    public CurrentQuiz() {
        this.q1 = null;
        this.q2 = null;
        this.q3 = null;
        this.q4 = null;
        this.q5 = null;
        this.q6 = null;
        this.date = null;
        this.curScore = -1;
        this.ansQ = -1;
    }

    public CurrentQuiz(Question q1, Question q2, Question q3, Question q4, Question q5, Question q6, String date, long curScore, long ansQ) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.date = date;
        this.curScore = curScore;
        this.ansQ = ansQ;
    }

    public Question getQ1() { return q1; }
    public void setQ1(Question q1) { this.q1 = q1; }
    public Question getQ2() { return q2; }
    public void setQ2(Question q2) { this.q2 = q2; }
    public Question getQ3() { return q3; }
    public void setQ3(Question q3) { this.q3 = q3; }
    public Question getQ4() { return q4; }
    public void setQ4(Question q4) { this.q4 = q4; }
    public Question getQ5() { return q5; }
    public void setQ5(Question q5) { this.q5 = q5; }
    public Question getQ6() { return q6; }
    public void setQ6(Question q6) { this.q6 = q6; }
    public String getDate() { return date; }
    public void setDate(String date) {this.date = date; }
    public long getCurScore() { return curScore; }
    public void setCurScore(long curScore) { this.curScore = curScore; }
    public long getAnsQ() { return ansQ; }
    public void setAnsQ(long ansQ) { this.ansQ = ansQ; }

    public String toString()
    {
        return q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5 + " " + q6 + " " + date + " " + curScore + " " + ansQ;
    }
}
