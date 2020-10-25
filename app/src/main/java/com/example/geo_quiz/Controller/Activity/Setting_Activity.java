package com.example.geo_quiz.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.geo_quiz.Controller.Fragment.Setting_Fragment;
import com.example.geo_quiz.R;

public class Setting_Activity extends SingleFragmentActivity {

    public static final String EXTRA_SCORE = "EXTRA_SCORE";
    public static final String EXTRA_CURRENT_INDEX = "EXTRA_CURRENT_INDEX";
    public static final String EXTRA_REQ_CODE = "EXTRA_REQ_CODE";
    public static final String EXTRA_SIZE = "EXTRA_SIZE";
    public static final String EXTRA_COLOR = "EXTRA_COLOR";

    public static Intent newIntent(Context context , int mCurrentIndex  , int Score , int requestCode , int size , int color){
        Intent intent = new Intent (context , Setting_Activity.class);
        intent.putExtra(EXTRA_CURRENT_INDEX, mCurrentIndex);
        intent.putExtra(EXTRA_REQ_CODE, requestCode);
        intent.putExtra(EXTRA_SCORE, Score);
        intent.putExtra(EXTRA_SIZE, size);
        intent.putExtra(EXTRA_COLOR, color);
        return  intent;
    }

    @Override
    public Fragment CreateFragment() {
         int mCurrentIndex = getIntent().getIntExtra(EXTRA_CURRENT_INDEX , 0);
         int Score = getIntent().getIntExtra(EXTRA_SCORE  , 0);
         int requestCode = getIntent().getIntExtra(EXTRA_REQ_CODE , 1);
         int sizeText  = getIntent().getIntExtra(EXTRA_SIZE , 16);
         int colorBackground = getIntent().getIntExtra(EXTRA_COLOR , 4);
        return Setting_Fragment.newInstance(mCurrentIndex , Score ,requestCode , sizeText , colorBackground);
    }


}