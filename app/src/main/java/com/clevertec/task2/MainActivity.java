package com.clevertec.task2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.clevertec.task2.fragment.FirstFragment;
import com.clevertec.task2.interfaces.OnBackPressedListener;
import com.clevertec.task_2.R;

/**
 * @author Vitikova Maria
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.container_fragments, FirstFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedForFragments = null;

        for (Fragment fragment : fm.getFragments()) {
            if (fragment instanceof OnBackPressedListener) {
                backPressedForFragments = (OnBackPressedListener) fragment;
                break;
            }
        }
        if (backPressedForFragments != null) {
            backPressedForFragments.onBackPressed();
        }
    }
}