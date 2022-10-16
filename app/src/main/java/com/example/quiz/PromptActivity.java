package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    private boolean correctAnswer;
    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.quiz.answerShown";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button showCorrectAnswerButton;
        TextView answerTextView;
        Button previousButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        showCorrectAnswerButton = findViewById(R.id.tip_button_confirm);
        answerTextView = findViewById(R.id.tip_text);
        previousButton = findViewById(R.id.previous_button);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);
        showCorrectAnswerButton.setOnClickListener(v -> {
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
        });
        previousButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }
}