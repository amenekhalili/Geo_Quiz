package com.example.geo_quiz.Controller.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.geo_quiz.R;

public abstract  class SingleFragmentActivity extends AppCompatActivity {
    public abstract Fragment CreateFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_geo_quiz);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_geo_quiz_fragment);

        if(fragment == null){

            fragmentManager.beginTransaction()
                    .add(R.id.container_geo_quiz_fragment,CreateFragment() )
                    .commit();
        }



    }

}
