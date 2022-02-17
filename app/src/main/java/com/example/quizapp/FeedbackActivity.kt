package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muddzdev.styleabletoastlibrary.StyleableToast
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        setSupportActionBar(toolbar)

        submit.setOnClickListener{
            StyleableToast.makeText(this, "Thank you for your feedback.", R.style.customToast).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}