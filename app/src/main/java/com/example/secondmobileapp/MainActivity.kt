package com.example.secondmobileapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

private const val EXTRA_ANSWER_SHOWN = "com.example.secondmobileapp.answer_shown"

class MainActivity : AppCompatActivity() {
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        nextButton = findViewById(R.id.imbtn1)
        prevButton = findViewById(R.id.imbtn2)
        questionTextView = findViewById(R.id.tv1)

        updateQuestion()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            }
        }

        findViewById<Button>(R.id.cheatBtn).setOnClickListener {
            val intent = CheatActivity.newIntent(this@MainActivity, quizViewModel.currentQuestionAnswer)
            startActivity(intent)
        }

    }

    private val quizViewModel: MainActivityViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider[MainActivityViewModel::class.java]
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val isCheat = quizViewModel.isCheater
        val messageReId = when {
            isCheat -> R.string.cheater_text
            userAnswer == correctAnswer -> R.string.true_text
            else -> R.string.false_text
        }
        Toast.makeText(this, messageReId, Toast.LENGTH_LONG).show()
    }

}