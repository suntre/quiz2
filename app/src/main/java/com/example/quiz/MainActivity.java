package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trueButton;
        Button falseButton;
        Button nextButton;

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(v -> checkAnswerCorrectness(true));

        falseButton.setOnClickListener(v -> checkAnswerCorrectness(false));

        nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1)%questions.length;
            setNextQuestion();
        });
        setNextQuestion();

    }

    protected void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].GetAnswer();
        int resultMessageId;

        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        }
        else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].GetQuestionId());
    }
}