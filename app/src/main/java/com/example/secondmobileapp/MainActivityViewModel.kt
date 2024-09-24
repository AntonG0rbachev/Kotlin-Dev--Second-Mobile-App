package com.example.secondmobileapp

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.firstQuestion, true),
        Question(R.string.secondQuestion, false),
        Question(R.string.thirdQuestion, false),
        Question(R.string.fourthQuestion, false),
        Question(R.string.fifthQuestion, true),
        Question(R.string.sixthQuestion, true),
        Question(R.string.seventhQuestion, false),
    )

    private var currentIndex = 0
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = (questionBank.size + currentIndex - 1) % questionBank.size
    }

}