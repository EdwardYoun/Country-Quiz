package edu.uga.cs.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name,
 * and country's continent.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class Quiz {

    //insert Question objects later. need to change Quiz, QuizzesData, and QuizzesDBHelper for this
    private long   id;
    private String date;
    private String result;
    private long   num;

    private Question question1;
    private Question question2;
    private Question question3;
    private Question question4;
    private Question question5;
    private Question question6;

    public Quiz()
    {
        this.id = -1;
        this.date = null;
        this.result = null;
        this.num = -1;
        this.question1 = null;
        this.question2 = null;
        this.question3 = null;
        this.question4 = null;
        this.question5 = null;
        this.question6 = null;
    }

    public Quiz( String date, String result, long num, Question[] questions) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.date = date;
        this.result = result;
        this.num = num;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result) { this.result = result; }

    public long getNum() { return num; }

    public void setNum(long num) { this.num = num; }

    public Question getQuestion1() { return question1; }

    public void setQuestion1(Question question1) { this.question1 = question1; }

    public Question getQuestion2() { return question2; }

    public void setQuestion2(Question question2) { this.question2 = question2; }

    public Question getQuestion3() { return question3; }

    public void setQuestion3(Question question3) { this.question3 = question3; }

    public Question getQuestion4() { return question4; }

    public void setQuestion4(Question question4) { this.question4 = question4; }

    public Question getQuestion5() { return question5; }

    public void setQuestion5(Question question5) { this.question5 = question5; }

    public Question getQuestion6() { return question6; }

    public void setQuestion6(Question question6) { this.question6 = question6; }


    public String toString()
    { return id + ": " + date + " " + result + " " + num + " " + question1 + " " + question2 + " " + question3 + " " + question4 + " " + question5 + " " + question6; }
}
