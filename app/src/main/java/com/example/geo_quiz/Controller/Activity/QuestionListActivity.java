package com.example.geo_quiz.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.geo_quiz.Controller.Fragment.QuestionListFragment;
import com.example.geo_quiz.R;

public class QuestionListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context , QuestionListActivity.class);
        return intent;
    }

    @Override
    public Fragment CreateFragment() {
        return QuestionListFragment.newInstance();
    }


}