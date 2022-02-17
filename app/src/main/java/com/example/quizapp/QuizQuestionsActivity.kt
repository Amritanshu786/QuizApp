package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition:Int = 1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition:Int=0
    private var mCorrectAnswer:Int = 0
    private var mUserName:String?=null
    private var mSelectionAllowed:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList=Constants.getQuestions()
        setQuestions()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
    }

    private fun setQuestions()
    {
        mSelectionAllowed = true
        val question: Question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }
        ProgressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition" + "/" + ProgressBar.max

        tvQuestion.text = question.question
        ivImage.setImageResource(question.Image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView()
    {
        if (mSelectionAllowed)
        {
            val options = ArrayList<TextView>()
            options.add(0, tvOptionOne)
            options.add(1, tvOptionTwo)
            options.add(2, tvOptionThree)
            options.add(3, tvOptionFour)

            for (option in options) {
                option.setTextColor(Color.parseColor("#7A8089"))
                option.typeface = Typeface.DEFAULT
                option.background =
                    ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.tvOptionOne->{
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tvOptionTwo->{
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tvOptionThree->{
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.tvOptionFour->{
                selectedOptionView(tvOptionFour, 4)
            }
            R.id.btnSubmit->{
                if(mSelectedOptionPosition==0)
                {
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionsList!!.size->{
                            setQuestions()
                        }
                        else->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer!=mSelectedOptionPosition)
                    {
                        answerView(mSelectedOptionPosition, R.drawable.incorrect_option_border_bg)
                    }
                    else
                    {
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    if(mCurrentPosition==mQuestionsList!!.size)
                    {
                        btnSubmit.text = "FINISH"
                    }
                    else
                    {
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                    mSelectionAllowed = false
                }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int)
    {
        when (answer) {
            1 -> {
                tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int)
    {
        defaultOptionsView()
        if (mSelectionAllowed)
        {
            mSelectedOptionPosition = selectedOptionNum
            tv.setTextColor(Color.parseColor("#363A43"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        }
    }
}