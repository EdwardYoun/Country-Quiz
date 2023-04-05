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
 *
 * Implements the quiz for the user to test their knowledge of countries and the
 * continents they are located in. There are 6 questions for each quiz with 3 answer
 * choices for each question.
 */
public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";

    private static Question[] quests = new Question[6]; // array of question objects

    private View view;

    // UI elements
    private RadioButton[] answers;
    private RadioGroup answerGroup;
    private TextView questionText; // UI elements
    private TextView score;
    private Button startNew;
    private Button seeResults;
    private CurrentQuiz currentQuiz; // for saving quiz results
    private QuizzesData quizzesData = null;

    //position in quiz
    private int questNum;
    //score of quiz
    private int quizScore;

    private String date;

    private List<Country> countriesList = new ArrayList<Country>();
    private CountriesData countriesData = null;
    private Random rand = new Random();

    /**
     * Constructor
     */
    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Creates new instance of QuizFragment.
     * @param questNum
     * @return
     */
    public static QuizFragment newInstance(int questNum) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt( "questNum", questNum );
        fragment.setArguments( args );
        return fragment;
    }

    /**
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questNum = getArguments().getInt("questNum");
        }
    }

    /**
     * Creates the UI.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * Creates the quiz view. Randomizes answers, hides score and buttons until end.
     * Once a user completes the questions and arrives on the last page, they will be shown their
     * score and given the option to take a new quiz or see their results from past attempts.
     */
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

        // log info for testing
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

        // When the user arrives on the last page
        if (questNum == 6) {
            questionText.setVisibility(View.GONE);
            answers[0].setVisibility(View.GONE);
            answers[1].setVisibility(View.GONE);
            answers[2].setVisibility(View.GONE);
            answerGroup.setVisibility(View.GONE);

            score.setText("Score: " + quizScore + "/6");

            currentQuiz = new CurrentQuiz (quests, questNum-1, date, quizScore);
            quizzesData = new QuizzesData(getActivity());
            quizzesData.open();
            new QuizFragment.QuizzesDBWriter().execute();

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

    /**
     * Sets the questions randomly. Chooses a country to base its question and gets the
     * correct answer along with two incorrect answers.
     * @param quests
     */
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

    /**
     * CheckListeners 0, 1, and 2 check the users input for the correct answer.
     * The user's score is increased when they select the correct answer.
     */
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

    /**
     * Resets the quiz data and starts a new quiz when the "New Quiz!"
     * button is clicked.
     */
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

    /**
     * Displays past quiz results when the "See Results!" button is clicked.
     */
    private class ResultListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ResultActivity.class );
            startActivity(intent);
        }
    }


    /**
     * Writes the quiz data to the database so that it can be displayed when the
     * user wants to see their past quiz results.
     */
    private class QuizzesDBWriter extends AsyncTask<Void, Quiz> {
        @Override
        protected Quiz doInBackground( Void... params) {
            Quiz quiz = new Quiz(currentQuiz.getDate(), currentQuiz.getCurScore());
            Log.d(TAG, "quiz made for database table");
            return quiz;
        }
        @Override
        protected void onPostExecute( Quiz quiz ) {
            quizzesData.storeQuiz(quiz);
            Log.d(TAG, "quiz stored");
        }
    }
}
