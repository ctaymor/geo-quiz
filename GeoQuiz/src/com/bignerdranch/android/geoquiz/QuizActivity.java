package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 * The primary activity of GeoQuiz app.
 * Asks a question and lets the user answer true/false, this is the
 * Controller layer.
 * 
 * @author Caroline Taymor
 */
public class QuizActivity extends Activity {
    //private static final String TAG = "QuizActivity";
    // TODO: Check proper order for variables
    private static final String KEY_INDEX = "index";
    private static final String CHEATER = "cheater"; 
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    
    private TrueFalseQuestion[] mQuestionBank = new TrueFalseQuestion[]{
        new TrueFalseQuestion(R.string.question_oceans, true),
        new TrueFalseQuestion(R.string.question_mideast, false),
        new TrueFalseQuestion(R.string.question_africa, false),
        new TrueFalseQuestion(R.string.question_americas, true),
        new TrueFalseQuestion(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    
    private boolean mIsCheater;
        
    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                QuizActivity.this.checkAnswer(true);
            }
        });
			
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                QuizActivity.this.checkAnswer(false);
            }
        });
		
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                QuizActivity.this.updateQuestion();
            }
        });
        
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(i, 0);
            }
        });
        
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(CHEATER, false);
        }
        
        this.updateQuestion();
    }
    
    @Override
    protected void onActivityResult(int requestionCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        } else {
            mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
        }
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(CHEATER, mIsCheater);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    };
    
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int answerMessageId = 0;
        
        if (mIsCheater) {
            answerMessageId = R.string.judgement_toast;
        } else{
            if (answerIsTrue == userPressedTrue){
                answerMessageId = R.string.correct_toast;
            } else {
                answerMessageId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(QuizActivity.this, answerMessageId, Toast.LENGTH_SHORT).show();

    };
}
