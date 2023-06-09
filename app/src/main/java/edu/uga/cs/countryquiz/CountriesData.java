package edu.uga.cs.countryquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This class facilitates storing and restoring countries stored in the database.
 */
public class CountriesData {

    public static final String DEBUG_TAG = "CountriesData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase   db;
    private SQLiteOpenHelper countriesDbHelper;

    //country info in db
    private static final String[] allColumns = {
            CountriesDBHelper.COUNTRIES_COLUMN_ID,
            CountriesDBHelper.COUNTRIES_COLUMN_NAME,
            CountriesDBHelper.COUNTRIES_COLUMN_CONTINENT
    };

    /**
     * Constructor creates CountriesDBHelper to manage database.
     * @param context
     */
    public CountriesData( Context context ) {
        this.countriesDbHelper = CountriesDBHelper.getInstance( context );
    }

    /**
     * opens the countries database
     */
    // Open the database
    public void open() {
        db = countriesDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "CountriesData: db open" );
    }

    /**
     * closes the countries database
     */
    public void close() {
        if( countriesDbHelper != null ) {
            countriesDbHelper.close();
            Log.d(DEBUG_TAG, "CountriesData: db closed");
        }
    }

    /**
     * Boolean to check if the database is open.
     * @return
     */
    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    /** Retrieve all countries and return them as a List.
     *This is how we restore persistent objects stored as rows in the countries table in the database.
     *For each retrieved row, we create a new Country (Java POJO object) instance and add it to the list.
     */
    public List<Country> retrieveAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( CountriesDBHelper.TABLE_COUNTRIES, allColumns,
                    null, null, null, null, null );

            // collect all countries into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 3) {

                        //get all attribute values of this country
                        columnIndex = cursor.getColumnIndex( CountriesDBHelper.COUNTRIES_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( CountriesDBHelper.COUNTRIES_COLUMN_NAME );
                        String name = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( CountriesDBHelper.COUNTRIES_COLUMN_CONTINENT );
                        String continent = cursor.getString( columnIndex );

                        //Create a new Country object and set its state to the retrieved values.
                        Country country = new Country( name, continent );
                        country.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        countries.add( country );
                        Log.d(DEBUG_TAG, "Retrieved Country: " + country);
                    }
                }
            }
            if( cursor != null )
                Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
            else
                Log.d( DEBUG_TAG, "Number of records from DB: 0" );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved countries
        return countries;
    }

    /**
     * Store a new country in the database.
     * @param country country object that will be stored
     * @return country that was just stored
     */
    public Country storeCountry( Country country ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the Country argument.
        // This is how we are providing persistence to a Country (Java object) instance
        // by storing it as a new row in the database table representing countries.
        ContentValues values = new ContentValues();
        values.put( CountriesDBHelper.COUNTRIES_COLUMN_NAME, country.getName());
        values.put( CountriesDBHelper.COUNTRIES_COLUMN_CONTINENT, country.getContinent() );

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( CountriesDBHelper.TABLE_COUNTRIES, null, values );

        // store the id (the primary key) in the Country instance, as it is now persistent
        country.setId( id );

        Log.d( DEBUG_TAG, "Stored new country with id: " + String.valueOf( country.getId() ) );

        return country;
    }


}

