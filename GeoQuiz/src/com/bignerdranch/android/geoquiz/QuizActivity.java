package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/*
 * The primary activity of GeoQuiz app.
 * Asks a question and lets the user answer true/false, this is the
 * Controller layer.
 * 
 * @author Caroline Taymor ctaymor@gmail.com
 */
public class QuizActivity extends Activity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    
    private TrueFalseQuestion[] mQuestionBank = new TrueFalseQuestion[] {
        new TrueFalseQuestion(R.string.question_oceans, true),
        new TrueFalseQuestion(R.string.question_mideast, false),
        new TrueFalseQuestion(R.string.question_africa, false),
        new TrueFalseQuestion(R.string.question_americas, true),
        new TrueFalseQuestion(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.this.nextQuestion();
            }
        });
        
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.this.checkAnswer(true);
            }
        });
			
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.this.checkAnswer(false);
            }
        });
		
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.this.nextQuestion();
            }
        });
        
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0) {
                    mCurrentIndex = (mCurrentIndex - 1);
                } else {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                QuizActivity.this.updateQuestion();
            }
        });
        
        this.updateQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    };
    
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int answerMessageId = 0;
        if (answerIsTrue == userPressedTrue) {
            answerMessageId = R.string.correct_toast;
        } else {
            answerMessageId = R.string.incorrect_toast;
        };
        Toast.makeText(QuizActivity.this, answerMessageId, Toast.LENGTH_SHORT).show();
    };
    
    private void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        QuizActivity.this.updateQuestion();
    };
}
