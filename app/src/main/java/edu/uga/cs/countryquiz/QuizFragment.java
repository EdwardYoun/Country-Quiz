package edu.uga.cs.countryquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import android.widget.RadioGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

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

    private String date;

    private List<Country> countriesList = new ArrayList<Country>();
    private CountriesData countriesData = null;

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


        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        setQuestions(quests);

        currentQuiz = new CurrentQuiz (quests, 0, date, 0, 0, 0);

        //question number
        int num = questNum + 1;

        if (questNum == 6) {
            questionText.setVisibility(View.GONE);
            answers[0].setVisibility(View.GONE);
            answerGroup.setVisibility(View.GONE);
        }
        else {
            score.setVisibility(View.GONE);
            startNew.setVisibility(View.GONE);
            seeResults.setVisibility(View.GONE);
        }

        questionText.setText("Question " + num);
    }

    private void setQuestions(Question[] quests) {
        countriesData = new CountriesData(getActivity());
        countriesData.open();
        countriesList = countriesData.retrieveAllCountries();
        Country country = null;
        for (int i = 0; i < 6; i++) {

        }
    }

    public static int getNumberOfQuestions() { return quests.length+1; } //push
}