package edu.uga.cs.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Button;

/**
 * QuizActivity Class contains the ViewPager that allows swiping between
 * the quiz questions and final score page.
 */
public class QuizActivity extends AppCompatActivity {

    /**
     * Sets view for QuizActivity. Initializes ViewPager2 for swiping
     * in the UI to navigate between the questions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ViewPager2 pager = findViewById( R.id.viewpager );
        QuizzesPagerAdapter avpAdapter = new
                QuizzesPagerAdapter(
                getSupportFragmentManager(), getLifecycle() );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( avpAdapter );
    } //push


}