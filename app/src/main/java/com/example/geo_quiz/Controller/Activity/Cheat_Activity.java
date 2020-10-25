package com.example.geo_quiz.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.geo_quiz.Controller.Fragment.Cheat_Fragment;
import com.example.geo_quiz.R;

public class Cheat_Activity extends SingleFragmentActivity{

    public static final String EXTRA_ANSWER_QUESTION = "AnswerQuestion";
    public static final String EXTRA_CURRENT_INDEX = "Current_Index";
    public static final String EXTRA_SCORE = "Score";
    public static final String EXTRA_ARRAY_ANSWERED = "ArrayAnswered";
    public static final String EXTRA_SIZE = "EXTRA_SIZE";
    public static final String EXTRA_COLOR = "EXTRA_COLOR";

    public static Intent newIntent (Context context , boolean AnswerQuestion  , int mCurrentIndex  , boolean[] isAnswer , int size , int color , int score){
        Intent intent = new Intent(context , Cheat_Activity.class);
        intent.putExtra(EXTRA_ANSWER_QUESTION, AnswerQuestion);
        intent.putExtra(EXTRA_CURRENT_INDEX, mCurrentIndex);
        intent.putExtra(EXTRA_ARRAY_ANSWERED, isAnswer);
        intent.putExtra(EXTRA_SIZE, size);
        intent.putExtra(EXTRA_COLOR, color);
        intent.putExtra(EXTRA_SCORE , score);
        return intent;
    }

    @Override
    public Fragment CreateFragment() {
        boolean AnswerQuestion = getIntent().getBooleanExtra(EXTRA_ANSWER_QUESTION , false);
        int mCurrentIndex = getIntent().getIntExtra(EXTRA_CURRENT_INDEX , 0);
       int Score = getIntent().getIntExtra(EXTRA_SCORE , 0);
        boolean[] isAnswer = getIntent().getBooleanArrayExtra(EXTRA_ARRAY_ANSWERED);
        int size = getIntent().getIntExtra(EXTRA_SIZE , 16);
        int color = getIntent().getIntExtra(EXTRA_COLOR , 4);
        return Cheat_Fragment.newInstance(AnswerQuestion , mCurrentIndex , isAnswer , size , color , Score);
    }


}