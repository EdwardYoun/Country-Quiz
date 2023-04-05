package edu.uga.cs.countryquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import android.widget.RadioGroup;
import android.widget.Button;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";

    private static Question[] quests = new Question[6];

    private View view;
    private RadioButton[] answers;
    private RadioGroup answerGroup;
    private TextView questionText;
    private TextView score;
    private Button startNew;
    private Button seeResults;
    private CurrentQuiz currentQuiz;

    //position in quiz
    private int questNum;
    //score of quiz
    private int quizScore;

    private String date;

    private List<Country> countriesList = new ArrayList<Country>();
    private CountriesData countriesData = null;
    private Random rand = new Random();

    public QuizFragment() {
        // Required empty public constructor
    }
    public static QuizFragment newInstance(int questNum) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt( "questNum", questNum );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questNum = getArguments().getInt("questNum");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        questionText = getView().findViewById(R.id.questText);
        answers = new RadioButton[] {
                getView().findViewById(R.id.ans1),
                getView().findViewById(R.id.ans2),
                getView().findViewById(R.id.ans3)
        };
        answerGroup = getView().findViewById(R.id.answer_choices);
        score = getView().findViewById(R.id.textView2);
        startNew = getView().findViewById(R.id.button4);
        seeResults = getView().findViewById(R.id.button3);
        answers[0].setOnClickListener(new CheckListener0());
        answers[1].setOnClickListener(new CheckListener1());
        answers[2].setOnClickListener(new CheckListener2());
        startNew.setOnClickListener(new NewListener());
        seeResults.setOnClickListener(new ResultListener());

        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        if (quests[0] == null) {
            setQuestions(quests);
            Log.d(TAG, "added quests");
        }
        else {
            Log.d(TAG, "quests already added");
        }

        //question number
        int num = questNum + 1;
        quizScore = quests[0].getPoint() + quests[1].getPoint() + quests[2].getPoint() + quests[3].getPoint() + quests[4].getPoint() + quests[5].getPoint();

        Log.d(TAG, "Current questNum: " + questNum);
        Log.d(TAG, "Current date: " + date);
        Log.d(TAG, "Current Score: " + quizScore);

        if (questNum == 6) {
            questionText.setVisibility(View.GONE);
            answers[0].setVisibility(View.GONE);
            answers[1].setVisibility(View.GONE);
            answers[2].setVisibility(View.GONE);
            answerGroup.setVisibility(View.GONE);

            score.setText("Score: " + quizScore + "/6");

            currentQuiz = new CurrentQuiz (quests, questNum-1, date, quizScore);
            //new QuizFragment.QuizzesDBWriter().execute();

        }
        else {
            score.setVisibility(View.GONE);
            startNew.setVisibility(View.GONE);
            seeResults.setVisibility(View.GONE);

            questionText.setText("Question " + num + ": What continent is " + quests[questNum].getCountry() + " from?");

            if (quests[questNum].getRightAnswer() == 0) {
                answers[0].setText("A: " + quests[questNum].getContinent());
                answers[1].setText("B: " + quests[questNum].getWrong1());
                answers[2].setText("C: " + quests[questNum].getWrong2());
            }
            else if (quests[questNum].getRightAnswer() == 1) {
                answers[0].setText("A: " + quests[questNum].getWrong1());
                answers[1].setText("B: " + quests[questNum].getContinent());
                answers[2].setText("C: " + quests[questNum].getWrong2());
            }
            else {
                answers[0].setText("A: " + quests[questNum].getWrong2());
                answers[1].setText("B: " + quests[questNum].getWrong1());
                answers[2].setText("C: " + quests[questNum].getContinent());
            }
        }


    }

    private void setQuestions(Question[] quests) {
        int randInt = -1;
        int[] temp = {-1, -1, -1, -1, -1, -1};
        int correct = -1;

        countriesData = new CountriesData(getActivity());
        countriesData.open();
        countriesList = countriesData.retrieveAllCountries();
        Country country = null;
        String wrong1 = "";
        String wrong2 = "";
        for (int i = 0; i < 6; i++) {
            while (randInt == -1 || randInt == temp[0] || randInt == temp[1] || randInt == temp[2] || randInt == temp[3] || randInt == temp[4] || randInt == temp[5]) {
                randInt = rand.nextInt(195);
            }
            country = countriesList.get(randInt);
            temp[i] = randInt;
            while (countriesList.get(randInt).getContinent().equals(country.getContinent())) {
                randInt = rand.nextInt(195);
            }
            wrong1 = countriesList.get(randInt).getContinent();
            while (countriesList.get(randInt).getContinent().equals(country.getContinent()) || countriesList.get(randInt).getContinent().equals(wrong1)) {
                randInt = rand.nextInt(195);
            }
            wrong2 = countriesList.get(randInt).getContinent();
            correct = rand.nextInt(3);
            quests[i] = new Question(country.getName(), country.getContinent(), wrong1, wrong2, correct, 0);
        }
    }

    public static int getNumberOfQuestions() { return quests.length+1; } //push

    private class CheckListener0 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (quests[questNum].getRightAnswer() == 0) {
                quests[questNum].setPoint(1);
                Log.d(TAG, "state: " + quests[questNum].getPoint());
            }
            else {
                quests[questNum].setPoint(0);
                Log.d(TAG, "state: " + quests[questNum].getPoint());
            }
        }
    }

    private class CheckListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (quests[questNum].getRightAnswer() == 1) {
                quests[questNum].setPoint(1);
                Log.d(TAG, "state: " + quests[questNum].getPoint());
            }
            else {
                quests[questNum].setPoint(0);
                Log.d(TAG, "state: " + quests[questNum].getPoint());
            }
        }
    }

    private class CheckListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (quests[questNum].getRightAnswer() == 2) {
                quests[questNum].setPoint(1);
                Log.d(TAG, "state: " + quests[questNum].getPoint());
            }
            else {
                quests[questNum].setPoint(0);
                Log.d(TAG, "state: " + quests[questNum].getPoint());
            }
        }
    }

    private class NewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            quests[0] = null;
            quests[1] = null;
            quests[2] = null;
            quests[3] = null;
            quests[4] = null;
            quests[5] = null;
            date = null;
            quizScore = 0;
            Intent intent = new Intent(v.getContext(), QuizActivity.class );
            startActivity(intent);
        }
    }

    private class ResultListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ResultActivity.class );
            startActivity(intent);
        }
    }


    /*
    private class QuizzesDBWriter extends AsyncTask<Void, --> {
        @Override
        protected -- doInBackground( Void... params) {

        }
        @Override
        protected void onPostExecute( -- ) {

            }
        }
    }*/
}
