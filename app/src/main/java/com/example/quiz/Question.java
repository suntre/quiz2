package com.example.quiz;

public class Question {
    private int QuestionId;
    private boolean Answer;
    public Question(int QuestionId, boolean Answer){
        this.QuestionId = QuestionId;
        this.Answer = Answer;
    }

    public boolean GetAnswer(){
        return this.Answer;
    }

    public int GetQuestionId(){
        return this.QuestionId;
    }
}
