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

    //question number in quiz
    private int questNum;

    private String date;

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

        currentQuiz = new CurrentQuiz (quests, 0, date, 0, 0, 0);
    }

/*    private void setQuestion(Question question) {

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

        int[] answerIndex = new int[] {0, 1, 2};
        shuffle(answerIndex);
        for (int i = 0; i < 3; i++) {
            answers[i].setText(question.getAnswerText(answerIndex[i]));
            if (answerIndex[i] == question.getRightAnswer()) {
                answers[i].setTag(true);
            }else {
                answers[i].setTag(false);
            }
        }
        answerGroup.clearCheck(); // clear selections
    } */


    public void shuffle(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        Country country = null;
        for (int i = 0; i < 6; i++) {

        }
    }

    public static int getNumberOfQuestions() { return quests.length+1; } //push
}