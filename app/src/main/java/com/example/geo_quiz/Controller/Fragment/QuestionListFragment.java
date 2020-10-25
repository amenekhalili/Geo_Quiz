package com.example.geo_quiz.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.geo_quiz.Controller.Activity.Geo_Quiz_Activity;
import com.example.geo_quiz.Model.Question;
import com.example.geo_quiz.R;
import com.example.geo_quiz.Repository.QuestionRepository;

import java.util.List;


public class QuestionListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    public static QuestionListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        QuestionListFragment fragment = new QuestionListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
       View view =  inflater.inflate(R.layout.fragment_question_list, container, false);
        findViews(view);
        initViews();
       return view;
    }


    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        QuestionRepository questionRepository = QuestionRepository.getInstance();
        List<Question> question = questionRepository.getQuestions();
        QuestionAdapter questionAdapter = new QuestionAdapter(question);
        mRecyclerView.setAdapter(questionAdapter);
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.Questionlist_recyvlerview);
    }



    private class QuestionHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewQuestionList;
        private CheckBox mCheckBoxAnswerList;
        private Question mQuestion;

        public void bindQuestion(Question question) {
            mQuestion = question;
            mTextViewQuestionList.setText(question.getmQuestiontextResId());
            mCheckBoxAnswerList.setChecked(question.ismIsAnswerTrue());
        }

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewQuestionList = itemView.findViewById(R.id.textView_row_list);
            mCheckBoxAnswerList = itemView.findViewById(R.id.checkBox_row_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = Geo_Quiz_Activity.newIntent(getActivity() , mQuestion.getUUID());
                    startActivity(intent);
                }
            });
        }


    }


    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {
        private List<Question> mQuestions;

        public List<Question> getQuestions() {
            return mQuestions;
        }

        public void setQuestions(List<Question> questions) {
            mQuestions = questions;
        }

        public QuestionAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }


        @NonNull
        @Override
        public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.row_list_question, parent, false);
            QuestionHolder questionHolder = new QuestionHolder(view);
            return questionHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bindQuestion(question);

        }


    }
}