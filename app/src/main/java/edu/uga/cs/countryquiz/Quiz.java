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
    private int result;

    /**
     * default values
     */
    public Quiz()
    {
        this.id = -1;
        this.date = null;
        this.result = -1;
    }

    /**
     * constructor of Quiz object
     * @param date date of quiz
     * @param result result score at the end of the quiz
     */
    public Quiz( String date, int result) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.date = date;
        this.result = result;
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

    public int getResult()
    {
        return result;
    }

    public void setResult(int result) { this.result = result; }


    public String toString()
    { return id + ": " + date + " " + result; }
}
