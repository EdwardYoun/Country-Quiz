package edu.uga.cs.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private QuizzesData quizzesData = null;

    private List<Quiz> quizzesList;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
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
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);

        quizzesData = new QuizzesData(getActivity());
        quizzesData.open();
        quizzesList = quizzesData.retrieveAllQuizzes();

        TableLayout tableLayout = getView().findViewById(R.id.table1);

        // set up margins for each TextView in the table layout
        android.widget.TableRow.LayoutParams layoutParams =
                new TableRow.LayoutParams( TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT );
        layoutParams.setMargins(20, 0, 20, 0);

        for (int i = 0; i < quizzesList.size(); i++) {
            TableRow tableRow = new TableRow( getContext() );
            TextView textView1 = new TextView( getContext() );
            TextView textView2 = new TextView( getContext() );

            textView1.setText(quizzesList.get(i).getDate());
            textView2.setText(quizzesList.get(i).getResult());
            tableRow.addView( textView1, layoutParams );
            tableRow.addView( textView2, layoutParams );
            // add the next row to the table layout
            tableLayout.addView( tableRow );
        }
    }
}