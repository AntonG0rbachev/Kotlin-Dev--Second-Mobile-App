package com.example.secondmobileapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val EXTRA_ANSWER_IS_TRUE = "com.example.secondmobileapp.answer_is_true"
private const val EXTRA_ANSWER_SHOWN = "com.example.secondmobileapp.answer_shown"

class CheatActivity : AppCompatActivity() {

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java)
                .apply {
                    putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                }
        }
    }

    private var answerIsTrue = false
    private lateinit var showAnswerBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val answerTextView = findViewById<TextView>(R.id.answerTextView)
        answerTextView.setText( when {
            answerIsTrue -> R.string.true_text
            else -> R.string.false_text
        })

        answerTextView.visibility = View.INVISIBLE
        showAnswerBtn = findViewById(R.id.showBtn)
        showAnswerBtn.setOnClickListener {
            answerTextView.visibility = View.VISIBLE
            showAnswerBtn.visibility = View.GONE

            val data = Intent()
                .apply {
                    putExtra(EXTRA_ANSWER_SHOWN, true)
            }
            setResult(Activity.RESULT_OK)
        }
    }

}