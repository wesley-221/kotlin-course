package com.example.level2task2.models

data class Question(
    var question: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            Question("The name Wesley starts with a 'w'", true),
            Question("The name Piet starts with a 'f'", false),
            Question("1+1=2", true),
            Question("This question is written in English", false)
        )
    }
}