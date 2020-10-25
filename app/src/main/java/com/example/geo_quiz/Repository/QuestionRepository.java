package com.example.geo_quiz.Repository;

import com.example.geo_quiz.Model.Question;
import com.example.geo_quiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionRepository {

    private static QuestionRepository sInstance;

    public static QuestionRepository getInstance() {

        if (sInstance == null) {
            sInstance = new QuestionRepository();
        }
        return sInstance;

    }

    private List<Question> mQuestions;


    private QuestionRepository() {
        mQuestions = new ArrayList<>();

        Question question1 = new Question(R.string.question_australia, false);
        Question question2 = new Question(R.string.question_oceans, true);
        Question question3 = new Question(R.string.question_mideast, false);
        Question question4 = new Question(R.string.question_africa, true);
        Question question5 = new Question(R.string.question_americas, false);
        Question question6 = new Question(R.string.question_asia, false);
        Question question7 = new Question(R.string.question_iran , true);
        Question question8 = new Question(R.string.question_german , true);
        Question question9 = new Question(R.string.question_America , false);
        Question question10 = new Question(R.string.question_france , false);
        Question question11 = new Question(R.string.question_lan , true);
        Question question12 = new Question(R.string.question_iraq , true);



        mQuestions.add(question1);
        mQuestions.add(question2);
        mQuestions.add(question3);
        mQuestions.add(question4);
        mQuestions.add(question5);
        mQuestions.add(question6);
        mQuestions.add(question7);
        mQuestions.add(question8);
        mQuestions.add(question9);
        mQuestions.add(question10);
        mQuestions.add(question11);
        mQuestions.add(question12);



    }


    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }

    public void insertQuestion(Question question) {
        mQuestions.add(question);
    }

    public Question getQuestion(UUID id) {

        for (Question question : mQuestions) {
            if (question.getUUID().equals(id))
                return question;
        }
        return null;
    }

    public int getPosition(UUID id){
        for (int i = 0; i <mQuestions.size() ; i++) {
            if(mQuestions.get(i).getUUID().equals(id)){
                return i;
            }

        }
        return -1;
    }

    public Question getQuestion(int index) {
        for (int i = 0; i < mQuestions.size() ; i++) {
            return mQuestions.get(index);
        }
        return null;
    }

    public int QuestionSize(){
        return mQuestions.size();
    }
}
