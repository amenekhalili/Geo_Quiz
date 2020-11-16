package com.example.geo_quiz.Controller.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geo_quiz.Controller.Activity.Cheat_Activity;
import com.example.geo_quiz.Controller.Activity.Setting_Activity;
import com.example.geo_quiz.Model.Question;
import com.example.geo_quiz.R;
import com.example.geo_quiz.Repository.QuestionRepository;

import java.util.UUID;

import static androidx.core.content.ContextCompat.getColor;


public class Geo_Quiz_Fragment extends Fragment {

    public static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";
    public static final String CURRENT_INDEX = "currentIndex";
    public static final String SCORE = "SCORE";
    public static final String IS_ANSWER = "isAnswer";
    public static final String ARG_CURRENT_INDEX = "ARG_CurrentIndex";
    public static final String ARG_IS_CHEAT = "ARG_IS_CHEAT";
    public static final String ARG_SCORE = "ARG_Score";
    public static final String ARG_IS_ANSWER = "ARG_IS_ANSWER";
    public static final String IS_CHEATER = "IS_CHEATER";
    public static final String INDEX_CHEATER = "INDEX_CHEATER";
    public static final String ARG_COLOR_BACK = "ARG_COLOR_BACK";
    public static final String ARG_SIZE_TXT_QUESTION = "ARG_SIZE_TXT_QUESTION";
    public static final String ARG_REQ_CODE = "ARG_REQ_CODE";
    public static final String SIZE_OF_TEXT_QUESTION = "size_text";
    public static final String COLOR_OF_BACKGROUND = "color_back";

    private Button btn_true;
    private Button btn_false;
    private ImageButton btn_next;
    private Button btn_cheat;
    private ImageButton btn_pre;
    private ImageButton btn_doubleNext;
    private ImageButton btn_doublePre;
    private TextView TextViewQuestion;
    private Button btn_score;
    private ImageButton btnReset;
    private Button btn_setting;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutPre;
    private LinearLayout linearLayoutNext;
    private LinearLayout linearLayoutTxtQuestion;
    private FrameLayout frameLayoutTxtQuestion;
    private String str;
    private int mCurrentIndex;
    private boolean mIsCheater;
    private int IndexCheater;
    private int score = 0;
    private String scoreStr;
    private  UUID QuestionId;
    private Question mQuestion;
    QuestionRepository questionRepository = QuestionRepository.getInstance();
    private final int size = questionRepository.QuestionSize();
    private boolean[] isAnswer = new boolean[size];
      private  int colorOfBackGround ;
      private int sizeOfTextQuestion ;
      private int RequestCodeSettingActivity = 1 ;
      private ViewPager2 mViewPager2;

    public static Geo_Quiz_Fragment newInstance(UUID QuestionId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION_ID, QuestionId);
        Geo_Quiz_Fragment fragment = new Geo_Quiz_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static Geo_Quiz_Fragment newInstance(int CurrentIndex , boolean isCheat , int Score , boolean[] isAnswer,int colorOfBackGround , int sizeOfTextQuestion) {

        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_INDEX, CurrentIndex);
        args.putBoolean(ARG_IS_CHEAT, isCheat);
        args.putInt(ARG_SCORE, Score);
        args.putBooleanArray(ARG_IS_ANSWER, isAnswer);
        args.putInt(ARG_COLOR_BACK,colorOfBackGround );
        args.putInt(ARG_SIZE_TXT_QUESTION, sizeOfTextQuestion);
        Geo_Quiz_Fragment fragment = new Geo_Quiz_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static Geo_Quiz_Fragment newInstance(int colorOfBackGround , int sizeOfTextQuestion ,int CurrentIndex  ,  int Score , int requestCode) {

        Bundle args = new Bundle();
        args.putInt(ARG_COLOR_BACK,colorOfBackGround );
        args.putInt(ARG_SIZE_TXT_QUESTION, sizeOfTextQuestion);
        args.putInt(ARG_CURRENT_INDEX, CurrentIndex);
        args.putInt(ARG_SCORE, Score);
        args.putInt(ARG_REQ_CODE, requestCode);
        Geo_Quiz_Fragment fragment = new Geo_Quiz_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < isAnswer.length; i++) {
            isAnswer[i] = false;
        }

        QuestionId = (UUID) getArguments().getSerializable(ARG_QUESTION_ID);
        mQuestion = questionRepository.getQuestion(QuestionId);
        mCurrentIndex = questionRepository.getPosition(QuestionId);

        RequestCodeSettingActivity = getArguments().getInt(ARG_REQ_CODE);

