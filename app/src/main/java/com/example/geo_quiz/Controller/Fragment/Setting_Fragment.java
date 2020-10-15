package com.example.geo_quiz.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.geo_quiz.Controller.Activity.Geo_Quiz_Activity;
import com.example.geo_quiz.R;

public class Setting_Fragment extends Fragment {
    public static final String ARG_CURRENT_INDEX = "ARG_CURRENT_INDEX";
    public static final String ARG_SCORE = "ARG_SCORE";
    public static final String ARG_REQ_CODE = "ARG_REQ_CODE";
    public static final String SIZE_QUESTION = "sizeQuestion";
    public static final String COLOE_BACKGROUND = "colorBackground";
    public static final String FLAG_SIZE = " flagSize";
    public static final String FLAG_COLOR = " flagColor";
    public static final String ARG_SIZE_TEXT = "ARG_SIZE_TEXT";
    public static final String ARG_COLOR_BACKGROUND = "ARG_COLOR_BACKGROUND";
    private Button btn_changeQuestionSize;
    private Button btn_smallsize;
    private Button btn_mediumsize;
    private Button btn_largesize;
    private Button btn_changebackgroundcolor;
    private Button btn_lightblue;
    private Button btn_lightred;
    private Button btn_lightgreen;
    private Button btn_white;
    private int sizeoftextquestion = 16;
    private int colorofbackground = 4;
    LinearLayout linearLayoutchangequestion;
    LinearLayout linearLayoutchangebackground;
    private boolean flagsize = false;
    private boolean flagcolor = false;
    private Button btn_save;
    private Button btn_discard;
    private int currentIndex;
    private int score;
    private int requestCode;

    public static Setting_Fragment newInstance(int currentIndex, int Score, int req_code,
                                               int sizeoftextquestion, int colorofbackground) {

        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_INDEX, currentIndex);
        args.putInt(ARG_SCORE, Score);
        args.putInt(ARG_REQ_CODE, req_code);
        args.putInt(ARG_SIZE_TEXT, sizeoftextquestion);
        args.putInt(ARG_COLOR_BACKGROUND, colorofbackground);
        Setting_Fragment fragment = new Setting_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentIndex = getArguments().getInt(ARG_CURRENT_INDEX);
        score = getArguments().getInt(ARG_SCORE);
        requestCode = getArguments().getInt(ARG_REQ_CODE);
        sizeoftextquestion = getArguments().getInt(ARG_SIZE_TEXT);
        colorofbackground = getArguments().getInt(ARG_COLOR_BACKGROUND);
        if (savedInstanceState != null) {
            sizeoftextquestion = savedInstanceState.getInt(SIZE_QUESTION, 16);
            colorofbackground = savedInstanceState.getInt(COLOE_BACKGROUND, 4);
            flagsize = savedInstanceState.getBoolean(FLAG_SIZE, false);
            flagcolor = savedInstanceState.getBoolean(FLAG_COLOR, false);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting_, container, false);
        findView(view);
        setlistener();
        linearLayoutchangequestion.setVisibility(LinearLayout.GONE);
        linearLayoutchangebackground.setVisibility(LinearLayout.GONE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (flagsize == true) {
            linearLayoutchangequestion.setVisibility(LinearLayout.VISIBLE);

        }
        if (flagcolor == true) {
            linearLayoutchangebackground.setVisibility(LinearLayout.VISIBLE);

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SIZE_QUESTION, sizeoftextquestion);
        outState.putInt(COLOE_BACKGROUND, colorofbackground);
        outState.putBoolean(FLAG_SIZE, flagsize);
        outState.putBoolean(FLAG_COLOR, flagcolor);
    }

    private void findView(View view) {

        btn_changeQuestionSize = view.findViewById(R.id.btn_changesizequestion);
        btn_smallsize = view.findViewById(R.id.btn_smallsize);
        btn_mediumsize = view.findViewById(R.id.btn_meduimsize);
        btn_largesize = view.findViewById(R.id.btn_largesize);
        linearLayoutchangequestion = view.findViewById(R.id.changequestion_layout);
        linearLayoutchangebackground = view.findViewById(R.id.changebackground_layout);
        btn_lightblue = view.findViewById(R.id.btn_lightblue);
        btn_lightred = view.findViewById(R.id.btn_lightred);
        btn_lightgreen = view.findViewById(R.id.btn_lightgreen);
        btn_white = view.findViewById(R.id.btn_white);
        btn_changebackgroundcolor = view.findViewById(R.id.btn_chnagebackground);
        btn_save = view.findViewById(R.id.btn_save);
        btn_discard = view.findViewById(R.id.btn_discard);
    }

    private void setlistener() {
        btn_changeQuestionSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutchangequestion.setVisibility(LinearLayout.VISIBLE);
                flagsize = true;
            }
        });

        btn_smallsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeoftextquestion = 14;

            }
        });


        btn_mediumsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeoftextquestion = 18;

            }
        });

        btn_largesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeoftextquestion = 26;

            }
        });

        btn_changebackgroundcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutchangebackground.setVisibility(LinearLayout.VISIBLE);
                flagcolor = true;
            }
        });

        btn_lightblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 1;

            }
        });

        btn_lightgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 2;

            }
        });

        btn_lightred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 3;

            }
        });

        btn_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 4;

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllData();

            }
        });

        btn_discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    private void setAllData() {
        Intent intent = Geo_Quiz_Activity.newIntent(getActivity(), colorofbackground,
                sizeoftextquestion, currentIndex, score, 2);
        startActivity(intent);
    }
}