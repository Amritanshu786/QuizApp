package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN         // To remove the status bar
        //supportActionBar?.hide()      //To remove the Action bar (It will not remove the status bar)
        //actionBar?.hide()             // same result as above

        btnStart.setOnClickListener{
            val name = etName.text.toString().trim()
            if(name.isEmpty())
            {
                etName.error = "Please Enter your name"
            }
            else
            {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, name)
                startActivity(intent)
                finish()
            }
        }
    }
    /*fun onClick(view:View)
    {
        if(etName.text.toString().isEmpty())
        {
            etName.error = "Please Enter your name"
            return
        }
        else
            {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }
    }
     */
}