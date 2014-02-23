package com.bignerdranch.android.geoquiz;

/*
 * An abstraction for true or false questions for QuizActivity.
 * 
 * @author Caroline Taymor ctaymor@gmail.com
 */
public class TrueFalseQuestion {
    private int mQuestion;
    private boolean mTrueQuestion;
    
    /**
     * Constructor for TrueFalse Question
     * @param question resource id for the question for this TrueFalseQuestion
     * @param trueQuestion true if the question is correct, false otherwise
     */
    public TrueFalseQuestion(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }
    

    /**
     * Gets the resource id for the current question
     * @return int the id for the string representing the question to ask 
     *      the user 
     */
    protected int getQuestion() {
        return mQuestion;
    }

    private void setQuestion(int question) {
        mQuestion = question;
    }

    
    /**
     * Gets the answer for the TrueFalseQuestion instance
     * @return boolean true if the answer to the question is true, false otherwise
     */
    protected boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    private void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
    

}
