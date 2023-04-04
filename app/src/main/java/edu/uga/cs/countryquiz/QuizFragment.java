package edu.uga.cs.countryquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.RadioGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private View view;
    private RadioButton[] answers;
    private RadioGroup answerGroup;
    private TextView questionText;
    private CurrentQuiz currentQuiz;



    public QuizFragment() {
        // Required empty public constructor
    }
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        questionText = view.findViewById(R.id.questText);
        answers = new RadioButton[] {
                view.findViewById(R.id.ans1),
                view.findViewById(R.id.ans2),
                view.findViewById(R.id.ans3)
        };
        answerGroup = view.findViewById(R.id.answer_choices);

        currentQuiz = ((QuizActivity) getActivity()).getCurrentQuiz();
        setQuestion(currentQuiz.getCurQuest());


        return view;
    }

    private void setQuestion(Question question) {
        questionText.setText(question.getQuestionText());

    }
}