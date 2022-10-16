package com.example.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Application;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private static final String QUIZ_TAG = "MainActivity";
    public static final String KEY_EXTRA_ANSWER = "extraAnswer";
    public static final int REQUEST_CODE_PROMPT = 0;
    private boolean answerWasShown = false;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(QUIZ_TAG, "On destroy activated");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "On save instace activated");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(QUIZ_TAG,"On stop activated");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(QUIZ_TAG, "On start activated");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(QUIZ_TAG, "On pause activated");
    }

    private final Question[] questions = new Question[]{
            new Question(R.string.q_design, true),
            new Question(R.string.q_file_r, false),
            new Question(R.string.q_java_csharp, true),
            new Question(R.string.q_kotlin, true),
            new Question(R.string.q_multi_platform, true)
    };

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button trueButton;
        Button falseButton;
        Button nextButton;
        Button tipButton;

        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG, "On create activated");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        tipButton = findViewById(R.id.tip_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(v -> checkAnswerCorrectness(true));

        falseButton.setOnClickListener(v -> checkAnswerCorrectness(false));

        nextButton.setOnClickListener((v) -> {
            currentIndex = (currentIndex + 1)%questions.length;
            answerWasShown = false;
            setNextQuestion();
        });
        tipButton.setOnClickListener((v -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].GetAnswer();
            intent.putExtra (KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);

        }));

        setNextQuestion();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) { return;}
        if(requestCode == REQUEST_CODE_PROMPT){
            if(data == null) {return;}
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    protected void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].GetAnswer();
        int resultMessageId;
        if(answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        }
        else {
            if (userAnswer == correctAnswer) {
                resultMessageId = R.string.correct_answer;
            } else {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].GetQuestionId());
    }
}