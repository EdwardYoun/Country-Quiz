package edu.uga.cs.countryquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * Splash screen for when app starts. Displays the basic information about the quiz layout.
 * Gives the user the option to start a new quiz or see their results of past quizzes.
 */
public class SplashFragment extends Fragment{

    private static final String TAG = "SplashFragment";

    private CountriesData countriesData = null;

    private List<Country> countriesList;


    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);


    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * Sets up the splash screen layout. "New Quiz!" and "See Results!" buttons.
     *
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );


        Button quiz = getView().findViewById(R.id.button);
        Button result = getView().findViewById(R.id.button2);

        quiz.setOnClickListener(new QuizListener());
        result.setOnClickListener(new ResultListener());


        countriesList = new ArrayList<Country>();

        Context context = getContext();
        File countriesFile = context.getDatabasePath("countries.db");

        countriesData = new CountriesData(getActivity());
        countriesData.open();

        countriesList = countriesData.retrieveAllCountries();

        // fill country info from database
        if (countriesList.isEmpty()) {
            new CountriesDBWriter().execute();
        }
    }

    /**
     * New Quiz Listener
     */
    private class QuizListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), QuizActivity.class );
            startActivity(intent);
        }
    }

    /**
     * See Results Listener
     */
    private class ResultListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ResultActivity.class );
            startActivity(intent);
        }
    }

    /**
     * Gets the countries and continents information from the database.
     */
    private class CountriesDBWriter extends AsyncTask<Void, InputStream> {
        /**
         * do in background
         * @param params nothing
         * @return inputstream for onPostExecute
         */
        @Override
        protected InputStream doInBackground( Void... params) {
            Context context = getContext();
            try {
                InputStream in_s = context.getAssets().open("country_continent.csv");
                return in_s;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * on post execute to start reading the cvs file and importing the info into the
         * countries database
         * @param in_s inputstream of cvs file
         */
        @Override
        protected void onPostExecute( InputStream in_s) {
            // read the CSV data
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;
            while(true) {
                try {
                    if (!(( nextRow = reader.readNext() ) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (CsvValidationException e) {
                    throw new RuntimeException(e);
                }

                // nextRow[] is an array of values from the line
                Country country = new Country(nextRow[0], nextRow[1]);
                countriesData.storeCountry(country);
                Log.d( TAG, country + " inserted" );
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (countriesData != null && !countriesData.isDBOpen() ) {
            countriesData.open();
            Log.d( TAG, "SplashFragment.onResume(): opening DB" );
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (countriesData != null) {
            countriesData.close();
            Log.d( TAG, "SplashFragment.onPause(): closing DB" );
        }
    }
}