package edu.uga.cs.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {

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

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        Button quiz = getView().findViewById(R.id.button);
        Button result = getView().findViewById(R.id.button2);

        //setOnClickListener will be for other activities

        countriesList = new ArrayList<Country>();

        countriesData = new CountriesData(getActivity());
        countriesData.open();

        new CountriesDBWriter().execute();
    }

    private class CountriesDBWriter extends AsyncTask<Void, Country> {
        @Override
        protected List<Country> doInBackground( Void... params) {
            InputStream in_s = getAssets().open( "ranking.csv" );
        }
        @Override
    }
}