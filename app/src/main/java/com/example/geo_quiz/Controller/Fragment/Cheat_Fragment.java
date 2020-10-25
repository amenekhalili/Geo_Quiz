package com.example.geo_quiz.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.geo_quiz.Controller.Activity.Geo_Quiz_Activity;
import com.example.geo_quiz.R;
import com.example.geo_quiz.Repository.QuestionRepository;


public class Cheat_Fragment extends Fragment {
    public static final String ARG_ANSWER_QUESTION = "ARG_ANSWER_QUESTION";
    public static final String ARG_CURRENT_INDEX = "ARG_CURRENT_INDEX";
    public static final String ARG_SCORE = "ARG_SCORE";
    public static final String ARG_ARRAY_ANSWERED = "ARG_ARRAY_ANSWERED";
    public static final String ARG_SIZE = "ARG_SIZE";
    public static final String ARG_COLOR = "ARG_COLOR";
    private Button mbtn_showcheat;
    private TextView mtxtview_Answer;
    private Button btn_back;
    QuestionRepository mQuestionRepository = QuestionRepository.getInstance();
    int size = mQuestionRepository.QuestionSize();
   boolean mIsAnswerTrue ;
   int [] mCurrentIndex = new int[size];
   int Score;
   boolean [] isAnswer = new boolean[size];
   int mCurrent =  0;
   boolean flag = false;
   private int sizeText ;
   private int color;

    public static Cheat_Fragment newInstance(boolean AnswerQuestion , int mCurrentIndex , boolean[] isAnswer , int size , int color , int Score) {

        Bundle args = new Bundle();
          args.putBoolean(ARG_ANSWER_QUESTION, AnswerQuestion);
          args.putInt(ARG_CURRENT_INDEX,mCurrentIndex);
          args.putInt(ARG_SCORE, Score);
          args.putBooleanArray(ARG_ARRAY_ANSWERED, isAnswer);
          args.putInt(ARG_SIZE, size);
          args.putInt(ARG_COLOR, color);
        Cheat_Fragment fragment = new Cheat_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIsAnswerTrue = getArguments().getBoolean(ARG_ANSWER_QUESTION);
        mCurrentIndex[mCurrent] = getArguments().getInt(ARG_CURRENT_INDEX);
        Score = getArguments().getInt(ARG_SCORE);
        isAnswer = getArguments().getBooleanArray(ARG_ARRAY_ANSWERED);
        sizeText = getArguments().getInt(ARG_SIZE);
        color = getArguments().getInt(ARG_COLOR);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viwe =  inflater.inflate(R.layout.fragment_cheat_, container, false);
        findView(viwe);
        setlistener();
        return viwe;
    }



    private void findView(View view) {
        mbtn_showcheat = view.findViewById(R.id.show_cheat);
        mtxtview_Answer = view.findViewById(R.id.answer_txtview);
        btn_back = view.findViewById(R.id.btn_back);
    }

    private void showAnswerforCheater() {
        if (mIsAnswerTrue)
            mtxtview_Answer.setText(R.string.str_true);
        else
            mtxtview_Answer.setText(R.string.str_false);
    }
    private void setlistener() {
        mbtn_showcheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswerforCheater();
                flag = true;


            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setShowAnswerResult(true , mCurrentIndex[mCurrent]);

            }
        });
    }
    private void setShowAnswerResult(boolean isCheat , int mCurrentIndex) {

       Intent intent = Geo_Quiz_Activity.newIntent(getActivity() , mCurrentIndex , isCheat  , isAnswer , sizeText , color , Score);
       startActivity(intent);
    }
}