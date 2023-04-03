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
    private String wrong1;
    private String wrong2;

    public Question()
    {
        this.country = null;
        this.continent = null;
        this.wrong1 = null;
        this.wrong2 = null;
    }

    public Question( String country, String continent, String wrong1, String wrong2) {
        this.country = country;
        this.continent = continent;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country) { this.country = country; }

    public String getContinent()
    {
        return continent;
    }

    public void setContinent(String continent) { this.continent = continent; }

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

    public String toString()
    {
        return country + " " + continent + " " + wrong1 + " " + wrong2;
    }
}
