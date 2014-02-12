package com.bignerdranch.android.geoquiz;
 
 /*
  * Serves as an abstraction for true or false questions for QuizActivity.
  * 
  * @author Caroline Taymor
  */
         
 
public class TrueFalseQuestion {
    private int mQuestion;
    
    private boolean mTrueQuestion;
    
    public TrueFalseQuestion(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }    

    /**
     * Gets the question id for the current TrueFalseQuestion element 
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
     * Gets the answer for the TrueFalseQuestion element
     * @return boolean true if the answer to the question is true, false otherwise
     */
    protected boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    private void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    } 
}
