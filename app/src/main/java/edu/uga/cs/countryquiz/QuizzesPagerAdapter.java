package edu.uga.cs.countryquiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuizzesPagerAdapter extends FragmentStateAdapter {
    public QuizzesPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
    }

    /**
     * remember the position of the fragment for displaying info
     * @param questNum question number
     * @return new fragment instance
     */
    @Override
    public Fragment createFragment(int questNum){
        return QuizFragment
                .newInstance( questNum );
    }

    /**
     * get number of questions + 1 more for the result screen
     * @return number of questions + 1
     */
    @Override
    public int getItemCount() {
        return QuizFragment
                .getNumberOfQuestions();
    }
}
