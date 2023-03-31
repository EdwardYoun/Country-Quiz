package edu.uga.cs.countryquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class is facilitates storing and restoring quizzes stored.
 */
public class QuizzesData {

    public static final String DEBUG_TAG = "QuizzesData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase   db;
    private SQLiteOpenHelper quizzesDbHelper;
    private static final String[] allColumns = {
            QuizzesDBHelper.QUIZZES_COLUMN_ID,
            QuizzesDBHelper.QUIZZES_COLUMN_DATE,
            QuizzesDBHelper.QUIZZES_COLUMN_RESULT,
            QuizzesDBHelper.QUIZZES_COLUMN_NUM
    };

    public QuizzesData( Context context ) {
        this.quizzesDbHelper = QuizzesDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = quizzesDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "QuizzesData: db open" );
    }

    // Close the database
    public void close() {
        if( quizzesDbHelper != null ) {
            quizzesDbHelper.close();
            Log.d(DEBUG_TAG, "QuizzesData: db closed");
        }
    }

    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    // Retrieve all quizzes and return them as a List.
    // This is how we restore persistent objects stored as rows in the quizzes table in the database.
    // For each retrieved row, we create a new Quiz (Java POJO object) instance and add it to the list.
    public List<Quiz> retrieveAllQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( QuizzesDBHelper.TABLE_QUIZZES, allColumns,
                    null, null, null, null, null );

            // collect all countries into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 3) {

                        // get all attribute values of this quiz
                        columnIndex = cursor.getColumnIndex( QuizzesDBHelper.QUIZZES_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( QuizzesDBHelper.QUIZZES_COLUMN_DATE );
                        String date = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( QuizzesDBHelper.QUIZZES_COLUMN_RESULT );
                        String result = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( QuizzesDBHelper.QUIZZES_COLUMN_NUM );
                        long num = cursor.getLong( columnIndex );

                        // create a new Country object and set its state to the retrieved values
                        Quiz quiz = new Quiz( date, result, num );
                        quiz.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        quizzes.add( quiz );
                        Log.d(DEBUG_TAG, "Retrieved Quiz: " + quiz);
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
        return quizzes;
    }
}
