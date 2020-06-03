package com.example.agendiario;
//seq-Junho-002
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel1 extends AndroidViewModel {

    private QuestionRepository1 mQuestionRepository1;
    private LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel1 (Application application){
        super(application);
        mQuestionRepository1 = new QuestionRepository1(application);
        mAllQuestions = mQuestionRepository1.getAllQuestions();
    }

    LiveData<List<Question>> getFullQuestions(){return  mAllQuestions;}

    public void insert(Question question){
        mQuestionRepository1.insert(question);
    }

    public void update(Question question){
        mQuestionRepository1.update(question);
    }


    public void delete(Question question){
        mQuestionRepository1.delete(question);
    }

    public void deleteAll(){
        mQuestionRepository1.deleteAll();
    }

}
