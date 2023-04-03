package edu.uga.cs.countryquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 *
 * This class is a singleton, following the Singleton Design Pattern.
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 */
public class QuizzesDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "QuizzesDBHelper";

    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 1;

    // Define all names (strings) for table and column names.
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_DATE = "date";
    public static final String QUIZZES_COLUMN_RESULT = "result";
    public static final String QUIZZES_COLUMN_NUM = "num";
    public static final String QUIZZES_COLUMN_QUESTION1 = "question1";
    public static final String QUIZZES_COLUMN_QUESTION2 = "question2";
    public static final String QUIZZES_COLUMN_QUESTION3 = "question3";
    public static final String QUIZZES_COLUMN_QUESTION4 = "question4";
    public static final String QUIZZES_COLUMN_QUESTION5 = "question5";
    public static final String QUIZZES_COLUMN_QUESTION6 = "question6";

    // This is a reference to the only instance for the helper.
    private static QuizzesDBHelper helperInstance;

    // A Create table SQL statement to create a table for quizzes.
    // _id is an auto increment primary key, i.e. the database will
    // automatically generate unique id values as keys.
    private static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + " ("
                    + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZZES_COLUMN_DATE + " TEXT, "
                    + QUIZZES_COLUMN_RESULT + " TEXT,"
                    + QUIZZES_COLUMN_NUM + " LONG,"
                    + QUIZZES_COLUMN_QUESTION1 + " QUESTION,"
                    + QUIZZES_COLUMN_QUESTION2 + " QUESTION,"
                    + QUIZZES_COLUMN_QUESTION3 + " QUESTION,"
                    + QUIZZES_COLUMN_QUESTION4 + " QUESTION,"
                    + QUIZZES_COLUMN_QUESTION5 + " QUESTION,"
                    + QUIZZES_COLUMN_QUESTION6 + " QUESTION"
                    + ")";

    //constructor
    private QuizzesDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    // Access method to the single instance of the class.
    // It is synchronized, so that only one thread can executes this method, at a time.
    public static synchronized QuizzesDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new QuizzesDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // Create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_QUIZZES );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZZES + " created" );
    }

    // Used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_QUIZZES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZZES + " upgraded" );
    }

}