        if( QuestionId == null && RequestCodeSettingActivity  == 2){
            colorOfBackGround = getArguments().getInt(ARG_COLOR_BACK);
            sizeOfTextQuestion = getArguments().getInt(ARG_SIZE_TXT_QUESTION);
            mCurrentIndex = getArguments().getInt(ARG_CURRENT_INDEX);
           score = getArguments().getInt(ARG_SCORE);
           mQuestion = questionRepository.getQuestion(mCurrentIndex);
        }else if(QuestionId == null){
            mIsCheater = getArguments().getBoolean(ARG_IS_CHEAT);
            IndexCheater = getArguments().getInt(ARG_CURRENT_INDEX);
            mCurrentIndex = getArguments().getInt(ARG_CURRENT_INDEX);
            score = getArguments().getInt(ARG_SCORE);
            isAnswer = getArguments().getBooleanArray(ARG_IS_ANSWER);
            mQuestion = questionRepository.getQuestion(mCurrentIndex);
            colorOfBackGround = getArguments().getInt(ARG_COLOR_BACK);
            sizeOfTextQuestion = getArguments().getInt(ARG_SIZE_TXT_QUESTION);
        }



        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
            score = savedInstanceState.getInt(SCORE, 0);
            isAnswer = savedInstanceState.getBooleanArray(IS_ANSWER);
            IndexCheater = savedInstanceState.getInt(INDEX_CHEATER, 10);
            mIsCheater = savedInstanceState.getBoolean(IS_CHEATER, false);
             sizeOfTextQuestion = savedInstanceState.getInt(SIZE_OF_TEXT_QUESTION, 16);
            colorOfBackGround = savedInstanceState.getInt(COLOR_OF_BACKGROUND, 0);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_geo__quiz_, container, false);
        findViews(view);
        setListener(view);
        setquestion(view);
        makestr();
        setScore(str);
        setVisibility(view);
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setSizeQuestionText();
        setColorofbackground();
    }

    private void setSizeQuestionText() {
        if (sizeOfTextQuestion == 14) {
            TextViewQuestion.setTextSize(14);
        }
        if (sizeOfTextQuestion == 18) {
            TextViewQuestion.setTextSize(18);
        }
        if (sizeOfTextQuestion == 26) {
           TextViewQuestion.setTextSize(26);
        }
    }

    private void setColorofbackground() {
        Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.container_geo_quiz_fragment);


        if (colorOfBackGround == 3) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.lightRed));

        }
        if (colorOfBackGround== 2) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.lightGreen));
        }
        if (colorOfBackGround == 1) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.lightBlue));
        }
        if (colorOfBackGround== 4) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.White));


        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX, mCurrentIndex);
        outState.putInt(SCORE, score);
        outState.putBooleanArray(IS_ANSWER, isAnswer);
       outState.putBoolean(IS_CHEATER, mIsCheater);
       outState.putInt(INDEX_CHEATER, IndexCheater);
       outState.putInt(SIZE_OF_TEXT_QUESTION, sizeOfTextQuestion);
        outState.putInt(COLOR_OF_BACKGROUND, colorOfBackGround);
    }
    private void findViews(View view ) {
        btn_true = view.findViewById(R.id.btn_true);
        btn_false = view.findViewById(R.id.btn_false);
        btn_next = view.findViewById(R.id.btn_next);
        btn_pre = view.findViewById(R.id.btn_pre);
        btn_doubleNext = view.findViewById(R.id.btn_doublenext);
        btn_doublePre = view.findViewById(R.id.btn_doublepre);
        TextViewQuestion = view.findViewById(R.id.txt_questiom);
        btn_score = view.findViewById(R.id.btn_score);
        btnReset = view.findViewById(R.id.btn_reset);
        linearLayout = view.findViewById(R.id.hideUI);
        linearLayoutPre = view.findViewById(R.id.pre_layout);
        linearLayoutNext = view.findViewById(R.id.next_layout);
        linearLayoutTxtQuestion = view.findViewById(R.id.layout_txtQuestion);
        btn_cheat = view.findViewById(R.id.cheat_btn);
        btn_setting = view.findViewById(R.id.btn_setting);
        frameLayoutTxtQuestion = view.findViewById(R.id.container_geo_quiz_fragment);
        mViewPager2 = getActivity().findViewById(R.id.view_pager_question);
    }
    private void setListener(final View view) {
        final View v = view;
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true, v);
                setScore(str);
            }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false, v);
                setScore(str);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % size;

               setquestion(view);
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 + size) % size;
              setquestion(view);

            }
        });


        btn_doubleNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = size;
               setquestion(view);
            }
        });

        btn_doublePre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                setquestion(view);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout.setVisibility(LinearLayout.VISIBLE);
                linearLayoutNext.setVisibility(LinearLayout.VISIBLE);
                linearLayoutPre.setVisibility(LinearLayout.VISIBLE);
                for (int i = 0; i < isAnswer.length; i++) {
                    isAnswer[i] = false;
                }
                setquestion(view);
                score = 0;
                makestr();
                setScore(str);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = Setting_Activity.newIntent(getActivity() , mCurrentIndex, score , RequestCodeSettingActivity , sizeOfTextQuestion , colorOfBackGround );
               startActivity(intent);
            }
        });

      btn_cheat.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = Cheat_Activity.newIntent(getActivity(), questionRepository.getQuestion(mCurrentIndex).ismIsAnswerTrue() , mCurrentIndex, isAnswer , sizeOfTextQuestion , colorOfBackGround , score) ;
              startActivity(intent);
          }
      });

    }
    private void setVisibility(View view) {
        boolean flag = false;
        for (int i = 0; i < isAnswer.length; i++) {
            if (isAnswer[i] == false) {
                flag = true;
            }
        }
        if (flag == false) {
            linearLayout.setVisibility(LinearLayout.GONE);
            linearLayoutNext.setVisibility(LinearLayout.GONE);
            linearLayoutPre.setVisibility(LinearLayout.GONE);
            btnReset.setVisibility(view.findViewById(R.id.btn_reset).VISIBLE);
        }

    }

    private void disableButton() {
        btn_true.setEnabled(false);
        btn_false.setEnabled(false);
    }

    private void EnableButton() {
        btn_true.setEnabled(true);
        btn_false.setEnabled(true);
    }

    private void checkAnswer(boolean userpressed, View view) {

        final LinearLayout layouttrue = new LinearLayout(getActivity());
        final TextView textViewtrue = new TextView(getActivity());
        final Toast toasttrue = new Toast(getContext());
        final ImageView imageViewtrue = new ImageView(getActivity());

        final LinearLayout layoutfalse = new LinearLayout(getActivity());
        final TextView textViewfalse = new TextView(getActivity());
        final Toast toastfalse = new Toast(getContext());
        final ImageView imageViewfalse = new ImageView(getActivity());

        final Toast toastcheat = new Toast(getContext());
        final TextView textViewcheat = new TextView(getActivity());
        final LinearLayout layoutcheat = new LinearLayout(getActivity());

        if (mIsCheater && IndexCheater == mCurrentIndex) {

            textViewcheat.setText(R.string.judgment);
            toastcheat.setDuration(Toast.LENGTH_SHORT);
            layoutcheat.removeAllViews();
            layoutcheat.addView(textViewcheat);
            toastcheat.setView(layoutcheat);
            toastcheat.show();
            isAnswer[mCurrentIndex] = true;
            disableButton();
            setVisibility(view);

            if (questionRepository.getQuestion(mCurrentIndex).ismIsAnswerTrue() == userpressed) {
                score++;
                makestr();
            } else {
                makestr();
            }


        } else {
            if (questionRepository.getQuestion(mCurrentIndex).ismIsAnswerTrue() == userpressed) {
                score++;
                makestr();
                textViewtrue.setText(R.string.true_message);
                textViewtrue.setTextColor(Color.GREEN);
                toasttrue.setDuration(Toast.LENGTH_SHORT);
                imageViewtrue.setImageResource(R.drawable.ic_true);
                layouttrue.removeAllViews();
                layouttrue.addView(textViewtrue);
                layouttrue.addView(imageViewtrue);
                toasttrue.setView(layouttrue);
                toasttrue.show();
                isAnswer[mCurrentIndex] = true;
                disableButton();
                setVisibility(view);
            } else if (questionRepository.getQuestion(mCurrentIndex).ismIsAnswerTrue() != userpressed) {
                makestr();
                textViewfalse.setText(R.string.false_message);
                textViewfalse.setTextColor(Color.RED);
                toastfalse.setDuration(Toast.LENGTH_SHORT);
                imageViewfalse.setImageResource(R.drawable.ic_false);
                layoutfalse.removeAllViews();
                layoutfalse.addView(textViewfalse);
                layoutfalse.addView(imageViewfalse);
                toastfalse.setView(layoutfalse);
                toastfalse.setGravity(Gravity.CENTER, 0, -400);
                toastfalse.show();
                isAnswer[mCurrentIndex] = true;
                disableButton();
                setVisibility(view);
            }
        }


    }

    private void setScore(String s) {
        btn_score.setText(s);
    }

    private void setquestion(View view) {
        if (isAnswer[mCurrentIndex] == false) {
            EnableButton();
        } else if (isAnswer[mCurrentIndex] == true) {
            disableButton();
        }

        btnReset.setVisibility(view.findViewById(R.id.btn_reset).INVISIBLE);
        int questionId = questionRepository.getQuestion(mCurrentIndex).getmQuestiontextResId();
        TextViewQuestion.setText(questionId);




    }


    private void makestr() {
        scoreStr = String.valueOf(score);
        str = "امتیاز: " + scoreStr;
    }
}