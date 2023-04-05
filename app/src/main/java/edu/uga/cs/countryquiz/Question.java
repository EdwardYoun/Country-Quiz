package edu.uga.cs.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name,
 * and country's continent.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class Question {

    private String country;
    private String continent;
    //wrong answer 1
    private String wrong1;
    //wrong answer 2
    private String wrong2;

    //which of the three options is correct
    private int correct;
    //if the question got a point or not
    private int point;


    /**
     * default values
     */
    public Question()
    {
        this.country = null;
        this.continent = null;
        this.wrong1 = null;
        this.wrong2 = null;
        this.correct = -1;
        this.point = -1;
    }

    /**
     * constructor of Question object
     * @param country name of country
     * @param continent continent of country
     * @param wrong1 incorrect answer
     * @param wrong2 incorrect answer
     * @param correct which radio button is correct
     * @param point score on question
     */
    public Question( String country, String continent, String wrong1, String wrong2, int correct, int point) {
        this.country = country;
        this.continent = continent;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.correct = correct;
        this.point = point;
    }

    public String getCountry()
    {
        return country;}

    public void setCountry(String country) {
        this.country = country; }

    public String getContinent()
    {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent; }

    public String getWrong1()
    {
        return wrong1;
    }

    public void setWrong1(String wrong1) { this.wrong1 = wrong1; }

    public String getWrong2()
    {
        return wrong2;
    }

    public void setWrong2(String wrong2) { this.wrong2 = wrong2; }

    public int getRightAnswer(){
        return correct;
    }

    public void setRightAnswer(int correct){
        this.correct = correct;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int point){
        this.point = point;
    }

    public String toString()
    {
        return country + " " + continent + " " + wrong1 + " " + wrong2 + " " + correct + " " + point;
    }

}
