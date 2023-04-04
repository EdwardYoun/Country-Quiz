package edu.uga.cs.countryquiz;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuizzesPagerAdapter extends FragmentStateAdapter {
    public QuizzesPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
    }

    @Override
    public QuizFragment createFragment(int questNum){
        return QuizFragment
                .newInstance( questNum );
    }

    @Override
    public int getItemCount() {
        return QuizFragment
                .getNumberOfQuestions();
    }
}
