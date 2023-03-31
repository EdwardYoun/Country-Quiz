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

    public Quiz()
    {
        this.id = -1;
        this.date = null;
        this.result = null;
        this.num = num;
    }

    public Quiz( String date, String result, long num) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.date = date;
        this.result = result;
        this.num = num;
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

    public String toString()
    {
        return id + ": " + date + " " + result + " " + num;
    }
}
