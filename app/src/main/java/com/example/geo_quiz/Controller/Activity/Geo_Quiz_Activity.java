package com.example.geo_quiz.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.geo_quiz.Controller.Fragment.Geo_Quiz_Fragment;
import com.example.geo_quiz.R;

import java.util.UUID;

public class Geo_Quiz_Activity extends SingleFragmentActivity {

    public static final String EXTRA_QUESTION_ID = "com.example.geo_quiz.Controller.questionId";
    public static final String EXTRA_CURRENT_INDEX = "Current_Index";
    public static final String EXTRA_IS_CHEAT = "Is_Cheat";
    public static final String EXTRA_SCORE = "Score";
    public static final String EXTRA_ARRAY_ANSWERED = "Array_Answered";
    public static final String COLOR_BACK = "color_back";
    public static final String SIZE_QUESTION = "size_question";
    public static final String EXTRA_REQ_CODE = "EXTRA_REQ_CODE";

    public  static Intent newIntent (Context context , UUID QuestionId){
        Intent intent = new Intent(context , Geo_Quiz_Activity.class);
        intent.putExtra(EXTRA_QUESTION_ID, QuestionId);
        return intent;
    }

    public  static Intent newIntent (Context context ,int CurrentIndex , boolean isCheat , boolean[] isAnswer ,int ColorOfBackGround , int SizeOfTextQuestion , int score ){
        Intent intent = new Intent(context , Geo_Quiz_Activity.class);
        intent.putExtra(EXTRA_CURRENT_INDEX, CurrentIndex);
        intent.putExtra(EXTRA_IS_CHEAT, isCheat);
        intent.putExtra(EXTRA_CURRENT_INDEX, CurrentIndex);
        intent.putExtra(EXTRA_ARRAY_ANSWERED, isAnswer);
        intent.putExtra(COLOR_BACK, ColorOfBackGround);
        intent.putExtra(SIZE_QUESTION, SizeOfTextQuestion);
        intent.putExtra(EXTRA_SCORE , score);
        return intent;
    }

    public  static Intent newIntent (Context context ,int ColorOfBackGround , int SizeOfTextQuestion , int CurrentIndex , int Score , int requestCode){
        Intent intent = new Intent(context , Geo_Quiz_Activity.class);
        intent.putExtra(COLOR_BACK, ColorOfBackGround);
        intent.putExtra(SIZE_QUESTION, SizeOfTextQuestion);
        intent.putExtra(EXTRA_CURRENT_INDEX, CurrentIndex);
        intent.putExtra(EXTRA_CURRENT_INDEX, CurrentIndex);
        intent.putExtra(EXTRA_REQ_CODE, requestCode);
        intent.putExtra(EXTRA_SCORE , Score);
        return intent;
    }

    @Override
    public Fragment CreateFragment() {
        UUID QuestionId = (UUID) getIntent().getSerializableExtra(EXTRA_QUESTION_ID);
        Geo_Quiz_Fragment geo_quiz_fragment = Geo_Quiz_Fragment.newInstance(QuestionId);

         int mCurrentIndex = getIntent().getIntExtra(EXTRA_CURRENT_INDEX , 0);
         boolean isCheat =  getIntent().getBooleanExtra(EXTRA_IS_CHEAT , false);
         int Score = getIntent().getIntExtra(EXTRA_SCORE , 0);
         boolean[] isAnswer = getIntent().getBooleanArrayExtra(EXTRA_ARRAY_ANSWERED);
        int colorOfBackGround1 = getIntent().getIntExtra(COLOR_BACK , 4);
        int sizeOfTextQuestion1 = getIntent().getIntExtra(SIZE_QUESTION , 16);
         Geo_Quiz_Fragment geo_quiz_fragment1 = Geo_Quiz_Fragment.newInstance(mCurrentIndex , isCheat , Score , isAnswer , sizeOfTextQuestion1 , colorOfBackGround1);

         int colorOfBackGround = getIntent().getIntExtra(COLOR_BACK , 4);
         int sizeOfTextQuestion = getIntent().getIntExtra(SIZE_QUESTION , 16);
        int mCurrentIndex1 = getIntent().getIntExtra(EXTRA_CURRENT_INDEX , 0);
        int Score1 = getIntent().getIntExtra(EXTRA_SCORE , 0);
        int reqCode = getIntent().getIntExtra(EXTRA_REQ_CODE , 1);
         Geo_Quiz_Fragment geo_quiz_fragment2 = Geo_Quiz_Fragment.newInstance(colorOfBackGround , sizeOfTextQuestion , mCurrentIndex1 , Score1 , reqCode);


       if(QuestionId != null){
           return geo_quiz_fragment;
       }else if(QuestionId == null && reqCode == 2){
           return geo_quiz_fragment2;
       }else{
           return geo_quiz_fragment1;
       }
    }


}