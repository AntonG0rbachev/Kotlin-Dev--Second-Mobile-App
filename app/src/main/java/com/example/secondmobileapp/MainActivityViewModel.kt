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

}